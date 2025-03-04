package com.app.myinapp.presentation.imagePreview

import com.app.myinapp.data.model.PhotoDTO

sealed interface ImagePreviewInteract {
    class navigateCoreImagePreview(var data: PhotoDTO) : ImagePreviewInteract

}