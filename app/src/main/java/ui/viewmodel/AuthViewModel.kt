package com.pedro.pideyaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.pedro.pideyaapp.domain.usecase.LoginCasoDeUso
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isLogged: Boolean = false,
    val error: String? = null
)

class AuthViewModel(
    private val loginCasoDeUso: LoginCasoDeUso
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    // ✅ LOGIN
    fun login() {

        val email = _uiState.value.email
        val password = _uiState.value.password

        val resultado = loginCasoDeUso.ejecutar(email, password)

        if (resultado) {
            _uiState.value = _uiState.value.copy(
                isLogged = true,
                error = null
            )
        } else {
            _uiState.value = _uiState.value.copy(
                error = "Credenciales inválidas"
            )
        }
    }

    // ✅ REGISTRO (mínimo necesario para la entrega)
    fun registrar() {

        val email = _uiState.value.email
        val password = _uiState.value.password

        // reutilizamos el caso de uso
        loginCasoDeUso.registrar(email, password)
    }

    fun logout() {
        _uiState.value = AuthUiState()
    }
}