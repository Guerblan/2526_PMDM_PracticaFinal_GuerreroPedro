package com.pedro.pideyaapp.data.repository

import com.pedro.pideyaapp.data.local.PedidoDao
import com.pedro.pideyaapp.data.local.PedidoEntity
import kotlinx.coroutines.flow.Flow

class PedidoRepositoryImpl(
    private val pedidoDao: PedidoDao
) {

    suspend fun insertarPedido(pedido: PedidoEntity) {
        pedidoDao.insertarPedido(pedido)
    }

    fun obtenerPedidos(): Flow<List<PedidoEntity>> {
        return pedidoDao.obtenerPedidos()
    }
}