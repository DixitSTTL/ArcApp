package com.app.myinapp.domain.usecase

import androidx.paging.PagingData
import com.app.myinapp.data.model.ImageDTO
import com.app.myinapp.data.model.Photo
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.data.utils.ResponseResult
import com.app.myinapp.domain.repository.MainScreenRepository
import kotlinx.coroutines.flow.Flow

class UseCaseMainScreen(private val mainScreenRepository: MainScreenRepository) {

    suspend fun fetchImage(): ResponseResult<ImageDTO> {
        return mainScreenRepository.getImageList()
    }

    suspend fun fetchVideo(): ResponseResult<VideoDTO> {
        return mainScreenRepository.getVideoList()
    }

    suspend fun fetchFlowImage(): Flow<PagingData<Photo>> {
        return mainScreenRepository.getFlowImageList()
    }
}