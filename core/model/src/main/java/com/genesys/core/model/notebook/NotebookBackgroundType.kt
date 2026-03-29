package com.genesys.core.model.notebook

sealed class NotebookBackgroundType(
    val key: String,
    val folderName: String
) {
    data object Image : NotebookBackgroundType("image", "images")
    data object ImageRepeating : NotebookBackgroundType("imagerepeating", "images")
    data object CoverImage : NotebookBackgroundType("coverImage", "covers")
    data object Native : NotebookBackgroundType("native", "")
    data object AutoPdf : NotebookBackgroundType("autoPdf", "pdfs")
    data class Pdf(val page: Int) : NotebookBackgroundType("pdf$page", "pdfs")

    fun resolveForExport(currentPage: Int?): NotebookBackgroundType =
        when (this) {
            AutoPdf -> currentPage?.let(::Pdf) ?: Native
            else -> this
        }

    companion object {
        fun fromKey(key: String): NotebookBackgroundType =
            when {
                key == Image.key -> Image
                key == ImageRepeating.key -> ImageRepeating
                key == CoverImage.key -> CoverImage
                key == Native.key -> Native
                key == AutoPdf.key -> AutoPdf
                key.startsWith("pdf") && key.removePrefix("pdf").toIntOrNull() != null -> {
                    Pdf(key.removePrefix("pdf").toInt())
                }

                else -> Native
            }
    }
}
