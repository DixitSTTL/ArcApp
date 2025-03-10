package com.app.myinapp.presentation.di

import com.app.myinapp.data.utils.SharedPrefManager
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

}