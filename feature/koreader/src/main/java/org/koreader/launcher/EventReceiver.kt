// A broadcast receiver that writes event codes to a Unix domain socket, so they can be consumed from lua

package org.koreader.launcher

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import timber.log.Timber
import kotlin.collections.HashMap
import kotlin.collections.get
import kotlin.collections.iterator

class EventReceiver : BroadcastReceiver() {
    private val tag = this::class.java.simpleName
    private val eventMap = HashMap<String, Int>()

    init {
        eventMap[Intent.ACTION_POWER_CONNECTED] = 100
        eventMap[Intent.ACTION_POWER_DISCONNECTED] = 101
        eventMap[DownloadManager.ACTION_DOWNLOAD_COMPLETE] = 110
    }

    val filter: IntentFilter
        get() {
            val info = StringBuilder()
            val filter = IntentFilter()
            for ((key, _) in eventMap) {
                info.append("$key\n".padStart(2))
                filter.addAction(key)
            }

            Timber.tag(tag).v("Filtering ${eventMap.size} events: \n$info")
            return filter
        }

    private fun write(code: Int?) {
        if (code == null) {
            Timber.tag(tag).e("Invalid code: must be a 32-bit integer")
            return
        }

        try {
            val rc = nativeSendEvent(code)
            if (rc != 0) {
                Timber.tag(tag).e("nativeSendEvent failed with code $rc")
            }
        } catch (e: Throwable) {
            Timber.tag(tag).e(e, "Cannot send event to native socket")
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { event ->
            if (eventMap.containsKey(event.action)) {
                Timber.tag(tag).v("Received event ${event.action}")
                write(eventMap[event.action])
            }
        }
    }

    companion object {
        @JvmStatic
        private external fun nativeSendEvent(code: Int): Int
    }
}
