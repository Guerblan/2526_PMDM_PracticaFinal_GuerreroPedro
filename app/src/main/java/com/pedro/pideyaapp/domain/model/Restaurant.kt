package com.pedro.pideyaapp.domain.model

data class Restaurant(
    val id: String,
    val name: String,
    val description: String,
    val categoryId: String,
    val categoryLabel: String,
    val deliveryTimeMinutes: Int,
    val deliveryFee: Double,
    val rating: Int
)
