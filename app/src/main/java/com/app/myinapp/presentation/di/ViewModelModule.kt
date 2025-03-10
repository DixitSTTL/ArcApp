package com.app.myinapp.presentation.di

import com.app.myinapp.presentation.app.AppViewModel
import com.app.myinapp.presentation.imagePreview.ImagePreviewViewModel
import com.app.myinapp.presentation.main.MainScreenViewModel
import com.app.myinapp.presentation.search.SearchScreenViewModel
import com.app.myinapp.presentation.setting.SettingScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {

    viewModel { MainScreenViewModel(get(), get()) }
    viewModel { SearchScreenViewModel(get()) }
    viewModel { ImagePreviewViewModel(get()) }
    viewModel { SettingScreenViewModel(get()) }
    viewModel { AppViewModel(get()) }

}