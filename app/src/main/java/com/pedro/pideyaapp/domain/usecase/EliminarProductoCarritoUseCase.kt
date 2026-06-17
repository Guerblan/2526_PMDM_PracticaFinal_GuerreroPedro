package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.repository.CartRepository

class EliminarProductoCarritoUseCase(
    private val repository: CartRepository
) {
    suspend operator fun invoke(itemId: Int) {
        repository.removeProduct(itemId)
    }
}
