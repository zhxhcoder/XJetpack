package com.zhxh.xjetpack;

import android.app.Application;

import timber.log.Timber;

public class JetPackApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Plant a Timber Debug Tree here
        Timber.plant(new Timber.DebugTree());
    }
}
