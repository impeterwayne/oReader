package org.koreader.launcher.device.lights

import android.app.Activity
import timber.log.Timber
import org.koreader.launcher.device.LightsInterface
import org.koreader.launcher.extensions.read
import org.koreader.launcher.extensions.write
import java.io.File

class OnyxC67Controller : LightsInterface {
    companion object {
        private const val TAG = "Lights"
        private const val BRIGHTNESS_MAX = 255
        private const val BRIGHTNESS_MIN = 0
        private const val BRIGHTNESS_FILE = "/sys/class/backlight/rk28_bl/brightness"
    }

    override fun getPlatform(): String {
        return "onyx-c67"
    }

    override fun hasFallback(): Boolean {
        return true
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
        return File(BRIGHTNESS_FILE).read()
    }

    override fun getWarmth(activity: Activity): Int {
        Timber.tag(TAG).w( "getWarmth: not implemented")
        return 0
    }

    override fun setBrightness(activity: Activity, brightness: Int) {
        if (brightness < BRIGHTNESS_MIN || brightness > BRIGHTNESS_MAX) {
            Timber.tag(TAG).w( "brightness value of of range: $brightness")
            return
        }
        Timber.tag(TAG).v( "Setting brightness to $brightness")
        File(BRIGHTNESS_FILE).write(brightness)
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
        return BRIGHTNESS_MIN
    }

    override fun getMaxBrightness(): Int {
        return BRIGHTNESS_MAX
    }

    override fun hasStandaloneWarmth(): Boolean {
        return false
    }
}
