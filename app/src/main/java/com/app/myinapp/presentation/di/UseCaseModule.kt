package com.app.myinapp.presentation.di

import com.app.myinapp.domain.usecase.UseCaseMainScreen
import com.app.myinapp.domain.usecase.UseCaseSearchScreen
import org.koin.dsl.module

val UseCaseModule = module {

    single { UseCaseMainScreen(get()) }
    single { UseCaseSearchScreen(get()) }

}