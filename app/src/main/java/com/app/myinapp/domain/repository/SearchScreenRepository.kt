package com.app.myinapp.domain.repository

import androidx.paging.PagingData
import com.app.myinapp.data.model.Photo
import kotlinx.coroutines.flow.Flow

interface SearchScreenRepository {
    suspend fun getFlowSearchImageList(searchText: String): Flow<PagingData<Photo>>
}