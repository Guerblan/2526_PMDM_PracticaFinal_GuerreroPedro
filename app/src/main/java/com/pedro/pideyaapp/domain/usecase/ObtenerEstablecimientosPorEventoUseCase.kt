package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.model.Establishment
import com.pedro.pideyaapp.domain.repository.EventCatalogRepository

class ObtenerEstablecimientosPorEventoUseCase(
    private val repository: EventCatalogRepository
) {
    suspend operator fun invoke(eventId: String): List<Establishment> {
        return repository.getEstablishmentsByEvent(eventId)
    }
}
