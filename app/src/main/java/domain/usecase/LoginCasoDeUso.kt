package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.repository.AuthRepository

class LoginCasoDeUso(
    private val repository: AuthRepository
) {

    // ✅ LOGIN
    fun ejecutar(email: String, password: String): Boolean {
        return repository.login(email, password)
    }

    // ✅ REGISTRO (mínimo necesario)
    fun registrar(email: String, password: String) {
        repository.register(email, password)
    }
}