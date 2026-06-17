package com.pedro.pideyaapp.ui.state

import com.pedro.pideyaapp.data.local.OrderEntity

data class OrdersUiState(
    val currentOrder: OrderEntity? = null,
    val history: List<OrderEntity> = emptyList(),
    val message: String? = null
)
