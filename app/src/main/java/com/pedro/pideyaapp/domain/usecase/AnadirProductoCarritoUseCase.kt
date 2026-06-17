package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.repository.CartRepository

class AnadirProductoCarritoUseCase(
    private val repository: CartRepository
) {
    suspend operator fun invoke(product: MenuProduct): Result<Unit> {
        return repository.addProduct(product)
    }
}
