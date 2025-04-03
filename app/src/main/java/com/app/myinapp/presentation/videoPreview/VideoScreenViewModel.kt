package com.app.myinapp.presentation.videoPreview

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.domain.usecase.UseCaseDownloadFile
import com.app.myinapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class VideoScreenViewModel(
    private val data: VideoDTO,
    private val context: Application,
    private val useCaseDownloadFile: UseCaseDownloadFile,
    ) : BaseViewModel<VideoScreenState, VideoScreenInteract>(VideoScreenState()) {

    val exoplayer = ExoPlayer.Builder(context).build()
    init {
        setMedia(data)
    }

    private fun setMedia(data: VideoDTO) {
        val mediaItems = arrayListOf<MediaItem>()
        mediaItems.add(
            MediaItem.Builder()
                .setUri(data.videoFiles[0].link)
                .setMediaId(data.id.toString())
                .setTag(data)
                .setMediaMetadata(MediaMetadata.Builder().setDisplayTitle(data.url).build())
                .build()
        )

        exoplayer.apply {
            setMediaItems(mediaItems)
            prepare()
            playWhenReady= true
            addListener(object : Player.Listener {
                override fun onEvents(player: Player, events: Player.Events) {
                    super.onEvents(player, events)
                    // Hide video title after playing for 200 milliseconds
//                    if (player.contentPosition >= 200) setDataState(state.value.copy(isVisible = false))

                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    super.onMediaItemTransition(mediaItem, reason)
                    // Callback when the video changes
//                    onVideoChange(this@apply.currentPeriodIndex)
//                    setDataState(state.value.copy(isVisible = true))
//                    videoTitle.value = mediaItem?.mediaMetadata?.displayTitle.toString()
                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    super.onPlaybackStateChanged(playbackState)
                    // Callback when the video playback state changes to STATE_ENDED
                    if (playbackState == ExoPlayer.STATE_ENDED) {
//                        isVideoEnded.invoke(true)
                    }
                }
            })
        }
    }

    fun downloadVideo(videoDTO: VideoDTO){
        viewModelScope.launch {
            useCaseDownloadFile.downloadFile(
                url = videoDTO.videoFiles[0].link,
                name = "video_${System.currentTimeMillis()}",
                type = "MP4"
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        exoplayer.release()
    }
}