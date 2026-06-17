package com.pedro.pideyaapp.domain.repository

import com.pedro.pideyaapp.data.local.OrderEntity
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun confirmOrder(): Result<Int>
    suspend fun payOrder(orderId: Int): Result<Unit>
    fun observeCurrentOrder(): Flow<OrderEntity?>
    fun observeOrderHistory(): Flow<List<OrderEntity>>
}
