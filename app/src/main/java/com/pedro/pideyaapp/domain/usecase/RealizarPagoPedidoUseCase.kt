package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.repository.OrderRepository

class RealizarPagoPedidoUseCase(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(orderId: Int): Result<Unit> = repository.payOrder(orderId)
}
