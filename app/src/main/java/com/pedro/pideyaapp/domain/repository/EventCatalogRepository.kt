package com.pedro.pideyaapp.domain.repository

import com.pedro.pideyaapp.domain.model.Establishment
import com.pedro.pideyaapp.domain.model.Event
import com.pedro.pideyaapp.domain.model.MenuProduct

interface EventCatalogRepository {
    suspend fun getEvents(): List<Event>
    suspend fun getEstablishmentsByEvent(eventId: String): List<Establishment>
    suspend fun getProductsByEstablishment(establishmentId: String): List<MenuProduct>
}
