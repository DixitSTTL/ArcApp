package com.app.myinapp.presentation.imagePreview

import com.app.myinapp.data.model.Photo

sealed interface ImagePreviewInteract {
    class navigateCoreImagePreview(var data: Photo) : ImagePreviewInteract

}