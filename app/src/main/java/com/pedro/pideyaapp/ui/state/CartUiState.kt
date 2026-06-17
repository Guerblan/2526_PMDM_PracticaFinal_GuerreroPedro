package com.pedro.pideyaapp.ui.state

import com.pedro.pideyaapp.data.local.CartItemEntity

data class CartUiState(
    val items: List<CartItemEntity> = emptyList(),
    val total: Double = 0.0,
    val message: String? = null
)
