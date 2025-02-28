package com.app.myinapp.presentation.di

import com.app.myinapp.presentation.main.MainScreenViewModel
import org.koin.dsl.module

val ViewModelModule = module {

    factory { MainScreenViewModel(get(), get()) }

}