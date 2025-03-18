package com.app.myinapp.domain.usecase

import androidx.paging.PagingData
import com.app.myinapp.data.model.ImageListDTO
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.data.model.VideoListDTO
import com.app.myinapp.data.utils.ResponseResult
import com.app.myinapp.domain.model.Photo
import com.app.myinapp.domain.repository.MainScreenRepository
import kotlinx.coroutines.flow.Flow

class UseCaseMainScreen(private val mainScreenRepository: MainScreenRepository) {

    suspend fun fetchImage(): ResponseResult<ImageListDTO> {
        return mainScreenRepository.getImageList()
    }

    suspend fun fetchVideo(): ResponseResult<VideoListDTO> {
        return mainScreenRepository.getVideoList()
    }

    suspend fun fetchFlowImage(): Flow<PagingData<PhotoDTO>> {
        return mainScreenRepository.getFlowImageList()
    }

    suspend fun fetchFlowVideo(): Flow<PagingData<VideoDTO>> {
        return mainScreenRepository.getFlowVideoList()
    }

    suspend fun fetchFlowLikedImage(): Flow<PagingData<Photo>> {
        return mainScreenRepository.getFlowLikedImageList()
    }
}