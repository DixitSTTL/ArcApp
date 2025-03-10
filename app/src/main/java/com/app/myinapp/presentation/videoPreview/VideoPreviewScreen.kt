package com.app.myinapp.presentation.videoPreview

import android.util.Log
import androidx.compose.foundation.AndroidExternalSurface
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavHostController
import com.app.myinapp.data.model.VideoDTO

@Composable
fun VideoPreviewScreen(navController: NavHostController, data: VideoDTO) {
    val context = LocalContext.current
    Log.d("TAG", "VideoPreviewScreen: ${data.url}")
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            // Configure the player
            // here I'm making the video loop
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            playWhenReady = true

            setMediaItem(MediaItem.fromUri(data.videoFiles[0].link))
            prepare()
        }
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        VideoSurface(modifier = Modifier.fillMaxWidth().height(data.height.dp), exoPlayer = exoPlayer)
    }
//    PlayerControls(exoPlayer)
}

@Composable
fun VideoSurface(modifier: Modifier = Modifier, exoPlayer: ExoPlayer) {
    AndroidExternalSurface(
        modifier = modifier,
        onInit = {
            onSurface { surface, _, _ ->
                exoPlayer.setVideoSurface(surface)
                surface.onDestroyed { exoPlayer.setVideoSurface(null) }
            }
        }
    )
}

@Composable
fun PlayerControls(player: ExoPlayer?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { player?.playWhenReady = true }) {
            Text("Play")
        }
        Button(onClick = { player?.playWhenReady = false }) {
            Text("Pause")
        }

        Button(onClick = {
            player?.seekTo(player.currentPosition - 10_000) // Seek backward 10 seconds
        }) {
            Text("Seek -10s")
        }
        Button(onClick = {
            player?.seekTo(player.currentPosition + 10_000) // Seek forward 10 seconds
        }) {
            Text("Seek +10s")
        }
    }
}