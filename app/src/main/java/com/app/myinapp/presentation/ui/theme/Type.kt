package com.app.myinapp.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.app.myinapp.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Zilla Slab"),
        fontProvider = provider
    )
)

@Composable
fun headlineLarge(): TextStyle {
    return TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.4.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun headline(): TextStyle {
    return TextStyle(
        fontSize = 20.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun titleLarge(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun title(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W600,
    )
}
@Composable
fun titleMedium(): TextStyle {
    return TextStyle(
        fontSize = 16.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W400,
    )
}
@Composable
fun body(): TextStyle {
    return TextStyle(
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
        fontFamily = fontFamily,
        fontWeight = FontWeight.W400,
    )
}
@Composable
fun caption(): TextStyle {
    return TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
    )
}