package com.app.myinapp.domain.workmanager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class FirstWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        val imageUrl = inputData.getString("image_url") ?: return Result.failure()

        Log.d("TAG", "doWork: hello")
        val outputData = Data.Builder()
            .putString("person", "Dixit")
            .build()
        val fileName = "WallpaperS_${System.currentTimeMillis()}.jpg"


        return try {
            val bitmap = downloadImage(imageUrl)
            saveImageToStorage(applicationContext, bitmap, fileName)
            Result.success()
        } catch (e: Exception) {
            Log.e("DownloadImageWorker", "Download failed: ${e.message}")
            Result.retry()
        }
    }

    private fun downloadImage(url: String): Bitmap {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.connect()
        val inputStream = connection.inputStream
        return BitmapFactory.decodeStream(inputStream)
    }

    private fun saveImageToStorage(context: Context, bitmap: Bitmap, fileName: String) {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        Log.d("DownloadImageWorker", "Image saved at: ${file.absolutePath}")
    }
}