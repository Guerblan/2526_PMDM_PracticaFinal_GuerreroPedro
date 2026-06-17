package com.pedro.pideyaapp.data.mapper

import com.pedro.pideyaapp.data.local.CartItemEntity
import com.pedro.pideyaapp.data.local.OrderEntity

fun List<CartItemEntity>.toOrderEntity(
    status: String,
    createdAt: Long
): OrderEntity {
    return OrderEntity(
        establishmentName = first().establishmentName,
        itemsSummary = joinToString(separator = ", ") { "${it.productName} x${it.quantity}" },
        totalAmount = sumOf { it.unitPrice * it.quantity },
        status = status,
        createdAt = createdAt
    )
}
