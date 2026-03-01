package com.pedro.pideyaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pedro.pideyaapp.domain.repository.AuthRepository
import com.pedro.pideyaapp.domain.usecase.LoginCasoDeUso

class AuthViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {

            // El ViewModel NO construye dependencias.
            // El Factory es el “montador” de la cadena: Repository -> CasoDeUso -> ViewModel
            val loginCasoDeUso = LoginCasoDeUso(authRepository)

            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(loginCasoDeUso) as T
        }

        throw IllegalArgumentException("ViewModel desconocido")
    }
}