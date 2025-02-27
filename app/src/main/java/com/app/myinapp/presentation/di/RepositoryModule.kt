package com.app.myinapp.presentation.di

import com.app.myinapp.data.repository.mainscreen.MainScreenRepositoryImpl
import com.app.myinapp.data.repository.mainscreen.dataSource.ImageListPagingSource
import com.app.myinapp.domain.repository.MainScreenRepository
import org.koin.core.scope.get
import org.koin.dsl.module

val RepositoryModule = module{

    single<MainScreenRepository> { MainScreenRepositoryImpl(get(),get()) }
    factory{ ImageListPagingSource(get()) }
}