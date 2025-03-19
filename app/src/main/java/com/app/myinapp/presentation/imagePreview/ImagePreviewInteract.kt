package com.app.myinapp.presentation.imagePreview

import com.app.myinapp.domain.model.Photo

sealed interface ImagePreviewInteract {
    class navigateCoreImagePreview(var data: Photo,var index: String) : ImagePreviewInteract
    class navigateDialog(var data: Photo) : ImagePreviewInteract
    class shareImage() : ImagePreviewInteract
    class likeWallpaper() : ImagePreviewInteract
    class setWallpaper(var wallpaperType: WallpaperType) : ImagePreviewInteract
    class downloadWallpaper() : ImagePreviewInteract
    class dismissDialog() : ImagePreviewInteract

}