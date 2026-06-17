package com.pedro.pideyaapp.domain.repository

import com.pedro.pideyaapp.domain.model.Event
import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.model.Restaurant

interface RestaurantRepository {
    suspend fun getEvents(): List<Event>
    suspend fun getEstablishmentsByEvent(eventId: String): List<Restaurant>
    suspend fun getProductsByEstablishment(establishmentId: String): List<MenuProduct>
}
