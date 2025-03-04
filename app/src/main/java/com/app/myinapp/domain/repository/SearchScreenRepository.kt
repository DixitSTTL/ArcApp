package com.app.myinapp.domain.repository

import androidx.paging.PagingData
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.model.VideoDTO
import kotlinx.coroutines.flow.Flow

interface SearchScreenRepository {
    suspend fun getFlowSearchImageList(searchText: String): Flow<PagingData<PhotoDTO>>
    suspend fun getFlowSearchVideoList(searchText: String): Flow<PagingData<VideoDTO>>
}