package com.app.myinapp.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.myinapp.data.model.Photo
import com.app.myinapp.data.network.NetworkClient
import com.app.myinapp.data.repository.search.dataSource.SearchImageListPagingSource
import com.app.myinapp.domain.repository.SearchScreenRepository
import kotlinx.coroutines.flow.Flow

class SearchScreenRepositoryImpl(private val networkClient: NetworkClient) :
    SearchScreenRepository {

    private lateinit var searchImageListPagingSource: SearchImageListPagingSource

    override suspend fun getFlowSearchImageList(searchText: String): Flow<PagingData<Photo>> =
        Pager(
            config = PagingConfig(20), pagingSourceFactory = {
                searchImageListPagingSource = SearchImageListPagingSource(networkClient, searchText)
                searchImageListPagingSource
            }
        ).flow
}