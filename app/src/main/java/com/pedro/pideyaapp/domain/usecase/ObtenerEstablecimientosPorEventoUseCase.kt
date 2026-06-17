package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.model.Restaurant
import com.pedro.pideyaapp.domain.repository.RestaurantRepository

class ObtenerEstablecimientosPorEventoUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(eventId: String): List<Restaurant> {
        return repository.getEstablishmentsByEvent(eventId)
    }
}
