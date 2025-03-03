package com.app.myinapp.presentation.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.app.myinapp.domain.usecase.UseCaseSearchScreen
import com.app.myinapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private var useCaseSearchScreen: UseCaseSearchScreen,
) : BaseViewModel<SearchScreenState, SearchScreenInteract>(SearchScreenState()) {

    fun fetchFlowSearchImage() {
        viewModelScope.launch {
            setDataState(state.value.copy(isLoading = true))
            val responseFlow =
                useCaseSearchScreen.fetchFlowSearchImage(state.value.query).cachedIn(viewModelScope)
            setDataState(state.value.copy(isLoading = false, imageFlowList = responseFlow))
        }
    }
}