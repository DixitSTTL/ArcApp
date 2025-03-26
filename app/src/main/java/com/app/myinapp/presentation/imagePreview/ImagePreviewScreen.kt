package com.app.myinapp.presentation.imagePreview

import android.net.Uri
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.app.myinapp.R
import com.app.myinapp.domain.model.Photo
import com.app.myinapp.presentation.imagePreview.composable.ActionButton
import com.app.myinapp.presentation.routes.CORE_IMAGE_PREVIEW_SCREEN
import com.app.myinapp.presentation.routes.OPTION_DIALOG
import com.app.myinapp.presentation.ui.theme.Theme
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ImagePreviewScreen(
    navController: NavHostController,
    data: Photo,
    index: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: ImagePreviewViewModel = koinViewModel(parameters = { parametersOf(data) })
) {

    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.uiAction.collect() { it ->
            when (it) {
                is ImagePreviewInteract.navigateCoreImagePreview -> {
                    val data = Uri.encode(Gson().toJson(it.data))
                    navController.navigate("${CORE_IMAGE_PREVIEW_SCREEN}/${data}/${index}")
                }

                is ImagePreviewInteract.navigateDialog -> {
                    val data = Uri.encode(Gson().toJson(it.data))
                    navController.navigate("${OPTION_DIALOG}/${data}")
                }

                is ImagePreviewInteract.shareImage -> {
                    viewModel.shareImage()
                }
                is ImagePreviewInteract.likeWallpaper -> {
                    viewModel.likeWallpaper()
                }

                is ImagePreviewInteract.setWallpaper -> {}
                is ImagePreviewInteract.downloadWallpaper -> {}
                is ImagePreviewInteract.dismissDialog -> {}
            }
        }

    }

    Scaffold { padding ->
        Box(Modifier.background(color = Theme.colors.background)) {
            Column(Modifier.padding(padding)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .sharedElement(
                                rememberSharedContentState(key = "${data.imageId}_${index}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                zIndexInOverlay = 2F,
                            )
                    ) {

                        AsyncImage(
                            model = data.portrait,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.sendAction(ImagePreviewInteract.navigateCoreImagePreview(data,index))
                        },
                        modifier = Modifier
                            .padding(22.dp)
                            .size(28.dp)
                            .align(
                                Alignment.BottomEnd
                            ),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Theme.colors.primaryContainer
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_preview),
                            contentDescription = "",
                            tint = Theme.colors.onPrimaryContainer,
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
                        viewModel.sendAction(ImagePreviewInteract.shareImage())
                    }, icon = R.drawable.ic_share)

                    ActionButton(onclick = {
                        viewModel.sendAction(ImagePreviewInteract.navigateDialog(data))
                    }, icon = R.drawable.ic_download)

                    ActionButton(onclick = {
                        viewModel.sendAction(ImagePreviewInteract.likeWallpaper())
                    }, icon = if (state.data.isLiked)R.drawable.ic_liked else R.drawable.ic_like)
                }
            }
        }
    }
}