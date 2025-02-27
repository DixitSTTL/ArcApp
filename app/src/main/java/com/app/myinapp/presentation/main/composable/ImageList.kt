package com.app.myinapp.presentation.main.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import com.app.myinapp.data.model.Photo
import com.app.myinapp.presentation.main.MainScreenInteract
import kotlinx.coroutines.Job

@Composable
fun ImageList(imageList: LazyPagingItems<Photo>, uiAction: (MainScreenInteract) -> Job) {

    LazyVerticalGrid(modifier = Modifier
        .fillMaxSize(),
        columns = GridCells.Fixed(3),) {
        items(imageList.itemCount) { index ->
            AsyncImage(
                model = imageList[index]?.src?.portrait,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable {
                        imageList[index]?.let {
                            uiAction.invoke(MainScreenInteract.navigateImagePreview(imageList[index]!!))

                        }
                    }
                    .padding(2.dp)
                    .clip(RoundedCornerShape(5)),
                contentScale = ContentScale.Crop
            )
        }
        imageList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator(color = Color.Black) }
                }

//                loadState.refresh is LoadState.Error -> {
//                    val error = moviePagingItems.loadState.refresh as LoadState.Error
//                    item {
//                        ErrorMessage(
//                            modifier = Modifier.fillParentMaxSize(),
//                            message = error.error.localizedMessage!!,
//                            onClickRetry = { retry() })
//                    }
//                }
//
//                loadState.append is LoadState.Loading -> {
//                    item { LoadingNextPageItem(modifier = Modifier) }
//                }
//
//                loadState.append is LoadState.Error -> {
//                    val error = moviePagingItems.loadState.append as LoadState.Error
//                    item {
//                        ErrorMessage(
//                            modifier = Modifier,
//                            message = error.error.localizedMessage!!,
//                            onClickRetry = { retry() })
//                    }
//                }
            }
        }
    }


}