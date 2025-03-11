package com.app.myinapp.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import com.app.myinapp.data.model.VideoDTO


@Composable
fun VideoList(videoDTOList: LazyPagingItems<VideoDTO>, onClick: (VideoDTO) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
    ) {
        itemsIndexed(videoDTOList.itemSnapshotList.items) { index, item ->
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(4.dp),
                onClick = {
                    onClick(item)
                }
            ){

                AsyncImage(
                    model = item.image,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

        }

        videoDTOList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator(color = Color.Black) }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            CircularProgressIndicator(
                                color = Color.Red,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}