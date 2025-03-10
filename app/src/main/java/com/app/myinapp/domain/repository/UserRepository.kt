package com.app.myinapp.domain.repository


import kotlinx.coroutines.flow.MutableStateFlow

interface UserRepository {
    suspend fun updateIsDynamicTheme(boolean: Boolean)
    suspend fun updateUiTheme(boolean: Boolean)

    suspend fun getUiTheme(): MutableStateFlow<Boolean>
    suspend fun getIsDynamicTheme(): MutableStateFlow<Boolean>
}