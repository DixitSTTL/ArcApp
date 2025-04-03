package com.app.myinapp.data.repository.imagePreview

import android.app.Application
import android.app.WallpaperManager
import android.content.Intent
import com.app.myinapp.domain.model.Photo
import com.app.myinapp.domain.repository.ImagePreviewRepository
import com.app.myinapp.domain.room.DatabaseHelper
import com.app.myinapp.presentation.imagePreview.WallpaperType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL

class ImagePreviewRepositoryImpl(
    private val androidApplication: Application,
    private val databaseHelper: DatabaseHelper,

    ) : ImagePreviewRepository {
    override suspend fun shareImage(photoDTO: Photo) {
        val txtIntent = Intent(Intent.ACTION_SEND)
        txtIntent.type = "text/plain"
        txtIntent.putExtra(Intent.EXTRA_TEXT, photoDTO.original)
        androidApplication.startActivity(Intent.createChooser(txtIntent, "Share").apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    override suspend fun setWallpaper(
        photoDTO: Photo,
        wallpaperType: WallpaperType
    ) {
        val wallpaperManager = WallpaperManager.getInstance(androidApplication)

        val inputStream: InputStream =
            withContext(Dispatchers.IO) {
                URL(photoDTO.original).openStream()
            }
        when (wallpaperType) {
            WallpaperType.DASHBOARDSCREEN -> {
                wallpaperManager.setStream(
                    inputStream, null, true, WallpaperManager.FLAG_SYSTEM
                )
            }

            WallpaperType.LOCKSCREEN -> {
                wallpaperManager.setStream(
                    inputStream, null, true, WallpaperManager.FLAG_LOCK
                )
            }

            WallpaperType.BOTHSCREEN -> {
                wallpaperManager.setStream(
                    inputStream, null, true, WallpaperManager.FLAG_SYSTEM
                )
                wallpaperManager.setStream(
                    inputStream, null, true, WallpaperManager.FLAG_LOCK
                )
            }
        }

    }

    override suspend fun likeWallpaper(photoDTO: Photo) {
        databaseHelper.daoLiked().insert(photoDTO)
    }

    override suspend fun disLikeWallpaper(photoDTO: Photo) {
        databaseHelper.daoLiked().delete(photoDTO.imageId)
    }

    override suspend fun checkLiked(photoDTO: Photo): Flow<Photo> {
        return databaseHelper.daoLiked().checkLiked(photoDTO.imageId)
    }

}