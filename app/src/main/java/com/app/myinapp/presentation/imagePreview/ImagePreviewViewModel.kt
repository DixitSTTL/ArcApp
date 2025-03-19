package com.app.myinapp.presentation.imagePreview

import androidx.lifecycle.viewModelScope
import com.app.myinapp.domain.model.Photo
import com.app.myinapp.domain.usecase.UseCaseImagePreviewScreen
import com.app.myinapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ImagePreviewViewModel(
    private val useCaseImagePreviewScreen: UseCaseImagePreviewScreen,
    private val photo: Photo
) : BaseViewModel<ImagePreviewState, ImagePreviewInteract>(ImagePreviewState()) {

    init {
        checkLiked()
    }

    fun shareImage() {
        viewModelScope.launch {
            useCaseImagePreviewScreen.shareImage(photo)
        }
    }

    fun likeWallpaper() {
        viewModelScope.launch(Dispatchers.IO) {
            if (state.value.isLiked) {
                useCaseImagePreviewScreen.disLikeWallpaper(photo)
            } else {
                useCaseImagePreviewScreen.likeWallpaper(photo)
            }
        }
    }

    private fun checkLiked() {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseImagePreviewScreen.checkLiked(photo).collectLatest { it ->
                if (it == null) {
                    setDataState(state.value.copy(isLiked = false))
                } else {
                    setDataState(state.value.copy(isLiked = true))
                }
            }
        }
    }

    fun setWallpaper(wallpaperType: WallpaperType) {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseImagePreviewScreen.setWallpaper(photo, wallpaperType)
            sendAction(ImagePreviewInteract.dismissDialog()) // Move dialog dismissal here

        }
    }

    fun downloadWallpaper() {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseImagePreviewScreen.downloadImage(photo)
            sendAction(ImagePreviewInteract.dismissDialog()) // Move dialog dismissal here
        }
    }
}