package com.pedro.pideyaapp.data.datasource

import com.pedro.pideyaapp.data.local.UserPreferences

class UserPreferencesDataSource(
    private val preferences: UserPreferences
) {
    fun saveUser(email: String) = preferences.saveUser(email)
    fun saveRegisteredUser(email: String, password: String) =
        preferences.saveRegisteredUser(email, password)
    fun getRegisteredEmail(): String? = preferences.getRegisteredEmail()
    fun getRegisteredPassword(): String? = preferences.getRegisteredPassword()
    fun setLogged(value: Boolean) = preferences.setLogged(value)
    fun isLogged(): Boolean = preferences.isLogged()
    fun getUser(): String? = preferences.getUser()
    fun saveLanguage(languageTag: String) = preferences.saveLanguage(languageTag)
    fun getLanguage(): String = preferences.getLanguage()
    fun logout() = preferences.logout()
}
