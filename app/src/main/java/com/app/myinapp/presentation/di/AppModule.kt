package com.app.myinapp.presentation.di

import com.app.myinapp.data.utils.SharedPrefManager
import com.app.myinapp.domain.room.DatabaseHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val AppModule = module {
    includes(
        NetworkModule,
        ViewModelModule,
        UseCaseModule,
        RepositoryModule
    )

    single { SharedPrefManager(androidContext()) }
    single { DatabaseHelper.getInstance(get()) }

}