package com.pedro.pideyaapp.domain.model

data class MenuProduct(
    val id: String,
    val restaurantId: String,
    val restaurantName: String,
    val name: String,
    val description: String,
    val price: Double
)
