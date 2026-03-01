package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.data.local.PedidoEntity
import com.pedro.pideyaapp.data.repository.PedidoRepositoryImpl
import kotlinx.coroutines.flow.Flow

class ObtenerPedidosUseCase(
    private val repository: PedidoRepositoryImpl
) {

    operator fun invoke(): Flow<List<PedidoEntity>> {
        return repository.obtenerPedidos()
    }
}