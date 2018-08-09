package com.zhxh.xjetpack

import android.app.Application

import timber.log.Timber

class JetPackApp : Application() {
    override fun onCreate() {
        super.onCreate()

        //Plant a Timber Debug Tree here
        Timber.plant(Timber.DebugTree())
    }
}
