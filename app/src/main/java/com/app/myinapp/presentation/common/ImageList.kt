package com.app.myinapp.presentation.common

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.app.myinapp.data.model.PhotoDTO

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ImageList(
    imageList: LazyPagingItems<PhotoDTO>,
    onClick: (PhotoDTO,String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
    ) {
        items(imageList.itemCount, key ={item -> item} ) { index ->
            val item = imageList[index]?:return@items // This ensures pagination works correctly

            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(4.dp)
                    .sharedElement(
                        rememberSharedContentState(key = "${item.id}_${index}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        zIndexInOverlay = 2F,
                    ),
                onClick = {
                    imageList[index]?.let {
                        onClick(item,index.toString())

                    }
                }
            ){

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageList[index]?.src?.portrait)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
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
                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            CircularProgressIndicator(
                                color = Color.Red,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }
                }
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