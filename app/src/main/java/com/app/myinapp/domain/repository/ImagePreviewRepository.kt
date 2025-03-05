package com.app.myinapp.domain.repository

import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.presentation.imagePreview.WallpaperType

interface ImagePreviewRepository {
    suspend fun shareImage(photoDTO: PhotoDTO)
    suspend fun setWallpaper(
        photoDTO: PhotoDTO,
        type: WallpaperType)
    suspend fun downloadWallpaper(photoDTO: PhotoDTO)
}