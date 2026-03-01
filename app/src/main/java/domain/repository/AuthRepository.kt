package com.pedro.pideyaapp.domain.repository

interface AuthRepository {

    // ✅ Login
    fun login(email: String, password: String): Boolean

    // ✅ Registro
    fun register(email: String, password: String)
}