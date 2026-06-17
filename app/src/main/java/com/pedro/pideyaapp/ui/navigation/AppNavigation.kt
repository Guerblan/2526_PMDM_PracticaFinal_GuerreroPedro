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
import com.pedro.pideyaapp.ui.screens.EventsScreen
import com.pedro.pideyaapp.ui.screens.EstablishmentsScreen
import com.pedro.pideyaapp.ui.screens.LoginScreen
import com.pedro.pideyaapp.ui.screens.OrdersScreen
import com.pedro.pideyaapp.ui.screens.ProductsScreen
import com.pedro.pideyaapp.ui.screens.ProfileScreen
import com.pedro.pideyaapp.ui.screens.RegisterScreen
import com.pedro.pideyaapp.ui.screens.SplashScreen
import com.pedro.pideyaapp.ui.viewmodel.PideYaViewModel
import com.pedro.pideyaapp.ui.viewmodel.PideYaViewModelFactory

object Routes {
    const val Splash = "splash"
    const val Login = "login"
    const val Register = "register"
    const val Events = "events"
    const val Establishments = "establishments/{eventId}/{eventName}"
    const val Products = "products/{establishmentId}/{establishmentName}"
    const val Cart = "cart"
    const val Orders = "orders"
    const val Profile = "profile"

    fun establishments(eventId: String, eventName: String): String {
        return "establishments/$eventId/${Uri.encode(eventName)}"
    }

    fun products(establishmentId: String, establishmentName: String): String {
        return "products/$establishmentId/${Uri.encode(establishmentName)}"
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
                onNavigateToEvents = {
                    navController.navigate(Routes.Events) {
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
                    navController.navigate(Routes.Events) {
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

        composable(Routes.Events) {
            EventsScreen(
                viewModel = viewModel,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = updateLanguage,
                onOpenEvent = { eventId, eventName ->
                    navController.navigate(Routes.establishments(eventId, eventName))
                },
                onOpenOrders = { navController.navigate(Routes.Orders) },
                onOpenProfile = { navController.navigate(Routes.Profile) },
                onLogout = {
                    viewModel.logout()
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Events) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Routes.Establishments,
            arguments = listOf(
                navArgument("eventId") { type = NavType.StringType },
                navArgument("eventName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId").orEmpty()
            val eventName = Uri.decode(
                backStackEntry.arguments?.getString("eventName").orEmpty()
            )
            EstablishmentsScreen(
                viewModel = viewModel,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = updateLanguage,
                eventId = eventId,
                eventName = eventName,
                onBack = { navController.popBackStack() },
                onOpenEstablishment = { establishmentId, establishmentName ->
                    navController.navigate(Routes.products(establishmentId, establishmentName))
                },
                onOpenCart = { navController.navigate(Routes.Cart) }
            )
        }

        composable(
            route = Routes.Products,
            arguments = listOf(
                navArgument("establishmentId") { type = NavType.StringType },
                navArgument("establishmentName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val establishmentId = backStackEntry.arguments?.getString("establishmentId").orEmpty()
            val establishmentName = Uri.decode(
                backStackEntry.arguments?.getString("establishmentName").orEmpty()
            )
            ProductsScreen(
                viewModel = viewModel,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = updateLanguage,
                establishmentId = establishmentId,
                establishmentName = establishmentName,
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
                onBackToEvents = {
                    navController.navigate(Routes.Events) {
                        popUpTo(Routes.Events) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Profile) {
            ProfileScreen(
                viewModel = viewModel,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = updateLanguage,
                onBack = { navController.popBackStack() },
                onLogout = {
                    viewModel.logout()
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Events) { inclusive = true }
                    }
                }
            )
        }
    }
}
