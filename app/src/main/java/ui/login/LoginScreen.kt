package com.pedro.pideyaapp.ui.login

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
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current
    val prefs = UserPreferences(context)

    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(20.dp)) {

        Text(text = "Login")

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

                Toast.makeText(context, "Usuario guardado", Toast.LENGTH_SHORT).show()

                navController.navigate("home")

            } else {
                Toast.makeText(context, "Introduce email", Toast.LENGTH_SHORT).show()
            }

        }) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "¿No tienes cuenta?")

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            navController.navigate("registro")
        }) {
            Text("Ir a registro")
        }
    }
}