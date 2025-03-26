package com.app.myinapp.presentation.base

import androidx.lifecycle.ViewModel
import com.app.myinapp.data.utils.StateClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class StateViewModel<S>(initialState: S): ViewModel() {

    private val _state: MutableStateFlow<StateClass<S>> by lazy { MutableStateFlow(StateClass( isLoading = false, data = initialState)) }
    val state: StateFlow<StateClass<S>> by lazy { _state.asStateFlow() }

    fun setDataState(data: S) {
        _state.update { state.value.copy(
            data = data
        ) }
    }

    private fun getState(): StateClass<S> {
        return state.value
    }

    fun getStateData(): S {
        return getState().data
    }

}