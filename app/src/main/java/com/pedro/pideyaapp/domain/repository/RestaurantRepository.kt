package com.pedro.pideyaapp.domain.repository

import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurants(): List<Restaurant>
    suspend fun getRestaurantsByCategory(categoryId: String): List<Restaurant>
    suspend fun getMenuByRestaurant(restaurantId: String): List<MenuProduct>
    suspend fun rateRestaurant(restaurantId: String, stars: Int)
}
