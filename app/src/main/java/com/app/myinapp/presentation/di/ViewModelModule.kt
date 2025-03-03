package com.app.myinapp.presentation.di

import com.app.myinapp.presentation.main.MainScreenViewModel
import com.app.myinapp.presentation.search.SearchScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {

    viewModel { MainScreenViewModel(get(), get()) }
    viewModel { SearchScreenViewModel(get()) }

}