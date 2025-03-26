package com.app.myinapp.common

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.myinapp.presentation.ui.theme.Theme
import kotlinx.coroutines.launch

@Composable
fun ColumnScope.TabBar(pagerState: PagerState, titles: List<String>) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {

        },
        modifier = Modifier
            .fillMaxWidth(),
        indicator = {
            SecondaryIndicator(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topEnd = 20.dp,
                            topStart = 20.dp
                        )
                    )
                    .tabIndicatorOffset(it[pagerState.currentPage]),
                height = 3.dp,
                color = Theme.colors.secondary
            )
        },
        containerColor = Theme.colors.onSecondary,
        contentColor = Color.Black,
    ) {

        titles.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(
                        text = title,
                        color = Theme.colors.secondary,
                        style = Theme.typography.titleMedium
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
}