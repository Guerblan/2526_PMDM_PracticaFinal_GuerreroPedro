package com.pedro.pideyaapp.ui.state

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isLogged: Boolean = false,
    val isLoading: Boolean = false,
    val message: String? = null,
    val registrationCompleted: Boolean = false
)
