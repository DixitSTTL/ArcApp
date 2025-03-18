package com.app.myinapp.domain.repository

import androidx.paging.PagingData
import com.app.myinapp.data.model.ImageListDTO
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.data.model.VideoListDTO
import com.app.myinapp.data.utils.ResponseResult
import com.app.myinapp.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface MainScreenRepository {
    suspend fun getImageList(): ResponseResult<ImageListDTO>
    suspend fun getFlowImageList(): Flow<PagingData<PhotoDTO>>
    suspend fun getFlowVideoList(): Flow<PagingData<VideoDTO>>
    suspend fun getFlowLikedImageList(): Flow<PagingData<Photo>>
    suspend fun getVideoList(): ResponseResult<VideoListDTO>
}