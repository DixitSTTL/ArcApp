package com.app.myinapp.data.repository.download

import android.app.Application
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.app.myinapp.domain.repository.DownloadRepository
import com.app.myinapp.domain.workmanager.FileParams.KEY_FILE_NAME
import com.app.myinapp.domain.workmanager.FileParams.KEY_FILE_TYPE
import com.app.myinapp.domain.workmanager.FileParams.KEY_FILE_URL
import com.app.myinapp.domain.workmanager.FirstWorker

class DownloadRepositoryImpl(
    private val androidApplication: Application,
    ) : DownloadRepository {


    override suspend fun downloadFile(url: String, type: String, name: String) {
        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()
//        val data = workDataOf("image_url" to photoDTO.original)
        val data = workDataOf(
            KEY_FILE_URL to url,
            KEY_FILE_TYPE to type,
            KEY_FILE_NAME to name,
        )
        val firstRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<FirstWorker>()
                .setConstraints(constraints)
                .setInputData(data)
                .build()

        WorkManager.getInstance(androidApplication).enqueue(firstRequest)
    }

}