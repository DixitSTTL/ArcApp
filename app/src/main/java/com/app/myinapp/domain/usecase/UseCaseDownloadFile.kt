package com.app.myinapp.domain.usecase

import com.app.myinapp.domain.repository.DownloadRepository

class UseCaseDownloadFile(private val downloadRepository: DownloadRepository) {

    suspend fun downloadFile(url: String, type: String, name: String) {
        return downloadRepository.downloadFile(url,type,name)
    }

}