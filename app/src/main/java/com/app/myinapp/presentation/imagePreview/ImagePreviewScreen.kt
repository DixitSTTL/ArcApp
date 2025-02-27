package com.app.myinapp.presentation.imagePreview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.app.myinapp.R
import com.app.myinapp.data.model.Photo
import com.app.myinapp.presentation.imagePreview.composable.ActionButton

@Composable
fun ImagePreviewScreen(navController: NavHostController, data: Photo) {


    Scaffold { padding ->
        Box(Modifier.padding(padding)) {
            Column {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    AsyncImage(
                        model = data.src.large2x,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .clip(RoundedCornerShape(5)),
                        contentScale = ContentScale.Crop
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_preview),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(25.dp)
                            .size(22.dp)
                            .align(
                                Alignment.BottomEnd
                            )
                    )

                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ActionButton(onclick = {}, icon = R.drawable.ic_share)
                    ActionButton(onclick = {}, icon = R.drawable.ic_download)
                    ActionButton(onclick = {}, icon = R.drawable.ic_like)
                }
            }
        }
    }
}