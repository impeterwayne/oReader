package com.genesys.feature.notebook.editor.utils

import android.util.Log
import com.onyx.android.sdk.api.device.epd.EpdController
import com.onyx.android.sdk.api.device.epd.UpdateOption
import com.onyx.android.sdk.device.Device

private const val TAG = "OnyxSdkCompat"
private const val DEFAULT_MAX_TOUCH_PRESSURE = 4096f

object OnyxSdkCompat {
    @Volatile
    private var epdUnavailable = false

    @Volatile
    private var rawInputUnavailable = false

    val isEpdAvailable: Boolean
        get() = DeviceCompat.isOnyxDevice && !epdUnavailable

    val isRawInputAvailable: Boolean
        get() = DeviceCompat.isOnyxDevice && !rawInputUnavailable

    fun markRawInputFailure(throwable: Throwable) {
        if (rawInputUnavailable) return
        rawInputUnavailable = true
        Log.w(TAG, "Disabling raw input after TouchHelper failure", throwable)
    }

    fun getMaxTouchPressureOrDefault(default: Float = DEFAULT_MAX_TOUCH_PRESSURE): Float =
        runEpd(default, "getMaxTouchPressure") {
            EpdController.getMaxTouchPressure()
        }

    fun getAppScopeRefreshModeOrNull(): UpdateOption? =
        runDevice(null, "getAppScopeRefreshMode") {
            Device.currentDevice().appScopeRefreshMode
        }

    fun setStrokeParameters(styleId: Int, params: FloatArray) {
        runDevice(Unit, "setStrokeParameters") {
            Device.currentDevice().setStrokeParameters(styleId, params)
        }
    }

    fun <T> runEpd(defaultValue: T, operation: String, block: () -> T): T {
        if (!isEpdAvailable) return defaultValue
        return try {
            block()
        } catch (throwable: Throwable) {
            epdUnavailable = true
            Log.w(TAG, "Disabling EPD integration after $operation failed", throwable)
            defaultValue
        }
    }

    fun <T> runDevice(defaultValue: T, operation: String, block: () -> T): T {
        if (!DeviceCompat.isOnyxDevice) return defaultValue
        return try {
            block()
        } catch (throwable: Throwable) {
            Log.w(TAG, "Device access failed during $operation", throwable)
            defaultValue
        }
    }
}
