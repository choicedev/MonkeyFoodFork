package com.choice.monkeyfoodfork

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MonkeyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}