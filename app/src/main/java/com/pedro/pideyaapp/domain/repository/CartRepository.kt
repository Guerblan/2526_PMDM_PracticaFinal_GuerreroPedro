package com.pedro.pideyaapp.domain.repository

import com.pedro.pideyaapp.data.local.CartItemEntity
import com.pedro.pideyaapp.domain.model.MenuProduct
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun observeCart(): Flow<List<CartItemEntity>>
    suspend fun addProduct(product: MenuProduct): Result<Unit>
    suspend fun removeProduct(itemId: Int)
    suspend fun clearCart()
}
