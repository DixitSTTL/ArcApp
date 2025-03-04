package com.app.myinapp.presentation.dialog.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun DialogOptions(onClick: () -> Unit, icon: Int, str: String) {

    Card(
        onClick = {onClick()},
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 8.dp)
            .background(Color.Transparent, RoundedCornerShape(30))
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp, 12.dp)
                .fillMaxWidth()
        ) {

            Icon(painter = painterResource(icon), "")

            Text(
                str, Modifier
                    .padding(12.dp, 0.dp)
                    .weight(1f)
            )
        }
    }
}