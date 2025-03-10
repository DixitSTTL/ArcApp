package com.app.myinapp.presentation.app

import com.app.myinapp.domain.usecase.UseCaseTheme
import com.app.myinapp.presentation.base.StateViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
        CoroutineScope(Dispatchers.IO).launch {
            useCaseTheme.getUiTheme().collectLatest {it->
                setDataState(state.value.copy(isDynamicUi = it))
            }

        }
    }

    private fun fetchDynamicTheme() {
        CoroutineScope(Dispatchers.IO).launch {
            useCaseTheme.getDynamicTheme().collectLatest {it->
                setDataState(state.value.copy(isDarkMode = it))
            }
        }
    }


}