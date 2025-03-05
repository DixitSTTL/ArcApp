package com.app.myinapp.presentation.imagePreview

import com.app.myinapp.data.model.PhotoDTO

sealed interface ImagePreviewInteract {
    class navigateCoreImagePreview(var data: PhotoDTO) : ImagePreviewInteract
    class navigateDialog(var data: PhotoDTO) : ImagePreviewInteract
    class shareImage(var data: PhotoDTO) : ImagePreviewInteract
    class setWallpaper(var data: PhotoDTO, var wallpaperType: WallpaperType) : ImagePreviewInteract
    class downloadWallpaper(var data: PhotoDTO) : ImagePreviewInteract
    class dismissDialog() : ImagePreviewInteract

}