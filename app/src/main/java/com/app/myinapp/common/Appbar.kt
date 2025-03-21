package com.app.myinapp.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.app.myinapp.presentation.ui.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onBackPress: () -> Unit, title: String, tintColor: Color, containerColor: Color, menuItems:@Composable () -> Unit) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
                color = tintColor,
                style = Theme.typography.headline
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPress() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = tintColor
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
            scrolledContainerColor = Theme.colors.primaryContainer,
        ),
        actions = {menuItems()}

    )
}