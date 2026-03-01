package com.pedro.pideyaapp.data.repository

import android.content.Context
import com.pedro.pideyaapp.data.local.UserPreferences
import com.pedro.pideyaapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val context: Context
) : AuthRepository {

    // ✅ LOGIN
    override fun login(email: String, password: String): Boolean {

        val prefs = UserPreferences(context)

        val ok = prefs.isRegistered(email, password)

        if (ok) {
            prefs.setLogged(true)
        }

        return ok
    }

    // ✅ REGISTRO (LO QUE FALTABA)
    override fun register(email: String, password: String) {

        val prefs = UserPreferences(context)

        // guardamos usuario en local
        prefs.saveUser(email, password)
    }
}