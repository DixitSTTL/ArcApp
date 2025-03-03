package com.app.myinapp.presentation.search

import com.app.myinapp.data.model.Photo
import com.app.myinapp.data.model.Video

sealed interface SearchScreenInteract {
    class navigateImagePreview(var data: Photo) : SearchScreenInteract
    class navigateVideoPreview(var video: Video) : SearchScreenInteract
    class searchList() : SearchScreenInteract
}