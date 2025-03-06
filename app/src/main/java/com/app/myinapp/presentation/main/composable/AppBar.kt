package com.app.myinapp.presentation.main.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.app.myinapp.R
import com.app.myinapp.presentation.ui.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onSwitchStateChange: (Boolean) -> Unit,
    onClick: () -> Unit,
    dynamic: Boolean
) {

    LargeTopAppBar(
        title = {
            Text("WallpaperS", color = Theme.colors.onPrimaryContainer)
        },
        scrollBehavior = scrollBehavior,
        actions = {
            Switch(
                checked = dynamic,
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Theme.colors.onPrimaryContainer,
                    checkedThumbColor = Theme.colors.primaryContainer,
                ),
                onCheckedChange = { status ->
                    onSwitchStateChange(status)
                })

            IconButton(onClick = { onClick() }) {
                Icon(
                    painterResource(R.drawable.ic_search),
                    "",
                    tint = Theme.colors.onPrimaryContainer
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Theme.colors.primaryContainer,
            scrolledContainerColor = Theme.colors.primaryContainer,
        ),
    )

}