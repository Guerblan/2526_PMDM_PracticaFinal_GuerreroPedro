package com.pedro.pideyaapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant_ratings")
data class RestaurantRatingEntity(
    @PrimaryKey
    val restaurantId: String,
    val stars: Int
)
