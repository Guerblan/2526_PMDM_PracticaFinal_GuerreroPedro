package com.pedro.pideyaapp.data.repository

import com.pedro.pideyaapp.data.datasource.RestaurantCatalogDataSource
import com.pedro.pideyaapp.data.local.RestaurantRatingDao
import com.pedro.pideyaapp.data.local.RestaurantRatingEntity
import com.pedro.pideyaapp.data.mapper.toDomain
import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.model.Restaurant
import com.pedro.pideyaapp.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl(
    private val catalog: RestaurantCatalogDataSource,
    private val ratingDao: RestaurantRatingDao
) : RestaurantRepository {

    override suspend fun getRestaurants(): List<Restaurant> {
        return mergeRatings(catalog.restaurants())
    }

    override suspend fun getRestaurantsByCategory(categoryId: String): List<Restaurant> {
        val restaurants = mergeRatings(catalog.restaurants())
        return if (categoryId == "all") restaurants else restaurants.filter { it.categoryId == categoryId }
    }

    override suspend fun getMenuByRestaurant(restaurantId: String): List<MenuProduct> {
        return catalog.menuByRestaurant(restaurantId).map { it.toDomain() }
    }

    override suspend fun rateRestaurant(restaurantId: String, stars: Int) {
        ratingDao.upsertRating(
            RestaurantRatingEntity(
                restaurantId = restaurantId,
                stars = stars.coerceIn(1, 5)
            )
        )
    }

    private suspend fun mergeRatings(restaurants: List<com.pedro.pideyaapp.data.datasource.RestaurantDataModel>): List<Restaurant> {
        val ratings = ratingDao.getRatings().associateBy { it.restaurantId }
        return restaurants.map { restaurant ->
            restaurant.toDomain(rating = ratings[restaurant.id]?.stars ?: restaurant.rating)
        }
    }
}
