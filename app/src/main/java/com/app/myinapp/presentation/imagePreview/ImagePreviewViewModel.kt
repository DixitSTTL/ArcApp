package com.app.myinapp.presentation.imagePreview

import android.app.Application
import android.app.WallpaperManager
import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL

class ImagePreviewViewModel(
    private val androidApplication: Application
) : BaseViewModel<ImagePreviewState, ImagePreviewInteract>(ImagePreviewState()) {
    private val wallpaperManager = WallpaperManager.getInstance(androidApplication)

    fun shareImage(photo: PhotoDTO) {
        val txtIntent = Intent(Intent.ACTION_SEND)
        txtIntent.type = "text/plain"
        txtIntent.putExtra(Intent.EXTRA_TEXT, photo.src.original)
        androidApplication.startActivity(Intent.createChooser(txtIntent, "Share").apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })

    }

    fun setWallpaper(photo: PhotoDTO, wallpaperType: WallpaperType) {
        viewModelScope.launch(Dispatchers.IO) {
            val inputStream: InputStream = URL(photo.src.original).openStream()
            val type = if(wallpaperType==WallpaperType.LOCKSCREEN) WallpaperManager.FLAG_LOCK else WallpaperManager.FLAG_SYSTEM
            wallpaperManager.setStream(
                inputStream, null, true, type
            )
        }
    }
}