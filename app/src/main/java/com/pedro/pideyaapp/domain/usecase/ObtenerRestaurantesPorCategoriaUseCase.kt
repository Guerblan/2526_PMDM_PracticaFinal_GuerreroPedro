package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.model.Restaurant
import com.pedro.pideyaapp.domain.repository.RestaurantRepository

class ObtenerRestaurantesPorCategoriaUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(categoryId: String): List<Restaurant> {
        return repository.getRestaurantsByCategory(categoryId)
    }
}
