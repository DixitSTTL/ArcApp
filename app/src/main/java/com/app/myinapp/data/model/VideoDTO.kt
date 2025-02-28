package com.app.myinapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDTO(
    @SerialName("next_page")
    val nextPage: String,
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total_results")
    val totalResults: Int,
    @SerialName("url")
    val url: String,
    @SerialName("videos")
    val videos: List<Video>
)

@Serializable
data class Video(
    @SerialName("avg_color")
    val avgColor: String?,
    @SerialName("duration")
    val duration: Int,
    @SerialName("full_res")
    val fullRes: String?,
    @SerialName("height")
    val height: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("url")
    val url: String,
    @SerialName("user")
    val user: User,
    @SerialName("video_files")
    val videoFiles: List<VideoFile>,
    @SerialName("video_pictures")
    val videoPictures: List<VideoPicture>,
    @SerialName("width")
    val width: Int
)

@Parcelize
@Serializable
data class User(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
) : Parcelable

@Serializable
data class VideoFile(
    @SerialName("file_type")
    val fileType: String,
    @SerialName("fps")
    val fps: Double,
    @SerialName("height")
    val height: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("link")
    val link: String,
    @SerialName("quality")
    val quality: String,
    @SerialName("size")
    val size: Int,
    @SerialName("width")
    val width: Int
)

@Serializable
data class VideoPicture(
    @SerialName("id")
    val id: Int,
    @SerialName("nr")
    val nr: Int,
    @SerialName("picture")
    val picture: String
)




