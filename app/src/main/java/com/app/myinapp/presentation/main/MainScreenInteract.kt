package com.app.myinapp.presentation.main

import com.app.myinapp.data.model.ImageDTO
import com.app.myinapp.data.model.Photo
import com.app.myinapp.data.model.Video

sealed interface MainScreenInteract{
   class navigateImagePreview(var data:Photo): MainScreenInteract
   class  navigateSearch : MainScreenInteract
   class  navigateVideoPreview(var video: Video) : MainScreenInteract

}