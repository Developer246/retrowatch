package com.hardcopy.retrowatch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Application class with Hilt initialization
 */
@HiltAndroidApp
class RetroWatchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeLogging()
    }

    private fun initializeLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("RetroWatch app initialized")
        }
    }
}
