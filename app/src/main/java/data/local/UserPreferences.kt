package com.pedro.pideyaapp.data.local

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUser(email: String) {
        prefs.edit().putString("user_email", email).apply()
    }

    fun getUser(): String? {
        return prefs.getString("user_email", null)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }

    fun isLogged(): Boolean {
        return getUser() != null
    }
}