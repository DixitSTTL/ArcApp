package com.app.myinapp.domain.usecase

import com.app.myinapp.domain.model.Photo
import com.app.myinapp.domain.repository.ImagePreviewRepository
import com.app.myinapp.presentation.imagePreview.WallpaperType
import kotlinx.coroutines.flow.Flow

class UseCaseImagePreviewScreen(private val imagePreviewRepository: ImagePreviewRepository) {

    suspend fun setWallpaper(
        data: Photo,
        type: WallpaperType,
    ) {
        return imagePreviewRepository.setWallpaper(data, type)
    }

    suspend fun shareImage(data: Photo) {
        return imagePreviewRepository.shareImage(data)
    }

    suspend fun likeWallpaper(data: Photo) {
        return imagePreviewRepository.likeWallpaper(data)
    }

    suspend fun disLikeWallpaper(data: Photo) {
        return imagePreviewRepository.disLikeWallpaper(data)
    }

    suspend fun checkLiked(data: Photo): Flow<Photo> {
        return imagePreviewRepository.checkLiked(data)
    }
}