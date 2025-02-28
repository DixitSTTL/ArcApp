package com.app.myinapp.data.repository.mainscreen

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.myinapp.data.model.ImageDTO
import com.app.myinapp.data.model.Photo
import com.app.myinapp.data.model.Video
import com.app.myinapp.data.model.VideoDTO
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
    override suspend fun getImageList(): ResponseResult<ImageDTO> {
        return networkClient.getImageList()
    }

    override suspend fun getFlowImageList(): Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(20), pagingSourceFactory = {
            imageListPagingSource
        }
    ).flow

    override suspend fun getFlowVideoList(): Flow<PagingData<Video>> = Pager(
        config = PagingConfig(20), pagingSourceFactory = {
            videoListPagingSource
        }
    ).flow

    override suspend fun getVideoList(): ResponseResult<VideoDTO> {
        return networkClient.getVideoList()
    }
}