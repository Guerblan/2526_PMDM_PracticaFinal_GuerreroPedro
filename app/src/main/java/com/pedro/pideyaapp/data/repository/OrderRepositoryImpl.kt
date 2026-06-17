package com.pedro.pideyaapp.data.repository

import com.pedro.pideyaapp.R
import com.pedro.pideyaapp.data.datasource.CartLocalDataSource
import com.pedro.pideyaapp.data.datasource.OrderLocalDataSource
import com.pedro.pideyaapp.data.mapper.toOrderEntity
import com.pedro.pideyaapp.data.local.OrderEntity
import com.pedro.pideyaapp.domain.model.OrderStatus
import com.pedro.pideyaapp.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(
    private val cartDataSource: CartLocalDataSource,
    private val orderDataSource: OrderLocalDataSource,
    private val stringsProvider: StringsProvider
) : OrderRepository {

    override suspend fun confirmOrder(): Result<Int> {
        val cartItems = cartDataSource.getCartSnapshot()
        if (cartItems.isEmpty()) {
            return Result.failure(IllegalStateException(stringsProvider.get(R.string.error_empty_cart)))
        }

        val orderId = orderDataSource.insertOrder(
            cartItems.toOrderEntity(
                status = OrderStatus.PendingPayment,
                createdAt = System.currentTimeMillis()
            )
        ).toInt()
        cartDataSource.clearCart()
        return Result.success(orderId)
    }

    override suspend fun payOrder(orderId: Int): Result<Unit> {
        val latest = orderDataSource.getLatestOrder()
            ?: return Result.failure(IllegalStateException(stringsProvider.get(R.string.error_order_not_found)))
        if (latest.id != orderId) {
            return Result.failure(IllegalStateException(stringsProvider.get(R.string.error_order_not_found)))
        }
        orderDataSource.updateOrderStatus(orderId, OrderStatus.Paid)
        return Result.success(Unit)
    }

    override fun observeCurrentOrder(): Flow<OrderEntity?> = orderDataSource.observeLatestOrder()

    override fun observeOrderHistory(): Flow<List<OrderEntity>> = orderDataSource.observeOrders()
}
