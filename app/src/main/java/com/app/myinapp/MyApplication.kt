package com.app.myinapp

import android.app.Application
import com.app.myinapp.presentation.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(AppModule)

        }
    }
}