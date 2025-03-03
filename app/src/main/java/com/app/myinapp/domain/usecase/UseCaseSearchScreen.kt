package com.app.myinapp.domain.usecase

import androidx.paging.PagingData
import com.app.myinapp.data.model.Photo
import com.app.myinapp.domain.repository.SearchScreenRepository
import kotlinx.coroutines.flow.Flow

class UseCaseSearchScreen(private val searchScreenRepository: SearchScreenRepository) {

    suspend fun fetchFlowSearchImage(searchText: String): Flow<PagingData<Photo>> {
        return searchScreenRepository.getFlowSearchImageList(searchText)
    }
}