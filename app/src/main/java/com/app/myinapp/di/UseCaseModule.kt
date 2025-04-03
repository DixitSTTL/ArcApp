package com.app.myinapp.di

import com.app.myinapp.domain.usecase.UseCaseDownloadFile
import com.app.myinapp.domain.usecase.UseCaseImagePreviewScreen
import com.app.myinapp.domain.usecase.UseCaseMainScreen
import com.app.myinapp.domain.usecase.UseCaseSearchScreen
import com.app.myinapp.domain.usecase.UseCaseSettingScreen
import com.app.myinapp.domain.usecase.UseCaseTheme
import org.koin.dsl.module

val UseCaseModule = module {

    single { UseCaseTheme(get()) }
    single { UseCaseMainScreen(get()) }
    single { UseCaseSearchScreen(get()) }
    single { UseCaseImagePreviewScreen(get()) }
    single { UseCaseSettingScreen(get()) }
    single { UseCaseDownloadFile(get()) }

}