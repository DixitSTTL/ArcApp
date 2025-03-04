package com.app.myinapp.data.repository.search.dataSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.data.network.NetworkClient

class SearchImageListPagingSource(
    private val networkClient: NetworkClient,
    private val searchText: String
) : PagingSource<Int, PhotoDTO>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDTO> {
        return try {
            val page = params.key ?: 1
            val response = networkClient.getSearchFlowImageList(page = page, query = searchText)
            Log.d("TAG", "load: " + response)

            LoadResult.Page(
                data = response.photos,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = page.plus(1),
            )
        } catch (e: Exception) {
            Log.d("TAG", "loadeee: " + e.message)

            LoadResult.Error(e)

        }


    }
}