package com.app.myinapp.domain.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SecondWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val person = inputData.getString("person")


        Log.d("TAG", "doWork: hello $person")

        return Result.success()
    }
}