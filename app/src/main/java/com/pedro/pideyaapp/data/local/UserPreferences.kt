package com.pedro.pideyaapp.data.local

import android.content.Context

class UserPreferences(context: Context) {

    private val prefs =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_REGISTERED_EMAIL = "registered_email"
        private const val KEY_REGISTERED_PASSWORD = "registered_password"
        private const val KEY_LOGGED = "logged"
        private const val KEY_LANGUAGE = "language"
    }

    fun saveUser(email: String) {
        prefs.edit()
            .putString(KEY_EMAIL, email)
            .apply()
    }

    fun setLogged(value: Boolean) {
        prefs.edit().putBoolean(KEY_LOGGED, value).apply()
    }

    fun isLogged(): Boolean {
        return prefs.getBoolean(KEY_LOGGED, false)
    }

    fun getUser(): String? {
        return prefs.getString(KEY_EMAIL, null)
    }

    fun saveRegisteredUser(email: String, password: String) {
        prefs.edit()
            .putString(KEY_REGISTERED_EMAIL, email)
            .putString(KEY_REGISTERED_PASSWORD, password)
            .apply()
    }

    fun getRegisteredEmail(): String? {
        return prefs.getString(KEY_REGISTERED_EMAIL, null)
    }

    fun getRegisteredPassword(): String? {
        return prefs.getString(KEY_REGISTERED_PASSWORD, null)
    }

    fun saveLanguage(languageTag: String) {
        prefs.edit().putString(KEY_LANGUAGE, languageTag).apply()
    }

    fun getLanguage(): String {
        return prefs.getString(KEY_LANGUAGE, "es") ?: "es"
    }

    fun logout() {
        prefs.edit()
            .putBoolean(KEY_LOGGED, false)
            .remove(KEY_EMAIL)
            .apply()
    }
}
