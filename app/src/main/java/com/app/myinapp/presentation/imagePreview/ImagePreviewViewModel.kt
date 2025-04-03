package com.app.myinapp.presentation.imagePreview

import androidx.lifecycle.viewModelScope
import com.app.myinapp.domain.model.Photo
import com.app.myinapp.domain.usecase.UseCaseDownloadFile
import com.app.myinapp.domain.usecase.UseCaseImagePreviewScreen
import com.app.myinapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ImagePreviewViewModel(
    private val useCaseImagePreviewScreen: UseCaseImagePreviewScreen,
    private val useCaseDownloadFile: UseCaseDownloadFile,
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
            if (getStateData().isLiked){
                useCaseImagePreviewScreen.disLikeWallpaper(photo)
            }else{
                useCaseImagePreviewScreen.likeWallpaper(photo)
            }
        }
    }

    private fun checkLiked() {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseImagePreviewScreen.checkLiked(photo).collectLatest {it->
                if (it==null){
                    setDataState(getStateData().copy(isLiked = false))
                }
                else{
                    setDataState(getStateData().copy(isLiked = true))
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
            useCaseDownloadFile.downloadFile(
                url = photo.original,
                type = "JPG",
                name = "WallpaperS_${System.currentTimeMillis()}")
            sendAction(ImagePreviewInteract.dismissDialog()) // Move dialog dismissal here
        }
    }
}