package com.app.myinapp.data.network

import io.ktor.http.HttpHeaders
import io.ktor.util.StringValues


object NetworkConstants {

    val BASE_URL = "api.pexels.com/v1"

    //route
    val curated = "curated"
    val video = "videos/popular/"

    fun Headers(): StringValues {
        return StringValues.build {
            append(
                HttpHeaders.Authorization,
                "x1rqvPKlrWj15hUunRzWy6T9WXLpJrXGFVcWi7fxaH1rb96FmSaVWKed"
            )
        }
    }
}