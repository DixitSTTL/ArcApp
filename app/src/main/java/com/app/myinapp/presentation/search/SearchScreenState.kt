package com.app.myinapp.presentation.search

import androidx.paging.PagingData
import com.app.myinapp.data.model.Photo
import com.app.myinapp.data.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchScreenState(
    var isLoading: Boolean = true,
    var query: String = "",
    var videoFlowList: Flow<PagingData<Video>> = emptyFlow(),
    var imageFlowList: Flow<PagingData<Photo>> = emptyFlow(),
)