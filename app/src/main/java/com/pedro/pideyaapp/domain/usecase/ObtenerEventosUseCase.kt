package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.model.Event
import com.pedro.pideyaapp.domain.repository.RestaurantRepository

class ObtenerEventosUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(): List<Event> = repository.getEvents()
}
