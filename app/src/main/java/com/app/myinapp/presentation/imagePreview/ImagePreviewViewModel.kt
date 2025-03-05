package com.app.myinapp.presentation.imagePreview

import androidx.lifecycle.viewModelScope
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.domain.usecase.UseCaseImagePreviewScreen
import com.app.myinapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImagePreviewViewModel(
    private val useCaseImagePreviewScreen: UseCaseImagePreviewScreen
) : BaseViewModel<ImagePreviewState, ImagePreviewInteract>(ImagePreviewState()) {

    fun shareImage(photo: PhotoDTO) {
        viewModelScope.launch {
            useCaseImagePreviewScreen.shareImage(photo)
        }
    }

    fun setWallpaper(photo: PhotoDTO, wallpaperType: WallpaperType) {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseImagePreviewScreen.setWallpaper(photo, wallpaperType)
            sendAction(ImagePreviewInteract.dismissDialog()) // Move dialog dismissal here

        }
    }

    fun downloadWallpaper(photo: PhotoDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseImagePreviewScreen.downloadImage(photo)
            sendAction(ImagePreviewInteract.dismissDialog()) // Move dialog dismissal here
        }
    }
}