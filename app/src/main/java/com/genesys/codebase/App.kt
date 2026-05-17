package com.genesys.codebase

import android.app.Application
import android.os.Build
import com.onyx.android.sdk.rx.RxManager
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp
import org.koreader.launcher.MainApp
import org.lsposed.hiddenapibypass.HiddenApiBypass
import timber.log.Timber

/**
 * @author : CuongNK
 * @created : 9/1/2025
 **/

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        RxManager.Builder.initAppContext(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            HiddenApiBypass.addHiddenApiExemptions("")
        }
        MainApp.initialize(this)
        MMKV.initialize(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
