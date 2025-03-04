package com.app.myinapp.presentation.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.app.myinapp.R
import com.app.myinapp.data.model.PhotoDTO
import com.app.myinapp.presentation.dialog.composable.DialogOptions
import com.app.myinapp.presentation.imagePreview.ImagePreviewInteract
import com.app.myinapp.presentation.imagePreview.ImagePreviewViewModel
import com.app.myinapp.presentation.imagePreview.WallpaperType
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionDialog(
    navController: NavHostController,
    data: PhotoDTO,
    viewModel: ImagePreviewViewModel = koinViewModel()
) {
    val state = rememberModalBottomSheetState(true)
    LaunchedEffect(Unit) {
        viewModel.uiAction.collect() {
            when (it) {
                is ImagePreviewInteract.navigateCoreImagePreview -> {}
                is ImagePreviewInteract.navigateDialog -> {}
                is ImagePreviewInteract.shareImage -> {}
                is ImagePreviewInteract.setWallpaper -> {
                    viewModel.setWallpaper(it.data,it.wallpaperType)
                }
            }
        }
    }

    ModalBottomSheet(sheetState = state, onDismissRequest = { navController.popBackStack() }) {

        Column {
            DialogOptions(icon = R.drawable.ic_download, str = "Download", onClick = {})
            DialogOptions(
                icon = R.drawable.ic_download,
                str = "Set lock screen",
                onClick = { viewModel.sendAction(ImagePreviewInteract.setWallpaper(data,WallpaperType.LOCKSCREEN)) })
            DialogOptions(
                icon = R.drawable.ic_download,
                str = "Set Dashboard",
                onClick = { viewModel.sendAction(ImagePreviewInteract.setWallpaper(data,WallpaperType.DASHBOARDSCREEN)) })

        }

    }

}