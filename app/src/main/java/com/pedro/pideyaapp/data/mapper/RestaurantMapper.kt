package com.pedro.pideyaapp.data.mapper

import com.pedro.pideyaapp.data.datasource.RestaurantDataModel
import com.pedro.pideyaapp.domain.model.Restaurant

fun RestaurantDataModel.toDomain(): Restaurant {
    return Restaurant(
        id = id,
        eventId = eventId,
        name = name,
        description = description,
        typeLabel = typeLabel
    )
}
