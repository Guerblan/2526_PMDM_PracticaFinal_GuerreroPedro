package com.pedro.pideyaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.pedro.pideyaapp.data.local.UserPreferences
import com.pedro.pideyaapp.data.local.AppDatabase
import com.pedro.pideyaapp.data.repository.PedidoRepositoryImpl
import com.pedro.pideyaapp.domain.usecase.InsertarPedidoUseCase
import com.pedro.pideyaapp.domain.usecase.ObtenerPedidosUseCase
import com.pedro.pideyaapp.ui.viewmodel.PedidoViewModel
import com.pedro.pideyaapp.data.local.PedidoEntity

@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current
    val prefs = UserPreferences(context)
    val email = prefs.getUser()

    // Dependencias
    val database = AppDatabase.getDatabase(context)
    val repository = PedidoRepositoryImpl(database.pedidoDao())

    val insertarUseCase = InsertarPedidoUseCase(repository)
    val obtenerUseCase = ObtenerPedidosUseCase(repository)

    val viewModel = PedidoViewModel(
        insertarUseCase,
        obtenerUseCase
    )

    // Flow -> State (Compose observa cambios automáticamente)
    val pedidos by viewModel.pedidos.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(text = "Bienvenido $email")

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                val pedido = PedidoEntity(
                    nombreProducto = "Hamburguesa",
                    cantidad = 1,
                    precioTotal = 9.99,
                    fecha = System.currentTimeMillis()
                )

                viewModel.insertarPedido(pedido)
            }
        ) {
            Text("Insertar pedido de prueba")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text("Pedidos guardados:")

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {

            // ✅ NUEVO: control cuando la lista está vacía
            if (pedidos.isEmpty()) {

                item {
                    Text(
                        text = "No hay pedidos todavía",
                        modifier = Modifier.padding(8.dp)
                    )
                }

            } else {

                items(pedidos) { pedido ->

                    Text(
                        text = "${pedido.nombreProducto} - ${pedido.precioTotal}€",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}