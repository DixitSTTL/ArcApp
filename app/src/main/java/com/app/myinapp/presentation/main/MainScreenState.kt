package com.app.myinapp.presentation.main

import androidx.paging.PagingData
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.model.VideoDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MainScreenState(
    var isLoading: Boolean = true,
    var imageList: List<PhotoDTO> = emptyList(),
    var videoDTOList: List<VideoDTO> = emptyList(),
    var videoDTOFlowList: Flow<PagingData<VideoDTO>> = emptyFlow(),
    var imageFlowList: Flow<PagingData<PhotoDTO>> = emptyFlow(),
)
