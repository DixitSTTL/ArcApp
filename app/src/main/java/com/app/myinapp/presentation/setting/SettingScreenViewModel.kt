package com.app.myinapp.presentation.setting

import androidx.lifecycle.viewModelScope
import com.app.myinapp.domain.usecase.UseCaseTheme
import com.app.myinapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingScreenViewModel(
    private var useCaseTheme: UseCaseTheme,
) : BaseViewModel<SettingScreenState, SettingScreenInteract>(SettingScreenState()) {

    init {
        fetchUiTheme()
        fetchDynamicTheme()

    }

    private fun fetchUiTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseTheme.getUiTheme().collectLatest {it->
                setDataState(state.value.copy(isDynamicUi = it))
            }
        }
    }

    private fun fetchDynamicTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseTheme.getDynamicTheme().collectLatest {it->
                setDataState(state.value.copy(isDarkMode = it))
            }
        }
    }

    fun setDynamicUI(status: Boolean) {
        viewModelScope.launch {
            useCaseTheme.switchDynamicTheme(status)
        }
    }

    fun setUITheme(status: Boolean) {
        viewModelScope.launch {
            useCaseTheme.switchUiTheme(status)
        }
    }
}