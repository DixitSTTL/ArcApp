package com.app.myinapp.domain.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class FirstWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {


        Log.d("TAG", "doWork: hello")
        val outputData = Data.Builder()
            .putString("person", "Dixit")
            .build()


        return Result.success(outputData)
    }
}