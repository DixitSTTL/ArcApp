package com.app.myinapp.presentation.app

import androidx.lifecycle.viewModelScope
import com.app.myinapp.domain.usecase.UseCaseTheme
import com.app.myinapp.presentation.base.StateViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AppViewModel(
    private val useCaseTheme: UseCaseTheme
) : StateViewModel<AppState>(AppState()) {

    init {
        fetchUiTheme()
        fetchDynamicTheme()

    }

    private fun fetchUiTheme() {
        viewModelScope.launch {
            useCaseTheme.getUiTheme().collectLatest { it ->

                setDataState(getStateData().copy(isDynamicUi = it))
            }

        }
    }

    private fun fetchDynamicTheme() {
        viewModelScope.launch {
            useCaseTheme.getDynamicTheme().collectLatest { it ->
                setDataState(getStateData().copy(isDarkMode = it))
            }
        }
    }


}