package com.app.myinapp.data.repository.mainscreen.dataSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.myinapp.data.model.Video
import com.app.myinapp.data.network.NetworkClient

class VideoListPagingSource(private val networkClient: NetworkClient) : PagingSource<Int, Video>() {
    override fun getRefreshKey(state: PagingState<Int, Video>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Video> {
        return try {
            val page = params.key ?: 2
            val response = networkClient.getFlowVideoList(page = page)
            Log.d("TAG", "load: " + response)

            LoadResult.Page(
                data = response.videos,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = page.plus(1),
            )
        } catch (e: Exception) {
            Log.d("TAG", "loadeee: " + e.message)
            LoadResult.Error(e)
        }
    }
}