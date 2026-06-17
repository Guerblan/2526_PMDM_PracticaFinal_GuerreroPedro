package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.model.Event
import com.pedro.pideyaapp.domain.repository.EventCatalogRepository

class ObtenerEventosUseCase(
    private val repository: EventCatalogRepository
) {
    suspend operator fun invoke(): List<Event> = repository.getEvents()
}
