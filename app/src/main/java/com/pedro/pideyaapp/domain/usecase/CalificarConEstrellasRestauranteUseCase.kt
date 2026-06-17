package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.repository.RestaurantRepository

class CalificarConEstrellasRestauranteUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(restaurantId: String, stars: Int) {
        repository.rateRestaurant(restaurantId, stars)
    }
}
