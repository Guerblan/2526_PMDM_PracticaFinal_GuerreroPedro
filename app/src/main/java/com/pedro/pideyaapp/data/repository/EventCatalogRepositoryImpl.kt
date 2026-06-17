package com.pedro.pideyaapp.data.repository

import com.pedro.pideyaapp.data.mapper.toDomain
import com.pedro.pideyaapp.data.datasource.EventCatalogDataSource
import com.pedro.pideyaapp.domain.model.Establishment
import com.pedro.pideyaapp.domain.model.Event
import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.repository.EventCatalogRepository

class EventCatalogRepositoryImpl(
    private val catalog: EventCatalogDataSource
) : EventCatalogRepository {

    override suspend fun getEvents(): List<Event> {
        return catalog.events().map { event ->
            Event(
                id = event.id,
                name = event.name,
                city = event.city,
                description = event.description,
                establishmentCount = event.establishmentCount
            )
        }
    }

    override suspend fun getEstablishmentsByEvent(eventId: String): List<Establishment> {
        return catalog.establishmentsByEvent(eventId).map { it.toDomain() }
    }

    override suspend fun getProductsByEstablishment(establishmentId: String): List<MenuProduct> {
        return catalog.productsByEstablishment(establishmentId).map { it.toDomain() }
    }
}
