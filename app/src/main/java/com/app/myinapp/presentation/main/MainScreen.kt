package com.app.myinapp.presentation.main

import android.net.Uri
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.myinapp.common.ImageList
import com.app.myinapp.common.LikedImageList
import com.app.myinapp.common.TabBar
import com.app.myinapp.common.VideoList
import com.app.myinapp.presentation.main.composable.AppBar
import com.app.myinapp.presentation.routes
import com.app.myinapp.presentation.routes.IMAGE_PREVIEW_SCREEN
import com.app.myinapp.presentation.routes.SEARCH_SCREEN
import com.app.myinapp.presentation.routes.VIDEO_PREVIEW_SCREEN
import com.app.myinapp.presentation.ui.theme.Theme
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MainScreen(
    navController: NavHostController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: MainScreenViewModel = koinViewModel()
) {

    val pagerState = rememberPagerState(pageCount = { 3 })
    val titles = listOf("Images", "Videos", "Liked")
    val state by viewModel.state.collectAsState()
    val stateImageFlow = state.data.imageFlowList.collectAsLazyPagingItems()
    val stateVideoFlow = state.data.videoDTOFlowList.collectAsLazyPagingItems()
    val stateLikedImageFlow = state.data.likedImageFlowList.collectAsLazyPagingItems()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())


    LaunchedEffect(Unit) {
        viewModel.uiAction.collectLatest {
            when (it) {
                is MainScreenInteract.navigateImagePreview -> {
                    val data = Uri.encode(Gson().toJson(it.data))
                    navController.navigate("${IMAGE_PREVIEW_SCREEN}/${data}/${it.index}")
                }

                is MainScreenInteract.navigateSearch -> {
                    val data = Uri.encode(Gson().toJson(it.data))
                    navController.navigate("${SEARCH_SCREEN}/${data}")
                }

                is MainScreenInteract.navigateVideoPreview -> {
                    val data = Uri.encode(Gson().toJson(it.data))
                    navController.navigate("${VIDEO_PREVIEW_SCREEN}/${data}")
                }

                is MainScreenInteract.navigateSetting -> {
                    navController.navigate(routes.SETTING_SCREEN) { launchSingleTop = true }
                }
            }
        }

    }
    Scaffold(
        topBar = {
            AppBar(
                scrollBehavior,
                navigateSearch = {
                    viewModel.sendAction(MainScreenInteract.navigateSearch(titles[pagerState.currentPage]))
                },
                navigateSetting = {
                    viewModel.sendAction(MainScreenInteract.navigateSetting())
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .background(color = Theme.colors.background)
        )
        {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TabBar(pagerState,titles)

                HorizontalPager(
                    state = pagerState,
                    pageSpacing = 12.dp,

                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    when (it) {

                        0 -> {
                            ImageList(stateImageFlow, onClick = { it, index ->
                                viewModel.sendAction(
                                    MainScreenInteract.navigateImagePreview(
                                        it.toPhoto(),
                                        index
                                    )
                                )
                            }, animatedVisibilityScope)
                        }

                        1 -> {
                            VideoList(stateVideoFlow, onClick = {it
                                viewModel.sendAction(MainScreenInteract.navigateVideoPreview(it))
                            }, animatedVisibilityScope)
                        }

                        2 -> {
                            LikedImageList(stateLikedImageFlow, onClick = { it, index ->
                                viewModel.sendAction(
                                    MainScreenInteract.navigateImagePreview(
                                        it,
                                        index
                                    )
                                )
                            }, animatedVisibilityScope)
                        }
                    }
                }
            }
        }
    }

}