package com.app.myinapp.domain.repository

import androidx.paging.PagingData
import com.app.myinapp.data.model.ImageDTO
import com.app.myinapp.data.model.Photo
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.data.utils.ResponseResult
import kotlinx.coroutines.flow.Flow

interface MainScreenRepository {
    suspend fun getImageList(): ResponseResult<ImageDTO>
    suspend fun getFlowImageList(): Flow<PagingData<Photo>>
    suspend fun getVideoList(): ResponseResult<VideoDTO>
}