package com.pedro.pideyaapp.data.datasource

import com.pedro.pideyaapp.data.local.CartDao
import com.pedro.pideyaapp.data.local.CartItemEntity
import kotlinx.coroutines.flow.Flow

class CartLocalDataSource(
    private val cartDao: CartDao
) {
    fun observeCart(): Flow<List<CartItemEntity>> = cartDao.observeCart()
    suspend fun getCartSnapshot(): List<CartItemEntity> = cartDao.getCartSnapshot()
    suspend fun getItemByProductId(productId: String): CartItemEntity? = cartDao.getItemByProductId(productId)
    suspend fun insertItem(item: CartItemEntity) = cartDao.insertItem(item)
    suspend fun updateItem(item: CartItemEntity) = cartDao.updateItem(item)
    suspend fun deleteItem(itemId: Int) = cartDao.deleteItem(itemId)
    suspend fun clearCart() = cartDao.clearCart()
}
