package com.app.myinapp.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.data.network.NetworkClient
import com.app.myinapp.data.repository.search.dataSource.SearchImageListPagingSource
import com.app.myinapp.data.repository.search.dataSource.SearchVideoListPagingSource
import com.app.myinapp.domain.repository.SearchScreenRepository
import kotlinx.coroutines.flow.Flow

class SearchScreenRepositoryImpl(private val networkClient: NetworkClient) :
    SearchScreenRepository {


    override suspend fun getFlowSearchImageList(searchText: String): Flow<PagingData<PhotoDTO>> =
        Pager(
            config = PagingConfig(20), pagingSourceFactory = {
                SearchImageListPagingSource(networkClient, searchText)
            }
        ).flow

    override suspend fun getFlowSearchVideoList(searchText: String): Flow<PagingData<VideoDTO>> =
        Pager(
            config = PagingConfig(20), pagingSourceFactory = {
                SearchVideoListPagingSource(networkClient, searchText)
            }
        ).flow
}