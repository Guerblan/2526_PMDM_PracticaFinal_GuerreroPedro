package com.pedro.pideyaapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPedido(pedido: PedidoEntity)

    @Query("SELECT * FROM pedidos ORDER BY id DESC")
    fun obtenerPedidos(): Flow<List<PedidoEntity>>
}