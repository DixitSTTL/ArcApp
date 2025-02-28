package com.app.myinapp.presentation

import kotlinx.serialization.Serializable

@Serializable
sealed class routes(val route: String) {

    @Serializable
    data object MAIN_SCREEN : routes("MAIN_SCREEN")

    @Serializable
    data object SEARCH_SCREEN : routes("IMAGE_PREVIEW_SCREEN")

    @Serializable
    data object IMAGE_PREVIEW_SCREEN : routes("IMAGE_PREVIEW_SCREEN/{Photo}")

    @Serializable
    data object CORE_IMAGE_PREVIEW_SCREEN : routes("IMAGE_PREVIEW_SCREEN")

    @Serializable
    data object VIDEO_PREVIEW_SCREEN : routes("IMAGE_PREVIEW_SCREEN")


}