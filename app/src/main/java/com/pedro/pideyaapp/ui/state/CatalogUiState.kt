package com.pedro.pideyaapp.ui.state

import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.model.Restaurant

data class CatalogUiState(
    val selectedCategory: String = "all",
    val restaurants: List<Restaurant> = emptyList(),
    val selectedRestaurantId: String? = null,
    val selectedRestaurantName: String = "",
    val menu: List<MenuProduct> = emptyList()
)
