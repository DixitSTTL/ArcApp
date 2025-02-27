package com.app.myinapp.presentation.main.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.app.myinapp.data.model.Video
import com.app.myinapp.presentation.main.MainScreenInteract
import kotlinx.coroutines.Job


@Composable
fun VideoList(videoList: List<Video>, uiAction: (MainScreenInteract) -> Job){
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
    ) {
        items(videoList) { item ->

            AsyncImage(
                model = item.image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        uiAction.invoke(MainScreenInteract.navigateVideoPreview(item))
                    }
                    .padding(2.dp)
                    .clip(RoundedCornerShape(5)),
                contentScale = ContentScale.Crop
            )

        }
    }
}