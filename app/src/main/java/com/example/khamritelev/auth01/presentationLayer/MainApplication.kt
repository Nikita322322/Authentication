package com.example.khamritelev.auth01.presentationLayer

import android.app.Application
import com.example.khamritelev.auth01.injection.appModule
import com.example.khamritelev.auth01.injection.presentationModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(listOf(appModule))
    }
}

