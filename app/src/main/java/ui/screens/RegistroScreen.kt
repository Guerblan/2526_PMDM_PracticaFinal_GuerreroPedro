package com.pedro.pideyaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext

import com.pedro.pideyaapp.ui.viewmodel.AuthViewModel
import com.pedro.pideyaapp.ui.viewmodel.AuthViewModelFactory
import com.pedro.pideyaapp.data.repository.AuthRepositoryImpl

@Composable
fun RegistroScreen(navController: NavController) {

    val context = LocalContext.current

    val factory = AuthViewModelFactory(
        AuthRepositoryImpl(context)
    )

    val viewModel: AuthViewModel = viewModel(
        factory = factory
    )

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(20.dp)
    ) {

        Text(text = "Registro")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // ✅ REGISTRO REAL
                viewModel.registrar()

                // volvemos al login
                navController.navigate("login") {
                    popUpTo("registro") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}