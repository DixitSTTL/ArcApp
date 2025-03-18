package com.app.myinapp.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "LikedImages", indices = [Index(value = ["imageId"], unique = true)])
data class Photo(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    val imageId: Int,
    val original: String,
    val portrait: String
    )