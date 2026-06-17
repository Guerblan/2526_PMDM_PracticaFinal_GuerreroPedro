package com.pedro.pideyaapp.ui.state

import com.pedro.pideyaapp.domain.model.Event
import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.model.Restaurant

data class CatalogUiState(
    val events: List<Event> = emptyList(),
    val selectedEventId: String? = null,
    val selectedEventName: String = "",
    val establishments: List<Restaurant> = emptyList(),
    val selectedEstablishmentId: String? = null,
    val selectedEstablishmentName: String = "",
    val products: List<MenuProduct> = emptyList()
)
