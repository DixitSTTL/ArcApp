package com.app.myinapp.domain.usecase

import com.app.myinapp.domain.repository.SettingScreenRepository

class UseCaseSettingScreen(private val settingScreenRepository: SettingScreenRepository) {

    suspend fun updateIsDynamicTheme(boolean: Boolean) {
        return settingScreenRepository.updateIsDynamicTheme(boolean)
    }

    suspend fun updateUiTheme(boolean: Boolean) {
        return settingScreenRepository.updateUiTheme(boolean)
    }
}