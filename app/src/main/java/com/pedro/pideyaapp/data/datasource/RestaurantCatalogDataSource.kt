package com.pedro.pideyaapp.data.datasource

import android.content.Context
import com.pedro.pideyaapp.R
import com.pedro.pideyaapp.data.localization.localizedString

data class EventDataModel(
    val id: String,
    val name: String,
    val city: String,
    val description: String,
    val establishmentCount: Int
)

data class RestaurantDataModel(
    val id: String,
    val eventId: String,
    val name: String,
    val description: String,
    val typeLabel: String
)

data class MenuProductDataModel(
    val id: String,
    val establishmentId: String,
    val establishmentName: String,
    val name: String,
    val description: String,
    val price: Double
)

class RestaurantCatalogDataSource(
    private val context: Context
) {

    fun events(): List<EventDataModel> = listOf(
        EventDataModel(
            id = "event_emerita",
            name = context.localizedString(R.string.event_1_name),
            city = context.localizedString(R.string.event_1_city),
            description = context.localizedString(R.string.event_1_desc),
            establishmentCount = 3
        ),
        EventDataModel(
            id = "event_madcool",
            name = context.localizedString(R.string.event_2_name),
            city = context.localizedString(R.string.event_2_city),
            description = context.localizedString(R.string.event_2_desc),
            establishmentCount = 3
        ),
        EventDataModel(
            id = "event_sanisidro",
            name = context.localizedString(R.string.event_3_name),
            city = context.localizedString(R.string.event_3_city),
            description = context.localizedString(R.string.event_3_desc),
            establishmentCount = 3
        )
    )

    fun establishmentsByEvent(eventId: String): List<RestaurantDataModel> = when (eventId) {
        "event_emerita" -> listOf(
            establishment("est_1", eventId, R.string.est_1_name, R.string.est_1_desc, R.string.est_1_type),
            establishment("est_2", eventId, R.string.est_2_name, R.string.est_2_desc, R.string.est_2_type),
            establishment("est_3", eventId, R.string.est_3_name, R.string.est_3_desc, R.string.est_3_type)
        )
        "event_madcool" -> listOf(
            establishment("est_4", eventId, R.string.est_4_name, R.string.est_4_desc, R.string.est_4_type),
            establishment("est_5", eventId, R.string.est_5_name, R.string.est_5_desc, R.string.est_5_type),
            establishment("est_6", eventId, R.string.est_6_name, R.string.est_6_desc, R.string.est_6_type)
        )
        else -> listOf(
            establishment("est_7", eventId, R.string.est_7_name, R.string.est_7_desc, R.string.est_7_type),
            establishment("est_8", eventId, R.string.est_8_name, R.string.est_8_desc, R.string.est_8_type),
            establishment("est_9", eventId, R.string.est_9_name, R.string.est_9_desc, R.string.est_9_type)
        )
    }

    fun restaurants(): List<RestaurantDataModel> = events().flatMap { event ->
        establishmentsByEvent(event.id)
    }

    fun productsByEstablishment(establishmentId: String): List<MenuProductDataModel> = when (establishmentId) {
        "est_1" -> listOf(
            product("prod_1", establishmentId, R.string.est_1_name, R.string.prod_1_name, R.string.prod_1_desc, 3.50),
            product("prod_2", establishmentId, R.string.est_1_name, R.string.prod_2_name, R.string.prod_2_desc, 2.00),
            product("prod_3", establishmentId, R.string.est_1_name, R.string.prod_3_name, R.string.prod_3_desc, 6.00)
        )
        "est_2" -> listOf(
            product("prod_4", establishmentId, R.string.est_2_name, R.string.prod_4_name, R.string.prod_4_desc, 3.50),
            product("prod_5", establishmentId, R.string.est_2_name, R.string.prod_5_name, R.string.prod_5_desc, 2.50),
            product("prod_6", establishmentId, R.string.est_2_name, R.string.prod_6_name, R.string.prod_6_desc, 3.00)
        )
        "est_3" -> listOf(
            product("prod_7", establishmentId, R.string.est_3_name, R.string.prod_7_name, R.string.prod_7_desc, 3.50),
            product("prod_8", establishmentId, R.string.est_3_name, R.string.prod_8_name, R.string.prod_8_desc, 9.00),
            product("prod_9", establishmentId, R.string.est_3_name, R.string.prod_9_name, R.string.prod_9_desc, 3.00)
        )
        "est_4" -> listOf(
            product("prod_10", establishmentId, R.string.est_4_name, R.string.prod_10_name, R.string.prod_10_desc, 8.90),
            product("prod_11", establishmentId, R.string.est_4_name, R.string.prod_11_name, R.string.prod_11_desc, 3.50),
            product("prod_12", establishmentId, R.string.est_4_name, R.string.prod_12_name, R.string.prod_12_desc, 2.50)
        )
        "est_5" -> listOf(
            product("prod_13", establishmentId, R.string.est_5_name, R.string.prod_13_name, R.string.prod_13_desc, 8.50),
            product("prod_14", establishmentId, R.string.est_5_name, R.string.prod_14_name, R.string.prod_14_desc, 9.50),
            product("prod_15", establishmentId, R.string.est_5_name, R.string.prod_15_name, R.string.prod_15_desc, 1.50)
        )
        "est_6" -> listOf(
            product("prod_16", establishmentId, R.string.est_6_name, R.string.prod_16_name, R.string.prod_16_desc, 2.50),
            product("prod_17", establishmentId, R.string.est_6_name, R.string.prod_17_name, R.string.prod_17_desc, 1.50),
            product("prod_18", establishmentId, R.string.est_6_name, R.string.prod_18_name, R.string.prod_18_desc, 3.00)
        )
        "est_7" -> listOf(
            product("prod_19", establishmentId, R.string.est_7_name, R.string.prod_19_name, R.string.prod_19_desc, 4.00),
            product("prod_20", establishmentId, R.string.est_7_name, R.string.prod_20_name, R.string.prod_20_desc, 3.00),
            product("prod_21", establishmentId, R.string.est_7_name, R.string.prod_21_name, R.string.prod_21_desc, 2.00)
        )
        "est_8" -> listOf(
            product("prod_22", establishmentId, R.string.est_8_name, R.string.prod_22_name, R.string.prod_22_desc, 3.50),
            product("prod_23", establishmentId, R.string.est_8_name, R.string.prod_23_name, R.string.prod_23_desc, 2.50),
            product("prod_24", establishmentId, R.string.est_8_name, R.string.prod_24_name, R.string.prod_24_desc, 2.00)
        )
        else -> listOf(
            product("prod_25", establishmentId, R.string.est_9_name, R.string.prod_25_name, R.string.prod_25_desc, 2.50),
            product("prod_26", establishmentId, R.string.est_9_name, R.string.prod_26_name, R.string.prod_26_desc, 1.50),
            product("prod_27", establishmentId, R.string.est_9_name, R.string.prod_27_name, R.string.prod_27_desc, 3.00)
        )
    }

    fun menuByRestaurant(restaurantId: String): List<MenuProductDataModel> =
        productsByEstablishment(restaurantId)

    private fun establishment(
        id: String,
        eventId: String,
        nameRes: Int,
        descRes: Int,
        typeRes: Int
    ): RestaurantDataModel {
        return RestaurantDataModel(
            id = id,
            eventId = eventId,
            name = context.localizedString(nameRes),
            description = context.localizedString(descRes),
            typeLabel = context.localizedString(typeRes)
        )
    }

    private fun product(
        id: String,
        establishmentId: String,
        establishmentNameRes: Int,
        nameRes: Int,
        descRes: Int,
        price: Double
    ): MenuProductDataModel {
        return MenuProductDataModel(
            id = id,
            establishmentId = establishmentId,
            establishmentName = context.localizedString(establishmentNameRes),
            name = context.localizedString(nameRes),
            description = context.localizedString(descRes),
            price = price
        )
    }
}
