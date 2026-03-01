package com.pedro.pideyaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedro.pideyaapp.data.local.PedidoEntity
import com.pedro.pideyaapp.domain.usecase.InsertarPedidoUseCase
import com.pedro.pideyaapp.domain.usecase.ObtenerPedidosUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PedidoViewModel(
    private val insertarPedidoUseCase: InsertarPedidoUseCase,
    obtenerPedidosUseCase: ObtenerPedidosUseCase
) : ViewModel() {

    val pedidos = obtenerPedidosUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertarPedido(pedido: PedidoEntity) {
        viewModelScope.launch {
            insertarPedidoUseCase(pedido)
        }
    }
}