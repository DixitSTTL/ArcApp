package com.app.myinapp.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.app.myinapp.R

data class Colors(
    val black: Color,
    val white: Color,
    val b_w: Color,
    val b_w_reverse: Color,
    val un_played: Color,
    val played: Color,

    val main_color: Color,
    val main_red: Color,
    val main_black: Color,

    val dialog_text: Color,
    val dialog_icon: Color,

    val tab_layout_bg: Color,

    val light_blue_50: Color,
    val light_blue_200: Color,
    val light_blue_600: Color,
    val light_blue_900: Color
)

private val DarkColorScheme = Colors(
    black =Color(0xFF000000),
    white =Color(0xFFFFFFFF),

    b_w =Color(0xFF000000),
    b_w_reverse =Color(0xFFffffff),

    un_played =Color(0xFF3A3A3A),
    played =Color(0x4AFFD700),

    main_color =Color(0xFFFFD700),
    main_red =Color(0xFF800000),
    main_black =Color(0xFF080808),

    dialog_text =Color(0xFFffffff),
    dialog_icon =Color(0xFFffffff),

    tab_layout_bg = Color(0xFF080808),

    light_blue_50 =Color(0xFFE1F5FE),
    light_blue_200 =Color(0xFF81D4FA),
    light_blue_600 =Color(0xFF039BE5),
    light_blue_900 = Color(0xFF01579B)

)

private val _DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF9F1C),  // Vibrant Orange
    onPrimary = Color(0xFF000000),  // Black (for contrast)
    primaryContainer = Color(0xFFFFBF69),  // Soft Amber
    onPrimaryContainer = Color(0xFF3E2723),  // Dark Brownish for contrast
    inversePrimary = Color(0xFFF95738),  // Fiery Red

    secondary = Color(0xFFFFBF69),  // Warm Amber
    onSecondary = Color(0xFF1C1C1C),  // Dark Gray for contrast
    secondaryContainer = Color(0xFF5A5A5A),  // Muted Grayish Brown
    onSecondaryContainer = Color(0xFFFFFFFF),  // White

    tertiary = Color(0xFF58C4DD),  // Cool Cyan for subtle contrast
    onTertiary = Color(0xFF00202E),  // Deep Navy for text/icons
    tertiaryContainer = Color(0xFF007EA7),  // Rich Blue
    onTertiaryContainer = Color(0xFFFFFFFF),  // White for clarity

    background = Color(0xFF0E0E0E),  // Deep Black
    onBackground = Color(0xFFFFFFFF),  // White for readability
    surface = Color(0xFF1C1C1C),  // Charcoal Gray
    onSurface = Color(0xFFE0E0E0),  // Soft White for text/icons

    surfaceVariant = Color(0xFF2E2E2E),  // Darker Gray for depth
    onSurfaceVariant = Color(0xFFB0B0B0),  // Soft Gray for contrast
    surfaceTint = Color(0xFFFF9F1C),  // Primary tint for dynamic effects

    inverseSurface = Color(0xFFFAFAFA),  // Light Gray for inverted UI
    inverseOnSurface = Color(0xFF1C1C1C),  // Dark text/icons for light elements

    error = Color(0xFFCF6679),  // Standard Material 3 error color
    onError = Color(0xFF370B1E),  // Darker Red for contrast
    errorContainer = Color(0xFFB00020),  // Deep Red
    onErrorContainer = Color(0xFFFFDAD4),  // Light Red for error messages

    outline = Color(0xFF5A5A5A),  // Soft border color
    outlineVariant = Color(0xFF3A3A3A),  // Darker border variant
    scrim = Color(0x99000000),  // Translucent Black for overlays

    surfaceBright = Color(0xFF303030),  // Brighter dark surface
    surfaceDim = Color(0xFF181818),  // Even darker surface for depth
    surfaceContainer = Color(0xFF252525),  // Standard dark container
    surfaceContainerHigh = Color(0xFF333333),  // Slightly lighter container
    surfaceContainerHighest = Color(0xFF3F3F3F),  // Lighter contrast
    surfaceContainerLow = Color(0xFF191919),  // Lower contrast
    surfaceContainerLowest = Color(0xFF121212)  // Almost pure black
)


