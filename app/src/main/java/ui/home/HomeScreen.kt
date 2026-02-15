package com.pedro.pideyaapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pedro.pideyaapp.data.local.UserPreferences

@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current
    val prefs = UserPreferences(context)
    val email = prefs.getUser()

    Column(modifier = Modifier.padding(20.dp)) {

        Text(text = "HOME")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Usuario logueado: $email")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {

            prefs.logout()

            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }

        }) {
            Text("Cerrar sesión")
        }
    }
}