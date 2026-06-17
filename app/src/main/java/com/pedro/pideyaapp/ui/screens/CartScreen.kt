package com.pedro.pideyaapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pedro.pideyaapp.R
import com.pedro.pideyaapp.ui.components.BrandTopBarTitle
import com.pedro.pideyaapp.ui.components.BottomLanguageFooter
import com.pedro.pideyaapp.ui.components.GlowPanel
import com.pedro.pideyaapp.ui.components.ProductPhoto
import com.pedro.pideyaapp.ui.components.ScreenBackdrop
import com.pedro.pideyaapp.ui.viewmodel.PideYaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: PideYaViewModel,
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onBack: () -> Unit,
    onConfirmOrder: () -> Unit
) {
    val cartState by viewModel.cartUiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var itemToRemove by remember { mutableStateOf<Int?>(null) }

    ScreenBackdrop {
        Scaffold(
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { BrandTopBarTitle() },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_action)
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
                    GlowPanel(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(R.string.cart_title),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = stringResource(R.string.cart_total, cartState.total),
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            Box(modifier = Modifier.size(104.dp)) {
                                ProductPhoto(
                                    productId = cartState.items.firstOrNull()?.productId ?: "menu_1",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }

                item {
                    Button(
                        onClick = { viewModel.confirmOrder { onConfirmOrder() } },
                        enabled = cartState.items.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.confirm_order_action))
                    }
                }

                if (cartState.items.isEmpty()) {
                    item {
                        GlowPanel(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = stringResource(R.string.empty_cart),
                                modifier = Modifier.padding(24.dp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    items(cartState.items) { item ->
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
                                ProductPhoto(
                                    productId = item.productId,
                                    modifier = Modifier.size(96.dp)
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = item.productName, style = MaterialTheme.typography.titleMedium)
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = stringResource(R.string.cart_item_quantity, item.quantity),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = stringResource(R.string.product_price, item.unitPrice * item.quantity),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    TextButton(onClick = { itemToRemove = item.id }) {
                                        Text(stringResource(R.string.remove_from_cart_action))
                                    }
                                }
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

    if (itemToRemove != null) {
        AlertDialog(
            onDismissRequest = { itemToRemove = null },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.removeCartItem(itemToRemove!!)
                        itemToRemove = null
                    }
                ) {
                    Text(stringResource(R.string.accept_action))
                }
            },
            dismissButton = {
                TextButton(onClick = { itemToRemove = null }) {
                    Text(stringResource(R.string.cancel_action))
                }
            },
            text = { Text(stringResource(R.string.remove_product_confirmation)) }
        )
    }
}
