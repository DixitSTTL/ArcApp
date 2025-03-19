package com.app.myinapp.data.network

import io.ktor.http.HttpHeaders
import io.ktor.util.StringValues


object NetworkConstants {

    init {
        System.loadLibrary("securedata-lib")
    }

    private external fun getBaseURL(): String
    private external fun getAPIKEY(): String

    val BASE_URL = getBaseURL()

    //route
    val curated = "curated"
    val video = "videos/popular/"
    val searchImage = "search"
    val searchVideo = "videos/search"

    fun Headers(): StringValues {
        return StringValues.build {
            append(
                HttpHeaders.Authorization,
                getAPIKEY()
            )
        }
    }
}