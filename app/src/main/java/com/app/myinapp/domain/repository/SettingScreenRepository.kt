package com.app.myinapp.domain.repository

interface SettingScreenRepository {
    suspend fun updateIsDynamicTheme(boolean: Boolean)
    suspend fun updateUiTheme(boolean: Boolean)
}