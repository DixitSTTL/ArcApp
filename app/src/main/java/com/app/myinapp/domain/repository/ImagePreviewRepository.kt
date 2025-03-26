package com.app.myinapp.domain.repository

import com.app.myinapp.domain.model.Photo
import com.app.myinapp.presentation.imagePreview.WallpaperType
import kotlinx.coroutines.flow.Flow

interface ImagePreviewRepository {
    suspend fun shareImage(photoDTO: Photo)
    suspend fun setWallpaper(
        photoDTO: Photo,
        type: WallpaperType
    )

    suspend fun downloadWallpaper(photoDTO: Photo)
    suspend fun likeWallpaper(photoDTO: Photo)
    suspend fun disLikeWallpaper(photoDTO: Photo)
    suspend fun checkLiked(photoDTO: Photo): Flow<Photo>
}