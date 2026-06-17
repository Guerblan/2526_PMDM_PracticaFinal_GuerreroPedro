package com.pedro.pideyaapp.data.datasource

import com.pedro.pideyaapp.data.local.OrderDao
import com.pedro.pideyaapp.data.local.OrderEntity
import kotlinx.coroutines.flow.Flow

class OrderLocalDataSource(
    private val orderDao: OrderDao
) {
    suspend fun insertOrder(order: OrderEntity): Long = orderDao.insertOrder(order)
    fun observeOrders(): Flow<List<OrderEntity>> = orderDao.observeOrders()
    fun observeLatestOrder(): Flow<OrderEntity?> = orderDao.observeLatestOrder()
    suspend fun getLatestOrder(): OrderEntity? = orderDao.getLatestOrder()
    suspend fun updateOrderStatus(orderId: Int, status: String) = orderDao.updateOrderStatus(orderId, status)
}
