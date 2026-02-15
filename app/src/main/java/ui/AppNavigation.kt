package com.pedro.pideyaapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import com.pedro.pideyaapp.data.local.UserPreferences
import com.pedro.pideyaapp.ui.home.MainScreen
import com.pedro.pideyaapp.ui.login.LoginScreen
import com.pedro.pideyaapp.ui.registro.RegistroScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val prefs = UserPreferences(context)

    val start = if (prefs.isLogged()) "main" else "login"

    NavHost(
        navController = navController,
        startDestination = start
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("registro") {
            RegistroScreen(navController)
        }

        composable("main") {
            MainScreen(navController)
        }
    }
}