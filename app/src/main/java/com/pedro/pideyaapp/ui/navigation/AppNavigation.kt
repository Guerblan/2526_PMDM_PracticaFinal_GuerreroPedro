package com.pedro.pideyaapp.ui.navigation
import android.net.Uri
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pedro.pideyaapp.data.AppContainer
import com.pedro.pideyaapp.ui.screens.CartScreen
import com.pedro.pideyaapp.ui.screens.LoginScreen
import com.pedro.pideyaapp.ui.screens.MenuScreen
import com.pedro.pideyaapp.ui.screens.OrdersScreen
import com.pedro.pideyaapp.ui.screens.RegisterScreen
import com.pedro.pideyaapp.ui.screens.RestaurantsScreen
import com.pedro.pideyaapp.ui.screens.SplashScreen
import com.pedro.pideyaapp.ui.viewmodel.PideYaViewModel
import com.pedro.pideyaapp.ui.viewmodel.PideYaViewModelFactory

object Routes {
    const val Splash = "splash"
    const val Login = "login"
    const val Register = "register"
    const val Restaurants = "restaurants"
    const val Menu = "menu/{restaurantId}/{restaurantName}"
    const val Cart = "cart"
    const val Orders = "orders"

    fun menu(restaurantId: String, restaurantName: String): String {
        return "menu/$restaurantId/${Uri.encode(restaurantName)}"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val container = remember(context) { AppContainer(context.applicationContext) }
    val viewModel: PideYaViewModel = viewModel(factory = PideYaViewModelFactory(container))
    val currentLanguage = AppCompatDelegate.getApplicationLocales().toLanguageTags()
    var selectedLanguage by remember {
        mutableStateOf(currentLanguage.ifBlank { container.userPreferences.getLanguage() })
    }
    val updateLanguage: (String) -> Unit = { languageTag ->
        if (languageTag != selectedLanguage) {
            selectedLanguage = languageTag
            container.userPreferences.saveLanguage(languageTag)
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(languageTag)
            )
            viewModel.onLanguageChanged()
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        composable(Routes.Splash) {
            SplashScreen(
                isLogged = viewModel.authUiState.value.isLogged,
                onNavigateToLogin = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                },
                onNavigateToRestaurants = {
                    navController.navigate(Routes.Restaurants) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Login) {
            LoginScreen(
                viewModel = viewModel,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = updateLanguage,
                onGoRegister = { navController.navigate(Routes.Register) },
                onLoginSuccess = {
                    navController.navigate(Routes.Restaurants) {
                        popUpTo(Routes.Login) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Register) {
            RegisterScreen(
                viewModel = viewModel,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = updateLanguage,
                onBackToLogin = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Register) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Restaurants) {
            RestaurantsScreen(
                viewModel = viewModel,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = updateLanguage,
                onOpenMenu = { restaurantId, restaurantName ->
                    navController.navigate(Routes.menu(restaurantId, restaurantName))
                },
                onOpenCart = { navController.navigate(Routes.Cart) },
                onOpenOrders = { navController.navigate(Routes.Orders) },
                onLogout = {
                    viewModel.logout()
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Restaurants) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Routes.Menu,
            arguments = listOf(
                navArgument("restaurantId") { type = NavType.StringType },
                navArgument("restaurantName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId").orEmpty()
            val restaurantName = Uri.decode(
                backStackEntry.arguments?.getString("restaurantName").orEmpty()
            )
            MenuScreen(
                viewModel = viewModel,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = updateLanguage,
                restaurantId = restaurantId,
                restaurantName = restaurantName,
                onBack = { navController.popBackStack() },
                onOpenCart = { navController.navigate(Routes.Cart) }
            )
        }

        composable(Routes.Cart) {
            CartScreen(
                viewModel = viewModel,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = updateLanguage,
                onBack = { navController.popBackStack() },
                onConfirmOrder = {
                    navController.navigate(Routes.Orders)
                }
            )
        }

        composable(Routes.Orders) {
            OrdersScreen(
                viewModel = viewModel,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = updateLanguage,
                onBackToRestaurants = {
                    navController.navigate(Routes.Restaurants) {
                        popUpTo(Routes.Restaurants) { inclusive = true }
                    }
                }
            )
        }
    }
}
