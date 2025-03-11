package com.app.myinapp.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.app.myinapp.presentation.ui.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onBackPress:()->Unit,title: String) {

    CenterAlignedTopAppBar(
        title = { Text(title, color = Theme.colors.onPrimaryContainer, style = Theme.typography.headline) },
        navigationIcon = {
            IconButton(onClick = {onBackPress()}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Theme.colors.onPrimaryContainer
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Theme.colors.primaryContainer,
            scrolledContainerColor = Theme.colors.primaryContainer,
        )

    )
}