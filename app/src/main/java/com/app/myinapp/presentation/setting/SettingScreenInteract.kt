package com.app.myinapp.presentation.setting

sealed interface SettingScreenInteract {
    class updateUiTheme(val status:Boolean) : SettingScreenInteract
    class updateDynamicTheme(val status:Boolean) : SettingScreenInteract
}