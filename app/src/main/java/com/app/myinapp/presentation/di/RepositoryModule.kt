package com.app.myinapp.presentation.di

import com.app.myinapp.data.repository.imagePreview.ImagePreviewRepositoryImpl
import com.app.myinapp.data.repository.mainscreen.MainScreenRepositoryImpl
import com.app.myinapp.data.repository.mainscreen.dataSource.ImageListPagingSource
import com.app.myinapp.data.repository.mainscreen.dataSource.VideoListPagingSource
import com.app.myinapp.data.repository.search.SearchScreenRepositoryImpl
import com.app.myinapp.domain.repository.ImagePreviewRepository
import com.app.myinapp.domain.repository.MainScreenRepository
import com.app.myinapp.domain.repository.SearchScreenRepository
import org.koin.dsl.module

val RepositoryModule = module {

    single<MainScreenRepository> { MainScreenRepositoryImpl(get(), get(), get()) }
    single<SearchScreenRepository> { SearchScreenRepositoryImpl(get()) }
    single<ImagePreviewRepository> { ImagePreviewRepositoryImpl(get()) }
    factory { ImageListPagingSource(get()) }
    factory { VideoListPagingSource(get()) }
}