package com.genesys.core.database.converters

import com.genesys.core.model.notebook.NotebookStrokePoint
import net.jpountz.lz4.LZ4Factory
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.pow
import kotlin.math.round

private const val PressureBit = 0
private const val TiltXBit = 1
private const val TiltYBit = 2
private const val DeltaTimeBit = 3

private const val PressureMask = 1 shl PressureBit
private const val TiltXMask = 1 shl TiltXBit
private const val TiltYMask = 1 shl TiltYBit
private const val DeltaTimeMask = 1 shl DeltaTimeBit

private const val Magic0: Byte = 'S'.code.toByte()
private const val Magic1: Byte = 'B'.code.toByte()
private const val FormatVersion: Byte = 1
private const val CompressionNone: Byte = 0
private const val CompressionLz4: Byte = 1
private const val HeaderSize = 1 + 1 + 1 + 1 + 4 + 1
private const val EncodingPrecisionXy = 2
private const val MaxDeltaTimeValue = 0xFFFE
private const val MinBytesForCompression = 512
private const val MinSavingRatio = 0.75

private val lz4Factory = LZ4Factory.fastestInstance()
private val lz4Compressor = lz4Factory.highCompressor()
private val lz4Decompressor = lz4Factory.fastDecompressor()

private fun Int.hasPressure() = (this and PressureMask) != 0
private fun Int.hasTiltX() = (this and TiltXMask) != 0
private fun Int.hasTiltY() = (this and TiltYMask) != 0
private fun Int.hasDeltaTime() = (this and DeltaTimeMask) != 0

private fun lz4Compress(rawBody: ByteArray): ByteArray {
    val maxCompressedLength = lz4Compressor.maxCompressedLength(rawBody.size)
    val compressed = ByteArray(maxCompressedLength)
    val compressedSize =
        lz4Compressor.compress(rawBody, 0, rawBody.size, compressed, 0, maxCompressedLength)
    return compressed.copyOf(compressedSize)
}

private fun compressBody(rawBody: ByteArray): Pair<Byte, ByteArray> {
    if (rawBody.size < MinBytesForCompression) {
        return CompressionNone to rawBody
    }

    val compressed = lz4Compress(rawBody)
    return if (compressed.size.toDouble() <= rawBody.size * MinSavingRatio) {
        CompressionLz4 to compressed
    } else {
        CompressionNone to rawBody
    }
}

fun computeNotebookStrokeMask(points: List<NotebookStrokePoint>): Int {
    require(points.isNotEmpty()) { "Stroke points cannot be empty." }

    val point = points.first()
    var mask = 0
    if (point.pressure != null) mask = mask or PressureMask
    if (point.tiltX != null) mask = mask or TiltXMask
    if (point.tiltY != null) mask = mask or TiltYMask
    if (point.dt != null) mask = mask or DeltaTimeMask
    return mask
}

private fun validateUniform(mask: Int, points: List<NotebookStrokePoint>) {
    if (mask.hasPressure()) {
        require(points.all { it.pressure != null }) { "Pressure must be present for all points." }
    } else {
        require(points.all { it.pressure == null }) { "Pressure must be absent for all points." }
    }

    if (mask.hasTiltX()) {
        require(points.all { it.tiltX != null }) { "Tilt X must be present for all points." }
    } else {
        require(points.all { it.tiltX == null }) { "Tilt X must be absent for all points." }
    }

    if (mask.hasTiltY()) {
        require(points.all { it.tiltY != null }) { "Tilt Y must be present for all points." }
    } else {
        require(points.all { it.tiltY == null }) { "Tilt Y must be absent for all points." }
    }

    if (mask.hasDeltaTime()) {
        require(points.all { it.dt != null }) { "Delta time must be present for all points." }
    } else {
        require(points.all { it.dt == null }) { "Delta time must be absent for all points." }
    }
}

