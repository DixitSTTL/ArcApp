package com.app.myinapp.presentation.search

import androidx.paging.PagingData
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.model.VideoDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchScreenState(
    var isLoading: Boolean = true,
    var query: String = "",
    var videoDTOFlowList: Flow<PagingData<VideoDTO>> = emptyFlow(),
    var imageFlowList: Flow<PagingData<PhotoDTO>> = emptyFlow(),
)