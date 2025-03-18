package com.app.myinapp.presentation.di

import com.app.myinapp.data.repository.imagePreview.ImagePreviewRepositoryImpl
import com.app.myinapp.data.repository.mainscreen.MainScreenRepositoryImpl
import com.app.myinapp.data.repository.mainscreen.dataSource.ImageListPagingSource
import com.app.myinapp.data.repository.mainscreen.dataSource.VideoListPagingSource
import com.app.myinapp.data.repository.search.SearchScreenRepositoryImpl
import com.app.myinapp.data.repository.setting.SettingScreenRepositoryImpl
import com.app.myinapp.data.repository.user.UserRepositoryImpl
import com.app.myinapp.domain.repository.ImagePreviewRepository
import com.app.myinapp.domain.repository.MainScreenRepository
import com.app.myinapp.domain.repository.SearchScreenRepository
import com.app.myinapp.domain.repository.SettingScreenRepository
import com.app.myinapp.domain.repository.UserRepository
import org.koin.dsl.module

val RepositoryModule = module {

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MainScreenRepository> { MainScreenRepositoryImpl(get(), get(), get(), get()) }
    single<SearchScreenRepository> { SearchScreenRepositoryImpl(get()) }
    single<ImagePreviewRepository> { ImagePreviewRepositoryImpl(get(),get()) }
    single<SettingScreenRepository> { SettingScreenRepositoryImpl(get()) }
    factory { ImageListPagingSource(get()) }
    factory { VideoListPagingSource(get()) }
}