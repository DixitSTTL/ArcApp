package com.app.myinapp.presentation.main

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.myinapp.presentation.main.composable.ImageList
import com.app.myinapp.presentation.main.composable.VideoList
import com.app.myinapp.presentation.routes.IMAGE_PREVIEW_SCREEN
import com.app.myinapp.presentation.routes.SEARCH_SCREEN
import com.app.myinapp.presentation.routes.VIDEO_PREVIEW_SCREEN
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainScreenViewModel = koinViewModel()) {

    val pagerState = rememberPagerState(pageCount = { 2 })
    val titles = listOf("Images", "Videos")
    val state by viewModel.state.collectAsState()
    val stateImageFlow = state.imageFlowList.collectAsLazyPagingItems()
    val stateVideoFlow = state.videoFlowList.collectAsLazyPagingItems()
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.uiAction.collect {
            when (it) {
                is MainScreenInteract.navigateImagePreview -> {
                    val data = Uri.encode(Gson().toJson(it.data))
                    navController.navigate("${IMAGE_PREVIEW_SCREEN}/${data}")
                }

                is MainScreenInteract.navigateSearch -> {
                    navController.navigate(SEARCH_SCREEN)
                }

                is MainScreenInteract.navigateVideoPreview -> {
                    navController.navigate(VIDEO_PREVIEW_SCREEN) { launchSingleTop = true }
                }
            }
        }

    }

    Scaffold {
        it

        Box(Modifier.padding(it)) {
            if (state.isLoading) {
                Box(Modifier.fillMaxSize()) {

                    CircularProgressIndicator(
                        Modifier.align(Alignment.Center),
                        color = Color.Yellow
                    )
                }
            } else {
                Column(
                    modifier = Modifier

                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        divider = {

                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        indicator = {
                            TabRowDefaults.Indicator(
                                modifier = Modifier
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topEnd = 20.dp,
                                            topStart = 20.dp
                                        )
                                    )
                                    .tabIndicatorOffset(it[pagerState.currentPage]),
                                color = Color.Cyan,
                                height = 3.dp
                            )
                        },
                        containerColor = Color.Transparent,
                        contentColor = Color.Black,
                    ) {

                        titles.forEachIndexed { index, title ->
                            Tab(
                                text = {
                                    Text(
                                        text = title,
                                        color = Color.Black,
                                        /*  style = TextStyle(
                                              fontFamily = FontFamily(Font(R.font.alegreya_bold)),
                                              fontSize = 16.sp
                                          )*/
                                    )

                                },
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                            )
                        }
                    }

                    HorizontalPager(
                        state = pagerState,
                        pageSpacing = 12.dp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        when (it) {

                            0 -> {
                                ImageList(stateImageFlow, viewModel::sendAction)
                            }

                            1 -> {
                                VideoList(stateVideoFlow, viewModel::sendAction)

                            }

                        }

                    }
                }
            }
        }


    }

}