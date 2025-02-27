package com.app.myinapp.presentation.imagePreview

import com.app.myinapp.data.model.ImageDTO
import com.app.myinapp.data.model.Photo

data class ImagePreviewState(
    var isLoading: Boolean = true,
    var imageList: List<Photo> = emptyList(),
)
