package com.app.myinapp.presentation.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E>(initialState: S) :
    StateViewModel<S>(initialState = initialState) {


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