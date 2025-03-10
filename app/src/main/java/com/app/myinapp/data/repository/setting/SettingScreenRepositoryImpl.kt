package com.app.myinapp.data.repository.setting

import com.app.myinapp.data.utils.SharedPrefManager
import com.app.myinapp.domain.repository.SettingScreenRepository

class SettingScreenRepositoryImpl(private val sharedPrefManager: SharedPrefManager) :
    SettingScreenRepository {
    override suspend fun updateIsDynamicTheme(boolean: Boolean) {
        sharedPrefManager.setString("IsDynamicTheme", boolean.toString())
    }

    override suspend fun updateUiTheme(boolean: Boolean) {
        sharedPrefManager.setString("UiTheme", boolean.toString())
    }

}