package com.app.myinapp.presentation.search.composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.myinapp.R
import com.app.myinapp.presentation.search.SearchScreenInteract
import com.app.myinapp.presentation.search.SearchScreenState
import com.app.myinapp.presentation.ui.theme.Theme
import kotlinx.coroutines.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    state: SearchScreenState,
    uiAction: (SearchScreenInteract) -> Job,
    stateUpdate: (SearchScreenState) -> Unit,
) {

    TopAppBar(
        title = {

            TextField(
                value = state.query,
                onValueChange = { text ->
                    stateUpdate(state.copy(query = text))
                },
                placeholder = {
                    Text(
                        "Enter query", style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,

                            ),
                        color = Theme.colors.primaryContainer
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Theme.colors.primaryContainer,
                    unfocusedTextColor = Theme.colors.primaryContainer,
                    focusedContainerColor = Theme.colors.onPrimaryContainer,
                    unfocusedContainerColor = Theme.colors.onPrimaryContainer,
                    cursorColor = Theme.colors.secondary,
                ),
                keyboardActions = KeyboardActions(onDone = {
                    uiAction(SearchScreenInteract.searchList())
                }),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                ),

            )
        },
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton(onClick = { uiAction(SearchScreenInteract.searchList()) }) {
                Icon(painterResource(R.drawable.ic_search), "", tint = Theme.colors.onPrimaryContainer)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Theme.colors.primaryContainer
        ))

}