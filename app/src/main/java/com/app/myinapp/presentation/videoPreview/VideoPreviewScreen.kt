package com.app.myinapp.presentation.videoPreview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.app.myinapp.R
import com.app.myinapp.common.AppBar
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.presentation.ui.theme.Theme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalSharedTransitionApi::class)
@UnstableApi
@Composable
fun SharedTransitionScope.VideoPreviewScreen(
    navController: NavHostController,
    data: VideoDTO,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: VideoScreenViewModel = koinViewModel(parameters = { parametersOf(data) })
) {

    val context = LocalContext.current
    val systemUiController: SystemUiController = rememberSystemUiController()
    // Initialize ExoPlayer
    val state by viewModel.state.collectAsState()
    val exoPlayer = remember { viewModel.exoplayer }
    systemUiController.isSystemBarsVisible = state.data.isVisible
    systemUiController.systemBarsBehavior = BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE


    DisposableEffect(key1 = data) {
        onDispose {
            systemUiController.isSystemBarsVisible = true
            systemUiController.systemBarsBehavior = BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    if (!exoPlayer.isPlaying)
//                    exoPlayer.play()
                        Log.d("LifecycleAwareComponent", "ON_RESUME triggered")
                    // Handle onResume logic here
                }

                Lifecycle.Event.ON_PAUSE -> {
                    if (exoPlayer.isPlaying)
                        exoPlayer.pause()
                    Log.d("LifecycleAwareComponent", "ON_PAUSE triggered")
                    // Handle onPause logic here
                }

                Lifecycle.Event.ON_STOP -> {
                    Log.d("LifecycleAwareComponent", "ON_STOP triggered")
                    // Handle onStop logic here
                }

                Lifecycle.Event.ON_DESTROY -> {
                    Log.d("LifecycleAwareComponent", "ON_DESTROY triggered")
                    // Handle onStop logic here
                }

                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        topBar = {
            if (state.data.isVisible) {
                AppBar(
                    onBackPress = {
                        navController.popBackStack()
                    },
                    title = data.id.toString(),
                    tintColor = Theme.colors.onBackground,
                    containerColor = Color.Transparent,
                    menuItems = {
                        IconButton({}) {
                            Icon(
                                painter = painterResource(R.drawable.ic_download),
                                contentDescription = "download_video",
                                tint = Theme.colors.onBackground
                            )
                        }
                    }
                )
            }
        }
    ) { padding ->
        Box(
            Modifier
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
                        showController()
                        setControllerVisibilityListener(PlayerView.ControllerVisibilityListener { visibility ->
                            viewModel.setDataState(

                                state.data.copy(isVisible = visibility == View.VISIBLE)
                            )
                        }
                        )
                    }
                })
        }
    }
}