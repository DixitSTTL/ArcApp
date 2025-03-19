package com.app.myinapp.presentation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.myinapp.common.AppBar
import com.app.myinapp.presentation.ui.theme.Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingScreen(
    navController: NavHostController,
    viewModel: SettingScreenViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.uiAction.collect {
            when (it) {
                is SettingScreenInteract.updateDynamicTheme -> {
                    viewModel.setDynamicUI(it.status)
                }

                is SettingScreenInteract.updateUiTheme -> {
                    viewModel.setUITheme(it.status)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            AppBar({
                navController.popBackStack()
            }, "Setting")
        }
    ) { it ->
        Box(
            Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize()
                .background(color = Theme.colors.background)
        ) {

            Column {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Dynamic Theme",
                        color = Theme.colors.secondary,
                        modifier = Modifier.weight(1f),
                        style = Theme.typography.body
                    )
                    Switch(
                        checked = state.isDynamicUi,
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Theme.colors.onPrimaryContainer,
                            checkedThumbColor = Theme.colors.primaryContainer,
                        ),
                        onCheckedChange = { status ->
                            viewModel.sendAction(SettingScreenInteract.updateDynamicTheme(status = status))
                        })
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Dark mode",
                        color = Theme.colors.secondary,
                        modifier = Modifier.weight(1f),
                        style = Theme.typography.body

                    )
                    Switch(
                        checked = state.isDarkMode,
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Theme.colors.onPrimaryContainer,
                            checkedThumbColor = Theme.colors.primaryContainer,
                        ),
                        onCheckedChange = { status ->
                            viewModel.sendAction(SettingScreenInteract.updateUiTheme(status = status))
                        })
                }
            }
        }
    }
}