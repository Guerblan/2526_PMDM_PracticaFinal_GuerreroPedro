package com.pedro.pideyaapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val establishmentName: String,
    val itemsSummary: String,
    val totalAmount: Double,
    val status: String,
    val createdAt: Long
)
