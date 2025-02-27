package com.app.myinapp.presentation.di

import org.koin.dsl.module

val AppModule = module {
    includes(
        NetworkModule,
        ViewModelModule,
        UseCaseModule,
        RepositoryModule
    )

}