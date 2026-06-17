package com.pedro.pideyaapp.domain.usecase

import com.pedro.pideyaapp.domain.repository.OrderRepository

class ConfirmarPedidoUseCase(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(): Result<Int> = repository.confirmOrder()
}
