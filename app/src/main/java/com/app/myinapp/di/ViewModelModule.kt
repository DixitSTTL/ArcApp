package com.app.myinapp.di

import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.domain.model.Photo
import com.app.myinapp.presentation.app.AppViewModel
import com.app.myinapp.presentation.imagePreview.ImagePreviewViewModel
import com.app.myinapp.presentation.main.MainScreenViewModel
import com.app.myinapp.presentation.search.SearchScreenViewModel
import com.app.myinapp.presentation.setting.SettingScreenViewModel
import com.app.myinapp.presentation.videoPreview.VideoScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {

    viewModel { MainScreenViewModel(get(), get()) }
    viewModel { SearchScreenViewModel(get()) }
    viewModel { (photo: Photo) -> ImagePreviewViewModel(get(), get(), get()) }
    viewModel { SettingScreenViewModel(get()) }
    viewModel { (video: VideoDTO) -> VideoScreenViewModel(video, get(), get()) }
    viewModel { AppViewModel(get()) }

}