package com.app.myinapp.presentation.videoPreview

sealed interface VideoScreenInteract {
    class update() : VideoScreenInteract
}