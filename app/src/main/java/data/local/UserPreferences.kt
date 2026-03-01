package com.pedro.pideyaapp.data.local

import android.content.Context

class UserPreferences(context: Context) {

    private val prefs =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_LOGGED = "logged"
    }

    // ✅ Guardar usuario registrado
    fun saveUser(email: String, password: String) {
        prefs.edit()
            .putString(KEY_EMAIL, email)
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    // ✅ Comprobar login
    fun isRegistered(email: String, password: String): Boolean {
        val savedEmail = prefs.getString(KEY_EMAIL, null)
        val savedPassword = prefs.getString(KEY_PASSWORD, null)

        return email == savedEmail && password == savedPassword
    }

    // ✅ Guardar sesión
    fun setLogged(value: Boolean) {
        prefs.edit().putBoolean(KEY_LOGGED, value).apply()
    }

    // ✅ Saber si hay sesión
    fun isLogged(): Boolean {
        return prefs.getBoolean(KEY_LOGGED, false)
    }

    // ✅ OBTENER EMAIL DEL USUARIO
    fun getUser(): String {
        return prefs.getString(KEY_EMAIL, "Usuario") ?: "Usuario"
    }

    // ✅ Logout
    fun logout() {
        prefs.edit().putBoolean(KEY_LOGGED, false).apply()
    }
}