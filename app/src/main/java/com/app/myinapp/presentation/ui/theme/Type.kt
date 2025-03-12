package com.app.myinapp.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp




@Composable
fun headlineLarge(fontFamily: FontFamily): TextStyle {
    return TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.4.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun headline(fontFamily: FontFamily): TextStyle {
    return TextStyle(
        fontSize = 20.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun titleLarge(fontFamily: FontFamily): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun title(fontFamily: FontFamily): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun titleMedium(fontFamily: FontFamily): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W400,
    )
}
@Composable
fun body(fontFamily: FontFamily): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W400,
    )
}
@Composable
fun caption(fontFamily: FontFamily): TextStyle {
    return TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
    )
}