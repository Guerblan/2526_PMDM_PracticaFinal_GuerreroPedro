package com.pedro.pideyaapp.ui.registro

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pedro.pideyaapp.data.local.UserPreferences

@Composable
fun RegistroScreen(navController: NavController) {

    val context = LocalContext.current
    val prefs = UserPreferences(context)

    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(20.dp)) {

        Text(text = "Registro")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            if (email.isNotEmpty()) {
                prefs.saveUser(email)

                Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()

                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }

            } else {
                Toast.makeText(context, "Introduce email", Toast.LENGTH_SHORT).show()
            }

        }) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate("login")
        }) {
            Text("Volver a login")
        }
    }
}