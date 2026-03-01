package com.pedro.pideyaapp.ui.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pedro.pideyaapp.data.local.UserPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {

    val context = LocalContext.current
    val prefs = UserPreferences(context)

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("PideYa") },
                actions = {
                    TextButton(onClick = {

                        prefs.logout()

                        navController.navigate("login") {
                            popUpTo("main") { inclusive = true }
                        }

                    }) {
                        Text("Cerrar sesión")
                    }
                }
            )
        },

        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    label = { Text("Inicio") },
                    icon = { }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    label = { Text("Pedidos") },
                    icon = { }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    label = { Text("Perfil") },
                    icon = { }
                )
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
        ) {
            Text("Pantalla principal REAL")
        }
    }
}