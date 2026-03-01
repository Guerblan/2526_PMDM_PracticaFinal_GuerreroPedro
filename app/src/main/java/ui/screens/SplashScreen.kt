package com.pedro.pideyaapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {

        // pequeña pausa para mostrar splash
        delay(1000)

        val sharedPreferences =
            context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        val usuarioGuardado =
            sharedPreferences.getString("usuario", null)

        if (usuarioGuardado != null) {
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "PideYa")
    }
}