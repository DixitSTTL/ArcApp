package com.app.myinapp.presentation

import kotlinx.serialization.Serializable

@Serializable
sealed class routes(val route: String) {

    @Serializable
    data object MAIN_SCREEN : routes("MAIN_SCREEN")

    @Serializable
    data object SEARCH_SCREEN : routes("SEARCH_SCREEN/{Data}")

    @Serializable
    data object IMAGE_PREVIEW_SCREEN : routes("IMAGE_PREVIEW_SCREEN/{Photo}/{Index}")

    @Serializable
    data object CORE_IMAGE_PREVIEW_SCREEN : routes("CORE_IMAGE_PREVIEW_SCREEN/{Photo}/{Index}")

    @Serializable
    data object VIDEO_PREVIEW_SCREEN : routes("VIDEO_PREVIEW_SCREEN/{Video}")

    @Serializable
    data object OPTION_DIALOG : routes("OPTION_DIALOG/{Photo}")

    @Serializable
    data object SETTING_SCREEN : routes("SETTING_SCREEN")


}