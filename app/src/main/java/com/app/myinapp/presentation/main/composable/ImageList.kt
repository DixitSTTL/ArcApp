package com.app.myinapp.presentation.main.composable

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.presentation.main.MainScreenInteract
import kotlinx.coroutines.Job

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ImageList(
    imageList: LazyPagingItems<PhotoDTO>,
    uiAction: (MainScreenInteract) -> Job,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
    ) {
        items(imageList.itemCount) { index ->
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(4.dp)
                    .sharedElement(
                        rememberSharedContentState(key = "${imageList[index]!!.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        zIndexInOverlay = 2F,
                    ),
                onClick = {
                    imageList[index]?.let {
                        uiAction.invoke(MainScreenInteract.navigateImagePreview(imageList[index]!!))

                    }
                }
            ){

                AsyncImage(
                    model = imageList[index]?.src?.portrait,
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