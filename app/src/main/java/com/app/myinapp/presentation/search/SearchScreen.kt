package com.app.myinapp.presentation.search

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.myinapp.presentation.routes
import com.app.myinapp.presentation.search.composable.AppBar
import com.app.myinapp.presentation.search.composable.ImageList
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchScreenViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()
    val stateImageFlow = state.imageFlowList.collectAsLazyPagingItems()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val keyboardController = LocalSoftwareKeyboardController.current


    LaunchedEffect(Unit) {
        viewModel.uiAction.collect {
            when (it) {
                is SearchScreenInteract.navigateImagePreview -> {
                    val data = Uri.encode(Gson().toJson(it.data))
                    navController.navigate("${routes.IMAGE_PREVIEW_SCREEN}/${data}")
                }

                is SearchScreenInteract.navigateVideoPreview -> {
                    navController.navigate(routes.IMAGE_PREVIEW_SCREEN)
                }

                is SearchScreenInteract.searchList -> {
                    keyboardController?.hide()
                    viewModel.fetchFlowSearchImage()
                }
            }
        }
    }

    Scaffold(
        topBar = { AppBar(scrollBehavior, state, viewModel::sendAction, viewModel::setDataState) }
    ) { it ->
        Box(Modifier.padding(it)) {

            ImageList(stateImageFlow, viewModel::sendAction)
        }
    }

}