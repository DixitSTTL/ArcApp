package com.app.myinapp.data.model

import com.app.myinapp.domain.model.Photo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageListDTO(
    @SerialName("next_page")
    val nextPage: String,
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("photos")
    val photos: List<PhotoDTO>,
    @SerialName("total_results")
    val totalResults: Int
)

@Serializable
data class PhotoDTO(
    @SerialName("alt")
    val alt: String,
    @SerialName("avg_color")
    val avgColor: String,
    @SerialName("height")
    val height: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("liked")
    val liked: Boolean,
    @SerialName("photographer")
    val photographer: String,
    @SerialName("photographer_id")
    val photographerId: Long,
    @SerialName("photographer_url")
    val photographerUrl: String,
    @SerialName("src")
    val src: Src,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int
) {
    fun toPhoto() = Photo(
        0,
        this.id,
        this.src.original,
        this.src.portrait
    )
}

@Serializable
data class Src(
    @SerialName("landscape")
    val landscape: String,
    @SerialName("large")
    val large: String,
    @SerialName("large2x")
    val large2x: String,
    @SerialName("medium")
    val medium: String,
    @SerialName("original")
    val original: String,
    @SerialName("portrait")
    val portrait: String,
    @SerialName("small")
    val small: String,
    @SerialName("tiny")
    val tiny: String
)




