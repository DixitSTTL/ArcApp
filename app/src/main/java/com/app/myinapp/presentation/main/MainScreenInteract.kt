package com.app.myinapp.presentation.main

import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.model.VideoDTO

sealed interface MainScreenInteract {
    class navigateImagePreview(var data: PhotoDTO) : MainScreenInteract
    class navigateSearch(var data: String) : MainScreenInteract
    class navigateVideoPreview(var videoDTO: VideoDTO) : MainScreenInteract

}