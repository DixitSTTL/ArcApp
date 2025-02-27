package com.app.myinapp.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.app.myinapp.data.utils.ResponseResult
import com.app.myinapp.domain.usecase.UseCaseMainScreen
import com.app.myinapp.domain.workmanager.FirstWorker
import com.app.myinapp.domain.workmanager.SecondWorker
import com.app.myinapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainScreenViewModel(
    private var useCaseMainScreen: UseCaseMainScreen,
    private var myApp: Application
) : BaseViewModel<MainScreenState, MainScreenInteract>(MainScreenState()) {


    fun fetchImage() {
        viewModelScope.launch {
            setDataState(state.value.copy(isLoading = true))
            val response = useCaseMainScreen.fetchImage()

            when (response) {
                is ResponseResult.Error -> {
                    Log.d("Error", response.message)
                    setDataState(state.value.copy(isLoading = false))

                }

                is ResponseResult.Success -> {

                    response.data?.photos?.let {
                        it
                        setDataState(state.value.copy(isLoading = false, imageList = it))


                    }
                }
            }
        }

    }

    fun fetchVideo() {
        viewModelScope.launch {

            val response = useCaseMainScreen.fetchVideo()

            when (response) {
                is ResponseResult.Error -> {
                    Log.d("Error", response.message)
                    setDataState(state.value.copy(isLoading = false))

                }

                is ResponseResult.Success -> {

                    response.data?.videos?.let {
                        it
                        setDataState(state.value.copy(isLoading = false, videoList = it))

                    }

                }


            }
        }

    }

    fun fetchFlowImage() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.METERED)
//            .setRequiresBatteryNotLow(true)
//            .setRequiresStorageNotLow(true)
//            .setRequiresCharging(true)
            .build()

        val firstRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<FirstWorker>()
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build()

        val secondRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<SecondWorker>()
                .setInitialDelay(3, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build()
        Log.d("TAG", "fetchFlowImage: $myApp")


        WorkManager.getInstance(myApp).enqueue(firstRequest)


//        viewModelScope.launch {
//            setDataState(state.value.copy(isLoading = true))
//            val responseFlow = useCaseMainScreen.fetchFlowImage()
//            setDataState(state.value.copy(isLoading = false, imageFlowList = responseFlow))
//        }
    }

}