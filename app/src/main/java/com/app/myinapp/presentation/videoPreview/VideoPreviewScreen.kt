package com.app.myinapp.presentation.videoPreview

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.presentation.ui.theme.Theme
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@UnstableApi
@Composable
fun SharedTransitionScope.VideoPreviewScreen(
    navController: NavHostController,
    data: VideoDTO,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: VideoScreenViewModel = koinViewModel()
) {

    val context = LocalContext.current
    // Initialize ExoPlayer
    val exoPlayer = remember { viewModel.exoplayer }

    LaunchedEffect(key1 = data) {
        if (exoPlayer.currentMediaItem == null) {
            delay(300) //
            viewModel.setMedia(data)
        }
    }
//    DisposableEffect(Unit) {
//        onDispose {
//
//            Log.d("TAG", "VideoPreviewScreen:ff "+exoPlayer)
//            exoPlayer.release() // Release ExoPlayer when leaving the screen
//        }
//    }

//    Seek to the specified index and start playing
//    exoPlayer.seekTo(playingIndex.value, C.TIME_UNSET)


    Scaffold { it ->
        Box(
            Modifier
                .padding(it)
                .background(color = Theme.colors.background)
        ) {
            AndroidView(
                modifier = Modifier
                    .testTag("VIDEO_PLAYER_TAG")
                    .sharedElement(
                        rememberSharedContentState(key = "${data.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                factory = {
                    // AndroidView to embed a PlayerView into Compose
                    PlayerView(context).apply {
                        player = exoPlayer
                        layoutParams = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        // Set resize mode to fill the available space
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        // Hide unnecessary player controls
                        setShowNextButton(false)
                        setShowPreviousButton(false)
                        setShowFastForwardButton(true)
                        setShowRewindButton(true)
                        setFullscreenButtonState(true)
                    }
                })
        }
    }
}