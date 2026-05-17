package com.genesys.feature.koreader.device.lights

import android.app.Activity
import android.provider.Settings
import timber.log.Timber
import com.genesys.feature.koreader.device.Ioctl
import com.genesys.feature.koreader.device.LightsInterface

/* Special controller for Tolino Page 2
 *
 * Same as `./TolinoNtxController.kt` but without warmth support.
 */

class TolinoNtxNoWarmthController : Ioctl(), LightsInterface {

    companion object {
        private const val TAG = "Lights"
        private const val BRIGHTNESS_MAX = 255
        private const val MIN = 0
    }

    override fun getPlatform(): String {
        return "tolino"
    }

    override fun hasFallback(): Boolean {
        return false
    }

    override fun hasWarmth(): Boolean {
        return false
    }

    override fun needsPermission(): Boolean {
        return false
    }

    override fun enableFrontlightSwitch(activity: Activity): Int {
        return 1
    }

    override fun getBrightness(activity: Activity): Int {
        return try {
            Settings.System.getInt(activity.applicationContext.contentResolver,
                "screen_brightness")
        } catch (e: Exception) {
            Timber.tag(TAG).w( e.toString())
            0
        }
    }

    override fun getWarmth(activity: Activity): Int {
        Timber.tag(TAG).w( "getWarmth: not implemented")
        return 0
    }

    override fun setBrightness(activity: Activity, brightness: Int) {
        if (brightness < MIN || brightness > BRIGHTNESS_MAX) {
            Timber.tag(TAG).w( "brightness value of of range: $brightness")
            return
        }
        Timber.tag(TAG).v( "Setting brightness to $brightness")
        try {
            Settings.System.putInt(activity.applicationContext.contentResolver,
                "screen_brightness", brightness)
        } catch (e: Exception) {
            Timber.tag(TAG).w( "$e")
        }
    }

    override fun setWarmth(activity: Activity, warmth: Int) {
        Timber.tag(TAG).w( "ignoring setWarmth: not implemented")
    }

    override fun getMinWarmth(): Int {
        return 0
    }

    override fun getMaxWarmth(): Int {
        return 0
    }

    override fun getMinBrightness(): Int {
        return MIN
    }

    override fun getMaxBrightness(): Int {
        return BRIGHTNESS_MAX
    }

    override fun hasStandaloneWarmth(): Boolean {
        return false
    }
}
