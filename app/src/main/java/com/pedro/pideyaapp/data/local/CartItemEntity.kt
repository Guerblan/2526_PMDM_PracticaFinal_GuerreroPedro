package com.pedro.pideyaapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: String,
    val establishmentId: String,
    val establishmentName: String,
    val productName: String,
    val unitPrice: Double,
    val quantity: Int
)
