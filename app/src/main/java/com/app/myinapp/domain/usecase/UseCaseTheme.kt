package com.app.myinapp.domain.usecase

import com.app.myinapp.domain.repository.UserRepository


class UseCaseTheme(private val userRepository: UserRepository) {

    suspend fun switchDynamicTheme(mode: Boolean) {
        userRepository.updateIsDynamicTheme(mode)
    }

    suspend fun switchUiTheme(mode: Boolean) {
        userRepository.updateUiTheme(mode)
    }

    suspend fun getDynamicTheme() = userRepository.getIsDynamicTheme()
    suspend fun getUiTheme() = userRepository.getUiTheme()

}