private val _LightColorScheme = lightColorScheme(
    primary = Color(0xFFF95738),  // Fiery Red
    onPrimary = Color(0xFFFFFFFF),  // White for contrast
    primaryContainer = Color(0xFFFFAD60),  // Warm Peach
    onPrimaryContainer = Color(0xFF3E2723),  // Deep Brown

    inversePrimary = Color(0xFFFF9F1C),  // Vibrant Orange

    secondary = Color(0xFFFFAD60),  // Warm Peach
    onSecondary = Color(0xFF1C1C1C),  // Dark text/icons
    secondaryContainer = Color(0xFFE3DAC9),  // Soft Beige
    onSecondaryContainer = Color(0xFF1C1C1C),  // Dark contrast

    tertiary = Color(0xFF007EA7),  // Cool Blue
    onTertiary = Color(0xFFFFFFFF),  // White for clarity
    tertiaryContainer = Color(0xFF58C4DD),  // Soft Cyan
    onTertiaryContainer = Color(0xFF00202E),  // Deep Navy

    background = Color(0xFFFAFAFA),  // Ultra Light Gray
    onBackground = Color(0xFF000000),  // Black for readability
    surface = Color(0xFFFFFFFF),  // Pure White
    onSurface = Color(0xFF1E1E1E),  // Deep Black

    surfaceVariant = Color(0xFFE0E0E0),  // Soft Gray
    onSurfaceVariant = Color(0xFF5A5A5A),  // Dark Gray
    surfaceTint = Color(0xFFF95738),  // Fiery Red accent

    inverseSurface = Color(0xFF303030),  // Dark Gray for inverted UI
    inverseOnSurface = Color(0xFFE0E0E0),  // Soft White text/icons

    error = Color(0xFFB00020),  // Material 3 error color
    onError = Color(0xFFFFFFFF),  // White contrast
    errorContainer = Color(0xFFFFDAD4),  // Light Red
    onErrorContainer = Color(0xFF370B1E),  // Darker Red

    outline = Color(0xFFD0D0D0),  // Light Gray outline
    outlineVariant = Color(0xFFC0C0C0),  // Softer Gray
    scrim = Color(0x55000000),  // Translucent Black for overlays

    surfaceBright = Color(0xFFF5F5F5),  // Brighter surface
    surfaceDim = Color(0xFFE0E0E0),  // Softer dim surface
    surfaceContainer = Color(0xFFF0F0F0),  // Standard light container
    surfaceContainerHigh = Color(0xFFEAEAEA),  // Slightly darker container
    surfaceContainerHighest = Color(0xFFE5E5E5),  // More contrast
    surfaceContainerLow = Color(0xFFF8F8F8),  // Softer contrast
    surfaceContainerLowest = Color(0xFFFFFFFF)  // Pure white background
)


private val LightColorScheme = Colors(
    black =Color(0xFF000000),
    white =Color(0xFFFFFFFF),

    b_w =Color(0xFFFFFFFF),
    b_w_reverse =Color(0xFF000000),

    un_played =Color(0xFF3A3A3A),
    played =Color(0x4AFFD700),

    main_color =Color(0xFFFFD700),
    main_red =Color(0xFF9e0000),
    main_black =Color(0xFF080808),

    dialog_text =Color(0xFF080808),
    dialog_icon =Color(0xFFFFD700),

    tab_layout_bg = Color(0xFFFFFFFF),

    light_blue_50 =Color(0xFFE1F5FE),
    light_blue_200 =Color(0xFF81D4FA),
    light_blue_600 =Color(0xFF039BE5),
    light_blue_900 = Color(0xFF01579B)
)
private val localDimens = staticCompositionLocalOf { Dimens() }
private val localColorScheme = staticCompositionLocalOf { _LightColorScheme }
private val localRadius = staticCompositionLocalOf { Radius() }
private val localTypography = staticCompositionLocalOf { Typography() }

object Theme {
    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = localColorScheme.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = localTypography.current

    val radius: Radius
        @Composable
        @ReadOnlyComposable
        get() = localRadius.current

    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = localDimens.current
}
@Composable
fun MyInAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> _DarkColorScheme
        else -> _LightColorScheme
    }


//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val fontFamily = remember { FontFamily(
        Font(
            googleFont = GoogleFont("Zilla Slab"),
            fontProvider = provider
        )
    ) }
    val typography = Typography(
        headlineLarge = headlineLarge(fontFamily),
        headline = headline(fontFamily),
        titleLarge = titleLarge(fontFamily),
        title = title(fontFamily),
        titleMedium = titleMedium(fontFamily),
        body = body(fontFamily),
        caption = caption(fontFamily),
    )

    CompositionLocalProvider(
        localColorScheme provides colorScheme,
        localTypography provides typography,
        localDimens provides Dimens(),
        localRadius provides Radius(),
    ) {
        content()
    }
}