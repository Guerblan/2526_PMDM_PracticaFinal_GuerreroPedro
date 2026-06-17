package com.pedro.pideyaapp.data.datasource

import android.content.Context

class AuthRemoteDataSource(
    context: Context
) {

    private val preferences = UserPreferencesDataSource(
        com.pedro.pideyaapp.data.local.UserPreferences(context.applicationContext)
    )

    fun isConfigured(): Boolean = true

    suspend fun login(email: String, password: String) {
        val registeredEmail = preferences.getRegisteredEmail()
        val registeredPassword = preferences.getRegisteredPassword()

        if (registeredEmail == null || registeredPassword == null) {
            throw IllegalStateException("No existe ningun usuario registrado.")
        }

        if (registeredEmail != email || registeredPassword != password) {
            throw IllegalStateException("Email o contrasena incorrectos.")
        }
    }

    suspend fun register(email: String, password: String) {
        preferences.saveRegisteredUser(email, password)
    }

    fun currentUserEmail(): String? = preferences.getUser()

    fun isLoggedIn(): Boolean = preferences.isLogged()

    fun logout() = Unit
}
