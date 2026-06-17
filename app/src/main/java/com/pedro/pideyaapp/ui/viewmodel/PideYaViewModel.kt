package com.pedro.pideyaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pedro.pideyaapp.data.AppContainer
import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.domain.model.OrderStatus
import com.pedro.pideyaapp.ui.state.AuthUiState
import com.pedro.pideyaapp.ui.state.CartUiState
import com.pedro.pideyaapp.ui.state.CatalogUiState
import com.pedro.pideyaapp.ui.state.OrdersUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PideYaViewModel(
    private val container: AppContainer
) : ViewModel() {

    private val _authUiState = MutableStateFlow(
        AuthUiState(
            isLogged = container.authRepositoryFacade.isLoggedIn(),
            email = container.authRepositoryFacade.getCurrentUserEmail().orEmpty()
        )
    )
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    private val _catalogUiState = MutableStateFlow(
        CatalogUiState(selectedCategory = container.userPreferences.getSelectedCategory())
    )
    val catalogUiState: StateFlow<CatalogUiState> = _catalogUiState.asStateFlow()

    private val _cartMessage = MutableStateFlow<String?>(null)
    val cartUiState: StateFlow<CartUiState> = container.cartRepositoryFacade.observeCart()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
        .let { cartFlow ->
            MutableStateFlow(CartUiState()).also { state ->
                viewModelScope.launch {
                    cartFlow.collect { items ->
                        state.value = CartUiState(
                            items = items,
                            total = items.sumOf { it.unitPrice * it.quantity },
                            message = _cartMessage.value
                        )
                    }
                }
                viewModelScope.launch {
                    _cartMessage.collect { message ->
                        state.value = state.value.copy(message = message)
                    }
                }
            }
        }

    private val _ordersUiState = MutableStateFlow(OrdersUiState())
    val ordersUiState: StateFlow<OrdersUiState> = _ordersUiState.asStateFlow()

    init {
        observeOrders()
        refreshRestaurants()
    }

    fun onEmailChange(email: String) {
        _authUiState.value = _authUiState.value.copy(
            email = email,
            message = null,
            registrationCompleted = false
        )
    }

    fun onPasswordChange(password: String) {
        _authUiState.value = _authUiState.value.copy(
            password = password,
            message = null,
            registrationCompleted = false
        )
    }

    fun clearAuthMessage() {
        _authUiState.value = _authUiState.value.copy(message = null)
    }

    fun login() {
        viewModelScope.launch {
            _authUiState.value = _authUiState.value.copy(isLoading = true, message = null)
            val result = container.loginUseCase(
                _authUiState.value.email,
                _authUiState.value.password
            )
            _authUiState.value = if (result.isSuccess) {
                AuthUiState(
                    email = container.authRepositoryFacade.getCurrentUserEmail().orEmpty(),
                    password = "",
                    isLogged = true,
                    isLoading = false,
                    message = null
                )
            } else {
                _authUiState.value.copy(
                    isLoading = false,
                    message = result.exceptionOrNull()?.message
                )
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            _authUiState.value = _authUiState.value.copy(isLoading = true, message = null)
            val result = container.registroUseCase(
                _authUiState.value.email,
                _authUiState.value.password
            )
            _authUiState.value = _authUiState.value.copy(
                isLoading = false,
                message = result.fold(
                    onSuccess = { null },
                    onFailure = { it.message }
                ),
                registrationCompleted = result.isSuccess
            )
        }
    }

    fun logout() {
        container.authRepositoryFacade.logout()
        _authUiState.value = AuthUiState()
    }

    fun refreshRestaurants() {
        viewModelScope.launch {
            val currentState = _catalogUiState.value
            val restaurants = restaurantsForCategory(currentState.selectedCategory)
            val selectedRestaurantName = currentState.selectedRestaurantId
                ?.let { selectedId -> restaurants.firstOrNull { it.id == selectedId }?.name }
                ?: currentState.selectedRestaurantName

            _catalogUiState.value = currentState.copy(
                restaurants = restaurants,
                selectedRestaurantName = selectedRestaurantName
            )
        }
    }

    fun onLanguageChanged() {
        viewModelScope.launch {
            val currentState = _catalogUiState.value
            val restaurants = restaurantsForCategory(currentState.selectedCategory)
            val selectedRestaurantId = currentState.selectedRestaurantId
            val selectedRestaurantName = selectedRestaurantId
                ?.let { selectedId -> restaurants.firstOrNull { it.id == selectedId }?.name }
                ?: currentState.selectedRestaurantName
            val menu = if (selectedRestaurantId != null) {
                container.verMenuRestauranteUseCase(selectedRestaurantId)
            } else {
                currentState.menu
            }

            _catalogUiState.value = currentState.copy(
                restaurants = restaurants,
                selectedRestaurantName = selectedRestaurantName,
                menu = menu
            )
        }
    }

    fun selectCategory(categoryId: String) {
        container.userPreferences.saveSelectedCategory(categoryId)
        _catalogUiState.value = _catalogUiState.value.copy(selectedCategory = categoryId)
        refreshRestaurants()
    }

    fun loadMenu(restaurantId: String, restaurantName: String) {
        viewModelScope.launch {
            val menu = container.verMenuRestauranteUseCase(restaurantId)
            val resolvedRestaurantName = _catalogUiState.value.restaurants
                .firstOrNull { it.id == restaurantId }
                ?.name
                ?: restaurantName
            _catalogUiState.value = _catalogUiState.value.copy(
                selectedRestaurantId = restaurantId,
                selectedRestaurantName = resolvedRestaurantName,
                menu = menu
            )
        }
    }

    fun rateRestaurant(restaurantId: String, stars: Int) {
        viewModelScope.launch {
            container.calificarConEstrellasRestauranteUseCase(restaurantId, stars)
            refreshRestaurants()
            _catalogUiState.value.selectedRestaurantId?.let { selectedId ->
                if (selectedId == restaurantId) {
                    loadMenu(restaurantId, _catalogUiState.value.selectedRestaurantName)
                }
            }
        }
    }

    fun addProductToCart(product: MenuProduct) {
        viewModelScope.launch {
            val result = container.anadirProductoCarritoUseCase(product)
            _cartMessage.value = result.exceptionOrNull()?.message
        }
    }

    fun clearCartMessage() {
        _cartMessage.value = null
    }

    fun removeCartItem(itemId: Int) {
        viewModelScope.launch {
            container.eliminarProductoCarritoUseCase(itemId)
        }
    }

    fun confirmOrder(onSuccess: (Int) -> Unit) {
        viewModelScope.launch {
            val result = container.confirmarPedidoUseCase()
            result.onSuccess { orderId ->
                _ordersUiState.value = _ordersUiState.value.copy(message = null)
                onSuccess(orderId)
            }.onFailure {
                _ordersUiState.value = _ordersUiState.value.copy(message = it.message)
            }
        }
    }

    fun payCurrentOrder() {
        val orderId = _ordersUiState.value.currentOrder?.id ?: return
        viewModelScope.launch {
            val result = container.realizarPagoPedidoUseCase(orderId)
            _ordersUiState.value = _ordersUiState.value.copy(
                message = result.exceptionOrNull()?.message
            )
        }
    }

    fun clearOrdersMessage() {
        _ordersUiState.value = _ordersUiState.value.copy(message = null)
    }

    private fun observeOrders() {
        viewModelScope.launch {
            container.consultarEstadoPedidoUseCase().collect { currentOrder ->
                _ordersUiState.value = _ordersUiState.value.copy(
                    currentOrder = currentOrder?.copy(status = normalizeOrderStatus(currentOrder.status))
                )
            }
        }
        viewModelScope.launch {
            container.consultarHistorialPedidosUseCase().collect { history ->
                _ordersUiState.value = _ordersUiState.value.copy(
                    history = history.map { order -> order.copy(status = normalizeOrderStatus(order.status)) }
                )
            }
        }
    }

    private suspend fun restaurantsForCategory(category: String) =
        if (category == "all") {
            container.obtenerListaRestaurantesUseCase()
        } else {
            container.obtenerRestaurantesPorCategoriaUseCase(category)
        }

    private fun normalizeOrderStatus(status: String): String {
        val normalized = status.trim().lowercase()
        return when {
            normalized == OrderStatus.PendingPayment -> OrderStatus.PendingPayment
            normalized == OrderStatus.Paid -> OrderStatus.Paid
            "pending" in normalized || "pendiente" in normalized -> OrderStatus.PendingPayment
            "paid" in normalized || "pagado" in normalized -> OrderStatus.Paid
            else -> status
        }
    }
}

class PideYaViewModelFactory(
    private val container: AppContainer
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PideYaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PideYaViewModel(container) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
