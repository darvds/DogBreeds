package com.darvds.dogbreeds.application

import android.app.Application
import com.darvds.dogbreeds.di.components.AppComponent
import com.darvds.dogbreeds.di.components.DaggerAppComponent

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .build()
    }
}