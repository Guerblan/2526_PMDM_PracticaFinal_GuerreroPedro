package com.pedro.pideyaapp.data.datasource

import android.content.Context
import com.pedro.pideyaapp.R
import com.pedro.pideyaapp.data.localization.localizedString

data class RestaurantDataModel(
    val id: String,
    val name: String,
    val description: String,
    val categoryId: String,
    val categoryLabel: String,
    val deliveryTimeMinutes: Int,
    val deliveryFee: Double,
    val rating: Int
)

data class MenuProductDataModel(
    val id: String,
    val restaurantId: String,
    val restaurantName: String,
    val name: String,
    val description: String,
    val price: Double
)

class RestaurantCatalogDataSource(
    private val context: Context
) {

    fun categories(): List<String> = listOf("all", "burger", "pizza", "sushi", "healthy")

    fun restaurants(): List<RestaurantDataModel> = listOf(
        RestaurantDataModel(
            id = "rest_1",
            name = context.localizedString(R.string.restaurant_1_name),
            description = context.localizedString(R.string.restaurant_1_desc),
            categoryId = "burger",
            categoryLabel = context.localizedString(R.string.category_burger),
            deliveryTimeMinutes = 25,
            deliveryFee = 2.49,
            rating = 4
        ),
        RestaurantDataModel(
            id = "rest_2",
            name = context.localizedString(R.string.restaurant_2_name),
            description = context.localizedString(R.string.restaurant_2_desc),
            categoryId = "pizza",
            categoryLabel = context.localizedString(R.string.category_pizza),
            deliveryTimeMinutes = 30,
            deliveryFee = 1.99,
            rating = 5
        ),
        RestaurantDataModel(
            id = "rest_3",
            name = context.localizedString(R.string.restaurant_3_name),
            description = context.localizedString(R.string.restaurant_3_desc),
            categoryId = "sushi",
            categoryLabel = context.localizedString(R.string.category_sushi),
            deliveryTimeMinutes = 35,
            deliveryFee = 3.50,
            rating = 4
        ),
        RestaurantDataModel(
            id = "rest_4",
            name = context.localizedString(R.string.restaurant_4_name),
            description = context.localizedString(R.string.restaurant_4_desc),
            categoryId = "healthy",
            categoryLabel = context.localizedString(R.string.category_healthy),
            deliveryTimeMinutes = 20,
            deliveryFee = 1.50,
            rating = 5
        )
    )

    fun menuByRestaurant(restaurantId: String): List<MenuProductDataModel> = when (restaurantId) {
        "rest_1" -> listOf(
            menuProduct("menu_1", restaurantId, R.string.restaurant_1_name, R.string.menu_1_name, R.string.menu_1_desc, 9.90),
            menuProduct("menu_2", restaurantId, R.string.restaurant_1_name, R.string.menu_2_name, R.string.menu_2_desc, 4.25)
        )
        "rest_2" -> listOf(
            menuProduct("menu_3", restaurantId, R.string.restaurant_2_name, R.string.menu_3_name, R.string.menu_3_desc, 11.50),
            menuProduct("menu_4", restaurantId, R.string.restaurant_2_name, R.string.menu_4_name, R.string.menu_4_desc, 8.10)
        )
        "rest_3" -> listOf(
            menuProduct("menu_5", restaurantId, R.string.restaurant_3_name, R.string.menu_5_name, R.string.menu_5_desc, 14.00),
            menuProduct("menu_6", restaurantId, R.string.restaurant_3_name, R.string.menu_6_name, R.string.menu_6_desc, 6.50)
        )
        else -> listOf(
            menuProduct("menu_7", restaurantId, R.string.restaurant_4_name, R.string.menu_7_name, R.string.menu_7_desc, 10.20),
            menuProduct("menu_8", restaurantId, R.string.restaurant_4_name, R.string.menu_8_name, R.string.menu_8_desc, 5.75)
        )
    }

    private fun menuProduct(
        id: String,
        restaurantId: String,
        restaurantNameRes: Int,
        nameRes: Int,
        descRes: Int,
        price: Double
    ): MenuProductDataModel {
        return MenuProductDataModel(
            id = id,
            restaurantId = restaurantId,
            restaurantName = context.localizedString(restaurantNameRes),
            name = context.localizedString(nameRes),
            description = context.localizedString(descRes),
            price = price
        )
    }
}
