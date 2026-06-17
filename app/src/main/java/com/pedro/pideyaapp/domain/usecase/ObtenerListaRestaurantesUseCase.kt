package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.model.Restaurant
import com.pedro.pideyaapp.domain.repository.RestaurantRepository

class ObtenerListaRestaurantesUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(): List<Restaurant> = repository.getRestaurants()
}
