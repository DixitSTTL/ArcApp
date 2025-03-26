package com.app.myinapp.presentation.main

import android.app.Application
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.app.myinapp.domain.jobs.MyJobService
import com.app.myinapp.domain.usecase.UseCaseMainScreen
import com.app.myinapp.domain.workmanager.FirstWorker
import com.app.myinapp.domain.workmanager.SecondWorker
import com.app.myinapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class MainScreenViewModel(
    private var useCaseMainScreen: UseCaseMainScreen,
    private var myApp: Application
) : BaseViewModel<MainScreenState, MainScreenInteract>(MainScreenState()) {

    init {
//        fetchFlowImage()
//        fetchFlowVideo()
//        fetchFlowLikedImage()
        fetchCommon()
    }

    private fun fetchCommon() {
        viewModelScope.launch(Dispatchers.IO) {
            setDataState(getStateData().copy(isLoading = true))
            val responseFlowVideo =
                async {
                    useCaseMainScreen.fetchFlowVideo().cachedIn(viewModelScope) }
            val responseFlowImage =
                async {
                    useCaseMainScreen.fetchFlowImage().cachedIn(viewModelScope) }
            val responseFlowLikedImage =
                async { useCaseMainScreen.fetchFlowLikedImage().cachedIn(viewModelScope) }
            setDataState(
                getStateData().copy(
                    isLoading = false,
                    videoDTOFlowList = responseFlowVideo.await(),
                    imageFlowList = responseFlowImage.await(),
                    likedImageFlowList = responseFlowLikedImage.await()
                )
            )
        }
    }

    private fun fetchFlowVideo() {
        viewModelScope.launch {
            setDataState(getStateData().copy(isLoading = true))
            val responseFlow = useCaseMainScreen.fetchFlowVideo().cachedIn(viewModelScope)
            setDataState(getStateData().copy(isLoading = false, videoDTOFlowList = responseFlow))
        }
    }

    private fun fetchFlowImage() {
        viewModelScope.launch {
            setDataState(getStateData().copy(isLoading = true))
            val responseFlow = useCaseMainScreen.fetchFlowImage().cachedIn(viewModelScope)
            setDataState(getStateData().copy(isLoading = false, imageFlowList = responseFlow))

        }
    }

    private fun fetchFlowLikedImage() {
        viewModelScope.launch(Dispatchers.IO) {
            val responseFlow = useCaseMainScreen.fetchFlowLikedImage().cachedIn(viewModelScope)
            withContext(Dispatchers.Main) {
                setDataState(
                    getStateData().copy(
                        isLoading = false,
                        likedImageFlowList = responseFlow
                    )
                )
            }
        }
    }

    fun startWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.METERED)
//            .setRequiresBatteryNotLow(true)
//            .setRequiresStorageNotLow(true)
//            .setRequiresCharging(true)
            .build()

        val firstRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<FirstWorker>()
                .setInitialDelay(15, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build()

        val secondRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<SecondWorker>()
                .setInitialDelay(3, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build()


        WorkManager.getInstance(myApp).enqueue(firstRequest)
    }


    fun startJob() {

        val componentName = ComponentName(myApp, MyJobService::class.java)
        val jobInfo = JobInfo.Builder(1, componentName)
//            .setRequiresCharging(true) // You can set any criteria here
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .build()

        val jobScheduler = myApp.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }

}