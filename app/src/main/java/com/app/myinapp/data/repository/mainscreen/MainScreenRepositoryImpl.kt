package com.app.myinapp.data.repository.mainscreen

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.myinapp.data.model.ImageListDTO
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.data.model.VideoListDTO
import com.app.myinapp.data.network.NetworkClient
import com.app.myinapp.data.repository.mainscreen.dataSource.ImageListPagingSource
import com.app.myinapp.data.repository.mainscreen.dataSource.VideoListPagingSource
import com.app.myinapp.data.utils.ResponseResult
import com.app.myinapp.domain.repository.MainScreenRepository
import kotlinx.coroutines.flow.Flow

class MainScreenRepositoryImpl(
    private val networkClient: NetworkClient,
    private val imageListPagingSource: ImageListPagingSource,
    private val videoListPagingSource: VideoListPagingSource
) : MainScreenRepository {
    override suspend fun getImageList(): ResponseResult<ImageListDTO> {
        return networkClient.getImageList()
    }

    override suspend fun getFlowImageList(): Flow<PagingData<PhotoDTO>> = Pager(
        config = PagingConfig(20), pagingSourceFactory = {
            imageListPagingSource
        }
    ).flow

    override suspend fun getFlowVideoList(): Flow<PagingData<VideoDTO>> = Pager(
        config = PagingConfig(20), pagingSourceFactory = {
            videoListPagingSource
        }
    ).flow

    override suspend fun getVideoList(): ResponseResult<VideoListDTO> {
        return networkClient.getVideoList()
    }
}