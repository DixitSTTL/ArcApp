package com.app.myinapp.domain.usecase

import androidx.paging.PagingData
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.domain.repository.SearchScreenRepository
import kotlinx.coroutines.flow.Flow

class UseCaseSearchScreen(private val searchScreenRepository: SearchScreenRepository) {

    suspend fun fetchFlowSearchImage(searchText: String): Flow<PagingData<PhotoDTO>> {
        return searchScreenRepository.getFlowSearchImageList(searchText)
    }

    suspend fun fetchFlowSearchVideo(searchText: String): Flow<PagingData<VideoDTO>> {
        return searchScreenRepository.getFlowSearchVideoList(searchText)
    }
}