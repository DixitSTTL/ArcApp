package com.app.myinapp.data.repository.search.dataSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.data.network.NetworkClient

class SearchVideoListPagingSource(
    private val networkClient: NetworkClient,
    private val searchText: String
) : PagingSource<Int, VideoDTO>() {
    override fun getRefreshKey(state: PagingState<Int, VideoDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoDTO> {
        return try {
            val page = params.key ?: 2
            val response = networkClient.getSearchFlowVideoList(page = page, query = searchText)
            Log.d("TAG", "load: " + response)

            LoadResult.Page(
                data = response.videoDTOS,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = page.plus(1),
            )
        } catch (e: Exception) {
            Log.d("TAG", "loadeee: " + e.message)

            LoadResult.Error(e)

        }

    }
}