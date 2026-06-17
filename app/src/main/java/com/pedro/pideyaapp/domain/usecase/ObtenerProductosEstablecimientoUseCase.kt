package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.repository.RestaurantRepository

class ObtenerProductosEstablecimientoUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(establishmentId: String): List<MenuProduct> {
        return repository.getProductsByEstablishment(establishmentId)
    }
}
