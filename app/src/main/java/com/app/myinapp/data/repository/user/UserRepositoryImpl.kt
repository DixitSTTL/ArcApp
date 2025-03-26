package com.app.myinapp.data.repository.user

import android.content.SharedPreferences
import android.util.Log
import com.app.myinapp.data.utils.SharedPrefManager
import com.app.myinapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow

class UserRepositoryImpl(
    private val sharedPrefManager: SharedPrefManager
) : UserRepository {
    private val isDynamic = MutableStateFlow(sharedPrefManager.getBoolean("IsDynamicTheme"))
    private val isDarkMode = MutableStateFlow(sharedPrefManager.getBoolean("UiTheme"))

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->

        Log.d("TAG", "IsDynamicTheme: ${key} ")

        when (key) {
            "IsDynamicTheme" -> {
                isDynamic.value = sharedPrefManager.getBoolean(key)
            }

            "UiTheme" -> {
                isDarkMode.value = sharedPrefManager.getBoolean(key)
            }

        }
    }

    init {
        sharedPrefManager.sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override suspend fun updateIsDynamicTheme(boolean: Boolean) {
        sharedPrefManager.setBoolean("IsDynamicTheme", boolean)
    }

    override suspend fun updateUiTheme(boolean: Boolean) {
        sharedPrefManager.setBoolean("UiTheme", boolean)
    }

    override suspend fun getIsDynamicTheme(): MutableStateFlow<Boolean> {
        return isDarkMode
    }

    override suspend fun getUiTheme(): MutableStateFlow<Boolean> {
        return isDynamic
    }
}