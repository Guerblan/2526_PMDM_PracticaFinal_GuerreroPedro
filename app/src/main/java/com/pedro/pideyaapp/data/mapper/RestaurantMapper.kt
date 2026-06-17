package com.pedro.pideyaapp.data.mapper

import com.pedro.pideyaapp.data.datasource.RestaurantDataModel
import com.pedro.pideyaapp.domain.model.Restaurant

fun RestaurantDataModel.toDomain(rating: Int = this.rating): Restaurant {
    return Restaurant(
        id = id,
        name = name,
        description = description,
        categoryId = categoryId,
        categoryLabel = categoryLabel,
        deliveryTimeMinutes = deliveryTimeMinutes,
        deliveryFee = deliveryFee,
        rating = rating
    )
}
