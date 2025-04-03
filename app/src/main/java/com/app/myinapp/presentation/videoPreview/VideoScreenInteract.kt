package com.app.myinapp.presentation.videoPreview

import com.app.myinapp.data.model.VideoDTO

sealed interface VideoScreenInteract {
    class update() : VideoScreenInteract
    class downloadVideo(val data: VideoDTO) : VideoScreenInteract
}