fun encodeNotebookStrokePoints(
    points: List<NotebookStrokePoint>,
    mask: Int = computeNotebookStrokeMask(points)
): ByteArray {
    require(points.isNotEmpty()) { "Stroke points cannot be empty." }
    validateUniform(mask, points)

    val encodedX = encodePolyline(points.map { it.x }, precision = EncodingPrecisionXy)
        .toByteArray(Charsets.UTF_8)
    val encodedY = encodePolyline(points.map { it.y }, precision = EncodingPrecisionXy)
        .toByteArray(Charsets.UTF_8)

    val count = points.size
    val bodySize =
        4 + encodedX.size +
            4 + encodedY.size +
            (if (mask.hasPressure()) count * 2 else 0) +
            (if (mask.hasTiltX()) count else 0) +
            (if (mask.hasTiltY()) count else 0) +
            (if (mask.hasDeltaTime()) count * 2 else 0)

    val rawBody = ByteBuffer.allocate(bodySize).order(ByteOrder.LITTLE_ENDIAN).apply {
        putInt(encodedX.size)
        put(encodedX)
        putInt(encodedY.size)
        put(encodedY)

        if (mask.hasPressure()) {
            points.forEach { putShort(it.pressure!!.toInt().toShort()) }
        }
        if (mask.hasTiltX()) {
            points.forEach { put(it.tiltX!!.toByte()) }
        }
        if (mask.hasTiltY()) {
            points.forEach { put(it.tiltY!!.toByte()) }
        }
        if (mask.hasDeltaTime()) {
            points.forEach { point ->
                putShort(point.dt!!.toInt().coerceIn(0, MaxDeltaTimeValue).toShort())
            }
        }
    }.array()

    val (compressionFlag, finalBody) = compressBody(rawBody)
    val compressedHeaderSize = if (compressionFlag == CompressionLz4) 4 else 0
    val buffer = ByteBuffer
        .allocate(HeaderSize + compressedHeaderSize + finalBody.size)
        .order(ByteOrder.LITTLE_ENDIAN)
    buffer.put(Magic0)
    buffer.put(Magic1)
    buffer.put(FormatVersion)
    buffer.put(mask.toByte())
    buffer.putInt(count)
    buffer.put(compressionFlag)
    if (compressionFlag == CompressionLz4) {
        buffer.putInt(rawBody.size)
    }
    buffer.put(finalBody)

    return buffer.array()
}

fun decodeNotebookStrokePoints(bytes: ByteArray): List<NotebookStrokePoint> {
    require(bytes.size >= HeaderSize) { "Stroke payload is too small." }

    val buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN)
    require(buffer.get() == Magic0 && buffer.get() == Magic1) { "Invalid stroke payload." }
    require(buffer.get() <= FormatVersion) { "Unsupported stroke payload version." }

    val mask = buffer.get().toInt() and 0xFF
    val count = buffer.int
    require(count >= 0) { "Negative point count is invalid." }
    val compressionFlag = buffer.get()
    val bodyBuffer = when (compressionFlag) {
        CompressionNone -> buffer
        CompressionLz4 -> {
            require(buffer.remaining() >= 4) { "Missing raw size for compressed stroke payload." }
            val rawSize = buffer.int
            require(rawSize > 0) { "Invalid raw size for compressed stroke payload." }
            val compressed = ByteArray(buffer.remaining())
            buffer.get(compressed)
            val decompressed = ByteArray(rawSize)
            lz4Decompressor.decompress(compressed, 0, decompressed, 0, rawSize)
            ByteBuffer.wrap(decompressed).order(ByteOrder.LITTLE_ENDIAN)
        }
        else -> error("Unknown stroke compression flag $compressionFlag")
    }

    val xValues = decodeFloatSection(bodyBuffer, precision = EncodingPrecisionXy)
    val yValues = decodeFloatSection(bodyBuffer, precision = EncodingPrecisionXy)
    require(xValues.size == count && yValues.size == count) {
        "Decoded point count does not match header."
    }

    val pressures = if (mask.hasPressure()) ShortArray(count) { bodyBuffer.short } else null
    val tiltXs = if (mask.hasTiltX()) ByteArray(count) { bodyBuffer.get() } else null
    val tiltYs = if (mask.hasTiltY()) ByteArray(count) { bodyBuffer.get() } else null
    val deltaTimes = if (mask.hasDeltaTime()) ShortArray(count) { bodyBuffer.short } else null

    return List(count) { index ->
        NotebookStrokePoint(
            x = xValues[index],
            y = yValues[index],
            pressure = pressures?.get(index)?.toFloat(),
            tiltX = tiltXs?.get(index)?.toInt(),
            tiltY = tiltYs?.get(index)?.toInt(),
            dt = deltaTimes?.get(index)?.toUShort()
        )
    }
}

