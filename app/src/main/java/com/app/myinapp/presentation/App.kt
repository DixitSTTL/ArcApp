package com.app.myinapp.presentation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.myinapp.data.model.Photo
import com.app.myinapp.presentation.imagePreview.ImagePreviewScreen
import com.app.myinapp.presentation.main.MainScreen
import com.app.myinapp.presentation.search.SearchScreen
import com.app.myinapp.presentation.ui.theme.MyInAppTheme
import com.app.myinapp.presentation.videoPreview.VideoPreviewScreen
import com.google.gson.Gson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass


class CustomNavType<T : Parcelable>(
    private val clazz: KClass<T>,
    private val serializer: KSerializer<T>
) : NavType<T>(false) {
    override fun get(bundle: Bundle, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, clazz.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(serializer, value)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: T): String {
        return Json.encodeToString(serializer, value)
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    MyInAppTheme {

        NavHost(navController = navController, startDestination = routes.MAIN_SCREEN) {
            composable<routes.MAIN_SCREEN> { backStackEntry ->
                MainScreen(navController)
            }

            composable<routes.SEARCH_SCREEN> { backStackEntry ->
                SearchScreen(navController)
            }

            composable(
                routes.IMAGE_PREVIEW_SCREEN.route,
                arguments = listOf(navArgument("Photo") { type = NavType.StringType })
            ) { backStackEntry ->
                backStackEntry.arguments?.let {
                    val dataJson = backStackEntry.arguments?.getString("Photo")
                    val data = Gson().fromJson(dataJson, Photo::class.java) // Decode recipe JSON

                    data?.let {
                        ImagePreviewScreen(navController, data)
                    }

                }

            }

            composable<routes.CORE_IMAGE_PREVIEW_SCREEN> { backStackEntry ->
//                ImagePreviewScreen(navController, data)
                VideoPreviewScreen(navController)

            }

            composable<routes.VIDEO_PREVIEW_SCREEN> { backStackEntry ->
                VideoPreviewScreen(navController)
            }

        }
    }


}