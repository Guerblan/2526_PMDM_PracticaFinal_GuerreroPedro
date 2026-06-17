package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.data.local.OrderEntity
import com.pedro.pideyaapp.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class ConsultarHistorialPedidosUseCase(
    private val repository: OrderRepository
) {
    operator fun invoke(): Flow<List<OrderEntity>> = repository.observeOrderHistory()
}
