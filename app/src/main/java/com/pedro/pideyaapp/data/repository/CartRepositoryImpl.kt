package com.pedro.pideyaapp.data.repository

import com.pedro.pideyaapp.R
import com.pedro.pideyaapp.data.datasource.CartLocalDataSource
import com.pedro.pideyaapp.data.local.CartItemEntity
import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val cartDataSource: CartLocalDataSource,
    private val stringsProvider: StringsProvider
) : CartRepository {

    override fun observeCart(): Flow<List<CartItemEntity>> = cartDataSource.observeCart()

    override suspend fun addProduct(product: MenuProduct): Result<Unit> {
        val currentCart = cartDataSource.getCartSnapshot()
        val currentRestaurantId = currentCart.firstOrNull()?.restaurantId

        if (currentRestaurantId != null && currentRestaurantId != product.restaurantId) {
            return Result.failure(
                IllegalStateException(stringsProvider.get(R.string.error_single_restaurant_cart))
            )
        }

        val existingItem = cartDataSource.getItemByProductId(product.id)
        if (existingItem == null) {
            cartDataSource.insertItem(
                CartItemEntity(
                    productId = product.id,
                    restaurantId = product.restaurantId,
                    restaurantName = product.restaurantName,
                    productName = product.name,
                    unitPrice = product.price,
                    quantity = 1
                )
            )
        } else {
            cartDataSource.updateItem(existingItem.copy(quantity = existingItem.quantity + 1))
        }

        return Result.success(Unit)
    }

    override suspend fun removeProduct(itemId: Int) {
        cartDataSource.deleteItem(itemId)
    }

    override suspend fun clearCart() {
        cartDataSource.clearCart()
    }
}
