package com.pedro.pideyaapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_items ORDER BY establishmentName, productName")
    fun observeCart(): Flow<List<CartItemEntity>>

    @Query("SELECT * FROM cart_items ORDER BY establishmentName, productName")
    suspend fun getCartSnapshot(): List<CartItemEntity>

    @Query("SELECT * FROM cart_items WHERE productId = :productId LIMIT 1")
    suspend fun getItemByProductId(productId: String): CartItemEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: CartItemEntity)

    @Update
    suspend fun updateItem(item: CartItemEntity)

    @Query("DELETE FROM cart_items WHERE id = :itemId")
    suspend fun deleteItem(itemId: Int)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}
