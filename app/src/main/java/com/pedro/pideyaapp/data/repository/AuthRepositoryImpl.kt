package com.pedro.pideyaapp.data.repository

import com.pedro.pideyaapp.data.datasource.AuthRemoteDataSource
import com.pedro.pideyaapp.data.datasource.UserPreferencesDataSource
import com.pedro.pideyaapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val preferencesDataSource: UserPreferencesDataSource
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        return runCatching {
            remoteDataSource.login(email.trim(), password)
            preferencesDataSource.saveUser(email.trim())
            preferencesDataSource.setLogged(true)
        }
    }

    override suspend fun register(email: String, password: String): Result<Unit> {
        return runCatching {
            remoteDataSource.register(email.trim(), password)
        }
    }

    override fun isLoggedIn(): Boolean {
        return remoteDataSource.isLoggedIn() || preferencesDataSource.isLogged()
    }

    override fun getCurrentUserEmail(): String? {
        return remoteDataSource.currentUserEmail() ?: preferencesDataSource.getUser()
    }

    override fun logout() {
        remoteDataSource.logout()
        preferencesDataSource.logout()
    }
}
