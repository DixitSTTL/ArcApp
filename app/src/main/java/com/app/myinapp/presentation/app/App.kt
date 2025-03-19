package com.app.myinapp.presentation.app

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.domain.model.Photo
import com.app.myinapp.presentation.coreImagePreview.CorePreviewScreen
import com.app.myinapp.presentation.dialog.OptionDialog
import com.app.myinapp.presentation.imagePreview.ImagePreviewScreen
import com.app.myinapp.presentation.main.MainScreen
import com.app.myinapp.presentation.routes
import com.app.myinapp.presentation.search.SearchScreen
import com.app.myinapp.presentation.setting.SettingScreen
import com.app.myinapp.presentation.ui.theme.MyInAppTheme
import com.app.myinapp.presentation.videoPreview.VideoPreviewScreen
import com.google.gson.Gson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun App(viewModel: AppViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()

    MyInAppTheme(dynamicColor = state.isDynamicUi, darkTheme = state.isDarkMode) {

        SharedTransitionLayout() {
            NavHost(navController = navController, startDestination = routes.MAIN_SCREEN) {
                composable<routes.MAIN_SCREEN> { backStackEntry ->
                    MainScreen(
                        navController,
                        animatedVisibilityScope = this@composable
                    )
                }

                composable(
                    routes.SEARCH_SCREEN.route,
                    arguments = listOf(navArgument("Data") { type = NavType.StringType })
                ) { backStackEntry ->
                    val dataJson = backStackEntry.arguments?.getString("Data")
                    val data = Gson().fromJson(dataJson, String::class.java) // Decode recipe JSON

                    BackHandler {
                        navController.popBackStack()
                    }
                    SearchScreen(navController, data, animatedVisibilityScope = this@composable)
                }

                composable(
                    routes.IMAGE_PREVIEW_SCREEN.route,
                    arguments = listOf(
                        navArgument("Photo") { type = NavType.StringType },
                        navArgument("Index") { type = NavType.StringType })
                ) { backStackEntry ->
                    BackHandler {
                        navController.popBackStack()
                    }

                    backStackEntry.arguments?.let {
                        val dataJson = backStackEntry.arguments?.getString("Photo")
                        val index = backStackEntry.arguments?.getString("Index") ?: ""
                        val data =
                            Gson().fromJson(dataJson, Photo::class.java) // Decode recipe JSON

                        data?.let {
                            ImagePreviewScreen(
                                navController,
                                data,
                                index,
                                animatedVisibilityScope = this@composable
                            )
                        }

                    }

                }

                composable(
                    routes.CORE_IMAGE_PREVIEW_SCREEN.route,
                    arguments = listOf(
                        navArgument("Photo") { type = NavType.StringType },
                        navArgument("Index") { type = NavType.StringType })
                ) { backStackEntry ->
                    BackHandler {
                        navController.popBackStack()
                    }
                    backStackEntry.arguments?.let {
                        val dataJson = backStackEntry.arguments?.getString("Photo")
                        val index = backStackEntry.arguments?.getString("Index") ?: ""
                        val data =
                            Gson().fromJson(dataJson, Photo::class.java) // Decode recipe JSON

                        data?.let {
                            CorePreviewScreen(
                                navController,
                                data,
                                index,
                                animatedVisibilityScope = this@composable
                            )
                        }
                    }

                }

                composable(
                    routes.VIDEO_PREVIEW_SCREEN.route,
                    arguments = listOf(navArgument("Video") { type = NavType.StringType })
                ) { backStackEntry ->
                    BackHandler {
                        navController.popBackStack()
                    }
                    backStackEntry.arguments?.let {
                        val dataJson = backStackEntry.arguments?.getString("Video")
                        val data =
                            Gson().fromJson(dataJson, VideoDTO::class.java) // Decode recipe JSON

                        data?.let {
                            VideoPreviewScreen(
                                navController,
                                data,
                                animatedVisibilityScope = this@composable
                            )

                        }
                    }
                }

                composable<routes.SETTING_SCREEN> { backStackEntry ->
                    BackHandler {
                        navController.popBackStack()
                    }
                    SettingScreen(navController)
                }

                dialog(
                    routes.OPTION_DIALOG.route,
                    arguments = listOf(navArgument("Photo") { type = NavType.StringType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.let {
                        val dataJson = backStackEntry.arguments?.getString("Photo")
                        val data =
                            Gson().fromJson(dataJson, Photo::class.java) // Decode recipe JSON

                        data?.let {
                            OptionDialog(navController, data)
                        }
                    }
                }

            }

        }
    }

}