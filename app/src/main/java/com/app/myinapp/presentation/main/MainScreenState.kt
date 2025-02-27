package com.app.myinapp.presentation.main

import androidx.paging.PagingData
import com.app.myinapp.data.model.Photo
import com.app.myinapp.data.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MainScreenState(
    var isLoading: Boolean = true,
    var imageList: List<Photo> = emptyList(),
    var videoList: List<Video> = emptyList(),
    var imageFlowList: Flow<PagingData<Photo>> = emptyFlow(),
)
