package com.pedro.pideyaapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pedro.pideyaapp.ui.screens.HomeScreen
import com.pedro.pideyaapp.ui.screens.LoginScreen
import com.pedro.pideyaapp.ui.screens.RegistroScreen
import com.pedro.pideyaapp.ui.screens.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        // SPLASH (decide a dónde ir)
        composable("splash") {
            SplashScreen(navController)
        }

        // LOGIN
        composable("login") {
            LoginScreen(navController)
        }

        // REGISTRO
        composable("registro") {
            RegistroScreen(navController)
        }

        // HOME
        composable("home") {
            HomeScreen(navController)
        }
    }
}