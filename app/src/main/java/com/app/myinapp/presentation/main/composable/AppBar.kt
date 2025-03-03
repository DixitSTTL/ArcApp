package com.app.myinapp.presentation.main.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.app.myinapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onClick: () -> Unit,
) {

    LargeTopAppBar(
        title = {
            Text("WallpaperS")
        },
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton(onClick = { onClick() }) {
                Icon(painterResource(R.drawable.ic_search), "")
            }
        })

}