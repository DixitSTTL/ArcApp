package com.app.myinapp.presentation.main

import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.domain.model.Photo

sealed interface MainScreenInteract {
    class navigateImagePreview(var data: Photo) : MainScreenInteract
    class navigateSearch(var data: String) : MainScreenInteract
    class navigateVideoPreview(var data: VideoDTO) : MainScreenInteract
    class navigateSetting() : MainScreenInteract

}