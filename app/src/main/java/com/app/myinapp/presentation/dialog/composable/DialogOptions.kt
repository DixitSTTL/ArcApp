package com.app.myinapp.presentation.dialog.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.myinapp.presentation.ui.theme.Theme

@Composable
fun DialogOptions(onClick: () -> Unit, icon: Int, str: String) {

    Card(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 8.dp)
            .background(Color.Transparent, RoundedCornerShape(30)),
        colors = CardDefaults.cardColors(
            containerColor = Theme.colors.primaryContainer,
        )
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp, 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(painter = painterResource(icon), "", tint = Theme.colors.onPrimaryContainer)

            Text(
                text = str,
                modifier = Modifier
                    .padding(12.dp, 0.dp)
                    .weight(1f),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Theme.colors.onPrimaryContainer
            )
        }
    }
}