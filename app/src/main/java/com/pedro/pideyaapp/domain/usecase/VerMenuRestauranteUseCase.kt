package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.repository.RestaurantRepository

class VerMenuRestauranteUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(restaurantId: String): List<MenuProduct> {
        return repository.getMenuByRestaurant(restaurantId)
    }
}
