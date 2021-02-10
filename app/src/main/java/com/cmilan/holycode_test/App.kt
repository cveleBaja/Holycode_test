package com.cmilan.holycode_test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        private lateinit var mInstance: App

        fun getInstance(): App = mInstance
    }

    override fun onCreate() {
        super.onCreate()

        mInstance = this
    }
}