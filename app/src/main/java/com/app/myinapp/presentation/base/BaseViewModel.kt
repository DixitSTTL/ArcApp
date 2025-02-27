package com.app.myinapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E>(initialState: S):ViewModel() {

    private var _state :MutableStateFlow<S> = MutableStateFlow(initialState)

    val state:StateFlow<S> =_state

    fun setDataState(data: S) {
        _state.update {data}
    }

    private val _uiAction: MutableSharedFlow<E> by lazy {
        MutableSharedFlow()
    }

    val uiAction: SharedFlow<E> by lazy {
        _uiAction.asSharedFlow()
    }

    fun sendAction(action: E) = viewModelScope.launch(Dispatchers.Main) {
        _uiAction.emit(action)
    }

}