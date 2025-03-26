package com.app.myinapp.presentation.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.app.myinapp.R
import com.app.myinapp.domain.model.Photo
import com.app.myinapp.presentation.dialog.composable.DialogOptions
import com.app.myinapp.presentation.imagePreview.ImagePreviewInteract
import com.app.myinapp.presentation.imagePreview.ImagePreviewViewModel
import com.app.myinapp.presentation.imagePreview.WallpaperType
import com.app.myinapp.presentation.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionDialog(
    navController: NavHostController,
    data: Photo,
    viewModel: ImagePreviewViewModel = koinViewModel(parameters = { parametersOf(data) })
) {
    val state = rememberModalBottomSheetState(true)
    LaunchedEffect(Unit) {
        viewModel.uiAction.collect() {
            when (it) {
                is ImagePreviewInteract.navigateCoreImagePreview -> {}
                is ImagePreviewInteract.navigateDialog -> {}
                is ImagePreviewInteract.shareImage -> {}
                is ImagePreviewInteract.setWallpaper -> {
                    viewModel.setWallpaper(it.wallpaperType)
                }

                is ImagePreviewInteract.downloadWallpaper -> {
                    viewModel.downloadWallpaper()
                }

                is ImagePreviewInteract.dismissDialog -> {
                    navController.popBackStack()
                }

                is ImagePreviewInteract.likeWallpaper -> {}
            }
        }
    }

    ModalBottomSheet(sheetState = state,
        onDismissRequest = { navController.popBackStack() },
        containerColor = Theme.colors.primaryContainer,
        dragHandle = {}) {

        Column {
            DialogOptions(icon = R.drawable.ic_image, str = "Set Dashboard", onClick = {
                viewModel.sendAction(
                    ImagePreviewInteract.setWallpaper(
                        WallpaperType.DASHBOARDSCREEN
                    )
                )
            })
            DialogOptions(icon = R.drawable.ic_lock, str = "Set lock screen", onClick = {
                viewModel.sendAction(
                    ImagePreviewInteract.setWallpaper(
                        WallpaperType.LOCKSCREEN
                    )
                )
            })
            DialogOptions(icon = R.drawable.ic_both, str = "Set Both", onClick = {
                viewModel.sendAction(
                    ImagePreviewInteract.setWallpaper(
                        WallpaperType.BOTHSCREEN
                    )
                )
            })
            DialogOptions(icon = R.drawable.ic_download,
                str = "Save to files",
                onClick = { viewModel.sendAction(ImagePreviewInteract.downloadWallpaper()) })

        }

    }

}