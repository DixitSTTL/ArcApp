package com.app.myinapp.presentation.search

import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.model.VideoDTO

sealed interface SearchScreenInteract {
    class navigateImagePreview(var data: PhotoDTO) : SearchScreenInteract
    class navigateVideoPreview(var videoDTO: VideoDTO) : SearchScreenInteract
    class searchList() : SearchScreenInteract
}