package com.pedro.pideyaapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pedro.pideyaapp.R
import com.pedro.pideyaapp.domain.model.OrderStatus
import com.pedro.pideyaapp.ui.components.BrandTopBarTitle
import com.pedro.pideyaapp.ui.components.BottomLanguageFooter
import com.pedro.pideyaapp.ui.components.GlowPanel
import com.pedro.pideyaapp.ui.components.ScreenBackdrop
import com.pedro.pideyaapp.ui.viewmodel.PideYaViewModel
import java.text.DateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(
    viewModel: PideYaViewModel,
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onBackToRestaurants: () -> Unit
) {
    val ordersState by viewModel.ordersUiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val pendingPaymentLabel = stringResource(R.string.order_status_pending_payment)
    val paidLabel = stringResource(R.string.order_status_paid)

    LaunchedEffect(ordersState.message) {
        ordersState.message?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearOrdersMessage()
        }
    }

    ScreenBackdrop {
        Scaffold(
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { BrandTopBarTitle() },
                    navigationIcon = {
                        IconButton(onClick = onBackToRestaurants) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_to_restaurants)
                            )
                        }
                    }
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    ordersState.currentOrder?.let { currentOrder ->
                        GlowPanel(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(18.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.current_order_title),
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(text = currentOrder.restaurantName)
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = currentOrder.itemsSummary,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(
                                        R.string.order_status_label,
                                        localizedOrderStatus(currentOrder.status, pendingPaymentLabel, paidLabel)
                                    ),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = stringResource(R.string.product_price, currentOrder.totalAmount),
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                                if (isPendingPayment(currentOrder.status)) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Button(onClick = viewModel::payCurrentOrder) {
                                        Text(stringResource(R.string.pay_order_action))
                                    }
                                }
                            }
                        }
                    } ?: GlowPanel(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.no_orders_yet),
                            modifier = Modifier.padding(24.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                item {
                    Text(
                        text = stringResource(R.string.order_history_title),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                items(ordersState.history) { order ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.78f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = order.restaurantName, style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = order.itemsSummary,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(
                                        R.string.order_status_label,
                                        localizedOrderStatus(order.status, pendingPaymentLabel, paidLabel)
                                    ),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = DateFormat.getDateTimeInstance().format(Date(order.createdAt)),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    BottomLanguageFooter(
                        selectedLanguage = selectedLanguage,
                        onLanguageSelected = onLanguageSelected
                    )
                }
            }
        }
    }
}

private fun localizedOrderStatus(
    status: String,
    pendingPaymentLabel: String,
    paidLabel: String
): String {
    return when (status) {
        OrderStatus.PendingPayment -> pendingPaymentLabel
        OrderStatus.Paid -> paidLabel
        else -> status
    }
}

private fun isPendingPayment(status: String): Boolean = status == OrderStatus.PendingPayment
