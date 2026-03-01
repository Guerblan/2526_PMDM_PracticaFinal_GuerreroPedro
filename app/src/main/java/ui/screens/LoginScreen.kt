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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

import com.pedro.pideyaapp.ui.viewmodel.AuthViewModel
import com.pedro.pideyaapp.ui.viewmodel.AuthViewModelFactory
import com.pedro.pideyaapp.data.repository.AuthRepositoryImpl

@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current

    val factory = AuthViewModelFactory(
        AuthRepositoryImpl(context)
    )

    val viewModel: AuthViewModel = viewModel(factory = factory)

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(20.dp)
    ) {

        Text(text = "Login")

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
            onClick = { viewModel.login() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                navController.navigate("registro")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a registro")
        }

        Spacer(modifier = Modifier.height(12.dp))

        uiState.error?.let {
            Text(text = it)
        }
    }

    // ✅ Navegación correcta tras login
    LaunchedEffect(uiState.isLogged) {
        if (uiState.isLogged) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }
}