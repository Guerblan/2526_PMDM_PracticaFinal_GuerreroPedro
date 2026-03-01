package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.data.local.PedidoEntity
import com.pedro.pideyaapp.data.repository.PedidoRepositoryImpl

class InsertarPedidoUseCase(
    private val repository: PedidoRepositoryImpl
) {

    suspend operator fun invoke(pedido: PedidoEntity) {
        repository.insertarPedido(pedido)
    }
}