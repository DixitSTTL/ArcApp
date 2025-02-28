package com.app.myinapp.domain.jobs

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyJobService : JobService() {

    override fun onStartJob(params: JobParameters): Boolean {
        // This is where you put the code to perform the background task

        // For this example, we'll just log a message
        Log.d("MyJobService", "Job started: " + Thread.currentThread())


        // Return true if the job needs to continue in a separate thread, false otherwise
        return false
    }

    override fun onStopJob(params: JobParameters): Boolean {
        // This is called if the job is interrupted, you can return true to reschedule the job
        return false
    }
}