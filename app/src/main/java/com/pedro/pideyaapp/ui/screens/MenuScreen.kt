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
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pedro.pideyaapp.R
import com.pedro.pideyaapp.domain.model.MenuProduct
import com.pedro.pideyaapp.ui.components.BrandTopBarTitle
import com.pedro.pideyaapp.ui.components.BottomLanguageFooter
import com.pedro.pideyaapp.ui.components.GlowPanel
import com.pedro.pideyaapp.ui.components.ProductPhoto
import com.pedro.pideyaapp.ui.components.RestaurantPhoto
import com.pedro.pideyaapp.ui.components.ScreenBackdrop
import com.pedro.pideyaapp.ui.viewmodel.PideYaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    viewModel: PideYaViewModel,
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    restaurantId: String,
    restaurantName: String,
    onBack: () -> Unit,
    onOpenCart: () -> Unit
) {
    val catalogState by viewModel.catalogUiState.collectAsState()
    val cartState by viewModel.cartUiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(restaurantId) {
        viewModel.loadMenu(restaurantId, restaurantName)
    }

    LaunchedEffect(cartState.message) {
        cartState.message?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearCartMessage()
        }
    }

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
                    },
                    actions = {
                        TextButton(onClick = onOpenCart) {
                            Text(stringResource(R.string.open_cart))
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = onOpenCart) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = stringResource(R.string.open_cart)
                    )
                }
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
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.size(124.dp)) {
                                RestaurantPhoto(restaurantId = restaurantId, modifier = Modifier.fillMaxSize())
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = catalogState.selectedRestaurantName.ifBlank { restaurantName },
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(R.string.menu_screen_subtitle),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                items(catalogState.menu) { product ->
                    MenuItemCard(
                        product = product,
                        onAdd = { viewModel.addProductToCart(product) }
                    )
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

@Composable
private fun MenuItemCard(
    product: MenuProduct,
    onAdd: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.78f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ProductPhoto(
                productId = product.id,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(176.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(text = product.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.product_price, product.price),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Button(onClick = onAdd) {
                    Text(stringResource(R.string.add_to_cart_action))
                }
            }
        }
    }
}
