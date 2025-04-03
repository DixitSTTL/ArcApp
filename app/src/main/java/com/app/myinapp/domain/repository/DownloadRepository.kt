package com.app.myinapp.domain.repository

interface DownloadRepository {
    suspend fun downloadFile(url: String, type: String, name: String)

}