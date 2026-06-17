package com.pedro.pideyaapp.data

import android.content.Context
import com.pedro.pideyaapp.data.datasource.AuthRemoteDataSource
import com.pedro.pideyaapp.data.datasource.CartLocalDataSource
import com.pedro.pideyaapp.data.datasource.OrderLocalDataSource
import com.pedro.pideyaapp.data.datasource.RestaurantCatalogDataSource
import com.pedro.pideyaapp.data.datasource.UserPreferencesDataSource
import com.pedro.pideyaapp.data.local.AppDatabase
import com.pedro.pideyaapp.data.local.UserPreferences
import com.pedro.pideyaapp.data.repository.AuthRepositoryImpl
import com.pedro.pideyaapp.data.repository.CartRepositoryImpl
import com.pedro.pideyaapp.data.repository.OrderRepositoryImpl
import com.pedro.pideyaapp.data.repository.RestaurantRepositoryImpl
import com.pedro.pideyaapp.data.repository.StringsProvider
import com.pedro.pideyaapp.domain.usecase.AnadirProductoCarritoUseCase
import com.pedro.pideyaapp.domain.usecase.ConfirmarPedidoUseCase
import com.pedro.pideyaapp.domain.usecase.ConsultarEstadoPedidoUseCase
import com.pedro.pideyaapp.domain.usecase.ConsultarHistorialPedidosUseCase
import com.pedro.pideyaapp.domain.usecase.EliminarProductoCarritoUseCase
import com.pedro.pideyaapp.domain.usecase.LoginUseCase
import com.pedro.pideyaapp.domain.usecase.ObtenerEstablecimientosPorEventoUseCase
import com.pedro.pideyaapp.domain.usecase.ObtenerEventosUseCase
import com.pedro.pideyaapp.domain.usecase.ObtenerProductosEstablecimientoUseCase
import com.pedro.pideyaapp.domain.usecase.RealizarPagoPedidoUseCase
import com.pedro.pideyaapp.domain.usecase.RegistroUseCase

class AppContainer(context: Context) {

    private val appContext = context.applicationContext
    private val database = AppDatabase.getDatabase(appContext)
    private val stringsProvider = StringsProvider(appContext)
    private val userPreferencesSource = UserPreferencesDataSource(UserPreferences(appContext))
    private val authRemoteDataSource = AuthRemoteDataSource(appContext)
    private val cartLocalDataSource = CartLocalDataSource(database.cartDao())
    private val orderLocalDataSource = OrderLocalDataSource(database.orderDao())
    private val authRepository = AuthRepositoryImpl(
        remoteDataSource = authRemoteDataSource,
        preferencesDataSource = userPreferencesSource
    )
    private val restaurantRepository = RestaurantRepositoryImpl(
        catalog = RestaurantCatalogDataSource(appContext)
    )
    private val cartRepository = CartRepositoryImpl(
        cartDataSource = cartLocalDataSource,
        stringsProvider = stringsProvider
    )
    private val orderRepository = OrderRepositoryImpl(
        cartDataSource = cartLocalDataSource,
        orderDataSource = orderLocalDataSource,
        stringsProvider = stringsProvider
    )

    val userPreferences = userPreferencesSource
    val loginUseCase = LoginUseCase(authRepository)
    val registroUseCase = RegistroUseCase(authRepository)
    val obtenerEventosUseCase = ObtenerEventosUseCase(restaurantRepository)
    val obtenerEstablecimientosPorEventoUseCase = ObtenerEstablecimientosPorEventoUseCase(restaurantRepository)
    val obtenerProductosEstablecimientoUseCase = ObtenerProductosEstablecimientoUseCase(restaurantRepository)
    val anadirProductoCarritoUseCase = AnadirProductoCarritoUseCase(cartRepository)
    val eliminarProductoCarritoUseCase = EliminarProductoCarritoUseCase(cartRepository)
    val confirmarPedidoUseCase = ConfirmarPedidoUseCase(orderRepository)
    val realizarPagoPedidoUseCase = RealizarPagoPedidoUseCase(orderRepository)
    val consultarEstadoPedidoUseCase = ConsultarEstadoPedidoUseCase(orderRepository)
    val consultarHistorialPedidosUseCase = ConsultarHistorialPedidosUseCase(orderRepository)
    val authRepositoryFacade = authRepository
    val cartRepositoryFacade = cartRepository
}
