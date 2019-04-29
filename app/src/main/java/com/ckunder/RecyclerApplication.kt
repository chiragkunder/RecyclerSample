package com.ckunder

import android.app.Application
import timber.log.Timber

class RecyclerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}