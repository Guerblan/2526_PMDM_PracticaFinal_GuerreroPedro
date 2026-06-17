package com.pedro.pideyaapp.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(email: String, password: String): Result<Unit>
    fun isLoggedIn(): Boolean
    fun getCurrentUserEmail(): String?
    fun logout()
}
