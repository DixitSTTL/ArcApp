package com.app.myinapp.presentation.imagePreview

import android.net.Uri
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.app.myinapp.R
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.presentation.imagePreview.composable.ActionButton
import com.app.myinapp.presentation.routes.CORE_IMAGE_PREVIEW_SCREEN
import com.app.myinapp.presentation.routes.OPTION_DIALOG

import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@Composable
fun ImagePreviewScreen(
    navController: NavHostController,
    data: PhotoDTO,
    viewModel: ImagePreviewViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.uiAction.collect() { it ->
            when (it) {
                is ImagePreviewInteract.navigateCoreImagePreview -> {
                    val data = Uri.encode(Gson().toJson(it.data))
                    navController.navigate("${CORE_IMAGE_PREVIEW_SCREEN}/${data}")
                }

                is ImagePreviewInteract.navigateDialog -> {
                    val data = Uri.encode(Gson().toJson(it.data))
                    navController.navigate("${OPTION_DIALOG}/${data}")
                }

                is ImagePreviewInteract.shareImage -> {
                    viewModel.shareImage(it.data)
                }

                is ImagePreviewInteract.setWallpaper -> {}
                is ImagePreviewInteract.downloadWallpaper ->{}
                is ImagePreviewInteract.dismissDialog -> {}
            }
        }

    }

    Scaffold { padding ->
        Box(Modifier.padding(padding)) {
            Column {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    AsyncImage(
                        model = data.src.portrait,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .clip(RoundedCornerShape(5)),
                        contentScale = ContentScale.Crop
                    )
                    IconButton(
                        onClick = {
                            viewModel.sendAction(ImagePreviewInteract.navigateCoreImagePreview(data))
                        },
                        modifier = Modifier
                            .padding(25.dp)
                            .size(22.dp)
                            .align(
                                Alignment.BottomEnd
                            )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_preview),
                            contentDescription = "",
                        )
                    }

                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ActionButton(onclick = {
                        viewModel.sendAction(ImagePreviewInteract.shareImage(data))
                    }, icon = R.drawable.ic_share)

                    ActionButton(onclick = {
                        viewModel.sendAction(ImagePreviewInteract.navigateDialog(data))
                    }, icon = R.drawable.ic_download)

                    ActionButton(onclick = {

                    }, icon = R.drawable.ic_like)
                }
            }
        }
    }
}