package com.pedro.pideyaapp.domain.model

data class Event(
    val id: String,
    val name: String,
    val city: String,
    val description: String,
    val establishmentCount: Int
)
