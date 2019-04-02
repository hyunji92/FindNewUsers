package com.hyundeee.app.findnewusers

import android.app.Application
import com.hyundeee.app.findnewusers.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            //androidContext(this@com.hyundeee.app.findnewusers.MyApplication)
            // modules
            modules(appModules)
        }
    }
}
