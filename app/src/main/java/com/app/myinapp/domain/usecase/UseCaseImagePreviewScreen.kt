package com.app.myinapp.domain.usecase

import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.domain.repository.ImagePreviewRepository
import com.app.myinapp.presentation.imagePreview.WallpaperType

class UseCaseImagePreviewScreen(private val imagePreviewRepository: ImagePreviewRepository) {

    suspend fun downloadImage(data: PhotoDTO) {
        return imagePreviewRepository.downloadWallpaper(data)
    }

    suspend fun setWallpaper(
        data: PhotoDTO,
        type: WallpaperType,
    ) {
        return imagePreviewRepository.setWallpaper(data, type)
    }

    suspend fun shareImage(data: PhotoDTO) {
        return imagePreviewRepository.shareImage(data)
    }
}