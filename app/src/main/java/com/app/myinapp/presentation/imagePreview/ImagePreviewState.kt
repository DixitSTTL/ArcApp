package com.app.myinapp.presentation.imagePreview

import com.app.myinapp.data.model.PhotoDTO

data class ImagePreviewState(
    var isLoading: Boolean = true,
    var isLiked: Boolean = false,
    var imageList: List<PhotoDTO> = emptyList(),
)
