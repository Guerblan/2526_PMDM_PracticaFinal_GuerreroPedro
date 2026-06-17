package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.repository.AuthRepository

class RegistroUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.register(email, password)
    }
}