fun getNotebookStrokeMask(bytes: ByteArray): Int {
    require(bytes.size >= HeaderSize) { "Stroke payload is too small." }

    val buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN)
    require(buffer.get() == Magic0 && buffer.get() == Magic1) { "Invalid stroke payload." }
    require(buffer.get() <= FormatVersion) { "Unsupported stroke payload version." }
    return buffer.get().toInt() and 0xFF
}

private fun decodeFloatSection(buffer: ByteBuffer, precision: Int): List<Float> {
    val size = buffer.int
    require(size >= 0 && buffer.remaining() >= size) { "Stroke payload is truncated." }
    val bytes = ByteArray(size)
    buffer.get(bytes)
    return decodePolyline(String(bytes, Charsets.UTF_8), precision) { it.toFloat() }
}

private fun <T : Number> encodePolyline(coords: List<T>, precision: Int): String {
    val result = mutableListOf<String>()
    var previousValue = 0

    for (value in coords) {
        val scaled = (value.toDouble() * 10.0.pow(precision)).toInt()
        result.add(encodeValue(scaled - previousValue))
        previousValue = scaled
    }

    return result.joinToString("")
}

private fun encodeValue(value: Int): String {
    val actualValue = if (value < 0) (value shl 1).inv() else (value shl 1)
    return splitIntoChunks(actualValue).map { (it + 63).toChar() }.joinToString("")
}

private fun splitIntoChunks(value: Int): List<Int> {
    val chunks = mutableListOf<Int>()
    var remaining = value
    while (remaining >= 32) {
        chunks.add((remaining and 31) or 0x20)
        remaining = remaining shr 5
    }
    chunks.add(remaining)
    return chunks
}

private fun <T : Number> decodePolyline(
    polyline: String,
    precision: Int,
    caster: (Double) -> T
): List<T> {
    val valueChunks = mutableListOf<MutableList<Int>>()
    valueChunks.add(mutableListOf())

    for (char in polyline.toCharArray()) {
        var value = char.code - 63
        val isLast = (value and 0x20) == 0
        value = value and 0x1F
        valueChunks.last().add(value)
        if (isLast) {
            valueChunks.add(mutableListOf())
        }
    }

    valueChunks.removeAt(valueChunks.lastIndex)

    val deltas = mutableListOf<Double>()
    for (chunk in valueChunks) {
        var coordinate = chunk.mapIndexed { index, part -> part shl (index * 5) }
            .reduce { acc, part -> acc or part }
        if (coordinate and 1 > 0) {
            coordinate = coordinate.inv()
        }
        coordinate = coordinate shr 1
        deltas.add(coordinate.toDouble() / 10.0.pow(precision))
    }

    val values = mutableListOf<T>()
    var previousValue = 0.0
    for (delta in deltas) {
        previousValue += delta
        values.add(caster(roundToPrecision(previousValue, precision)))
    }
    return values
}

private fun roundToPrecision(value: Double, precision: Int): Double {
    val factor = 10.0.pow(precision)
    return round(value * factor) / factor
}
