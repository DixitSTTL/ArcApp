package com.app.myinapp.presentation.search

import android.net.Uri
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.myinapp.presentation.routes
import com.app.myinapp.presentation.search.composable.AppBar
import com.app.myinapp.presentation.common.ImageList
import com.app.myinapp.presentation.common.VideoList
import com.app.myinapp.presentation.routes.VIDEO_PREVIEW_SCREEN
import com.app.myinapp.presentation.ui.theme.Theme
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SearchScreen(
    navController: NavHostController,
    searchType: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: SearchScreenViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()
    val stateImageFlow = state.imageFlowList.collectAsLazyPagingItems()
    val stateVideoFlow = state.videoDTOFlowList.collectAsLazyPagingItems()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val keyboardController = LocalSoftwareKeyboardController.current

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiAction.collect {
            when (it) {
                is SearchScreenInteract.navigateImagePreview -> {
                    val data = Uri.encode(Gson().toJson(it.data.toPhoto()))
                    navController.navigate("${routes.IMAGE_PREVIEW_SCREEN}/${data}/${it.index}")
                }

                is SearchScreenInteract.navigateVideoPreview -> {
                    val data = Uri.encode(Gson().toJson(it.data))
                    navController.navigate("$VIDEO_PREVIEW_SCREEN/${data}")
                }

                is SearchScreenInteract.searchList -> {
                    keyboardController?.hide()
                    if (state.query.isNotEmpty()){
                            if (searchType == "Images") {
                                viewModel.fetchFlowSearchImage()
                            } else {
                                viewModel.fetchFlowSearchVideo()
                            }
                    }
                    else{
                        snackBarHostState.showSnackbar("Please enter some text")
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                scrollBehavior,
                state,
                { navController.popBackStack() },
                viewModel::sendAction,
                viewModel::setDataState
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }

    ) { it ->
        Box(Modifier
            .padding(top = it.calculateTopPadding())
            .background(color = Theme.colors.background)) {

            if (searchType == "Images")
                ImageList(stateImageFlow, onClick = {it,index->
                    viewModel.sendAction(SearchScreenInteract.navigateImagePreview(it,index))
                }, animatedVisibilityScope)
            else
                VideoList(stateVideoFlow,{it
                    viewModel.sendAction(SearchScreenInteract.navigateVideoPreview(it))
                }, animatedVisibilityScope)
        }
    }

}