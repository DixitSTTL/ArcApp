package com.app.myinapp.presentation.imagePreview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun ActionButton(onclick: () -> Unit, icon: Int){

    Box(
        modifier = Modifier.size(60.dp).clip(RoundedCornerShape(50)).clickable { onclick() }.background(Color.Gray)) {
        Icon(painter = painterResource(icon), contentDescription = "", modifier = Modifier.size(22.dp).align(Alignment.Center))
    }

}