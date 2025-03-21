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
            setDataState(getStateData().copy(isLoading = true))
            val responseFlow =
                useCaseSearchScreen.fetchFlowSearchImage(getStateData().query).cachedIn(viewModelScope)
            setDataState(getStateData().copy(isLoading = false, imageFlowList = responseFlow))
        }
    }

    fun fetchFlowSearchVideo() {
        viewModelScope.launch {
            setDataState(getStateData().copy(isLoading = true))
            val responseFlow =
                useCaseSearchScreen.fetchFlowSearchVideo(getStateData().query).cachedIn(viewModelScope)
            setDataState(getStateData().copy(isLoading = false, videoDTOFlowList = responseFlow))
        }
    }
}