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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pedro.pideyaapp.R
import com.pedro.pideyaapp.ui.components.BrandTopBarTitle
import com.pedro.pideyaapp.ui.components.BottomLanguageFooter
import com.pedro.pideyaapp.domain.model.Restaurant
import com.pedro.pideyaapp.ui.components.CircularBadge
import com.pedro.pideyaapp.ui.components.GlowPanel
import com.pedro.pideyaapp.ui.components.RestaurantPhoto
import com.pedro.pideyaapp.ui.components.ScreenBackdrop
import com.pedro.pideyaapp.ui.viewmodel.PideYaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantsScreen(
    viewModel: PideYaViewModel,
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onOpenMenu: (String, String) -> Unit,
    onOpenCart: () -> Unit,
    onOpenOrders: () -> Unit,
    onLogout: () -> Unit
) {
    val catalogState by viewModel.catalogUiState.collectAsState()
    val authState by viewModel.authUiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showLogoutDialog by remember { mutableStateOf(false) }

    ScreenBackdrop {
        Scaffold(
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { BrandTopBarTitle() }
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
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(
                                        R.string.restaurants_title,
                                        authState.email.ifBlank { stringResource(R.string.user_fallback) }
                                    ),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(R.string.splash_subtitle),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(14.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                    Button(onClick = onOpenOrders) {
                                        Text(stringResource(R.string.open_orders))
                                    }
                                    TextButton(onClick = { showLogoutDialog = true }) {
                                        Text(stringResource(R.string.logout_action))
                                    }
                                }
                            }
                            Box(modifier = Modifier.size(132.dp)) {
                                RestaurantPhoto(
                                    restaurantId = catalogState.restaurants.firstOrNull()?.id ?: "rest_1",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }

                item {
                    Column {
                        Text(
                            text = stringResource(R.string.category_all),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CategoryFilters(
                            selectedCategory = catalogState.selectedCategory,
                            onSelectCategory = viewModel::selectCategory
                        )
                    }
                }

                items(catalogState.restaurants) { restaurant ->
                    RestaurantCard(
                        restaurant = restaurant,
                        onViewMenu = { onOpenMenu(restaurant.id, restaurant.name) },
                        onRate = { stars -> viewModel.rateRestaurant(restaurant.id, stars) }
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

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    }
                ) {
                    Text(stringResource(R.string.accept_action))
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text(stringResource(R.string.cancel_action))
                }
            },
            text = { Text(stringResource(R.string.logout_confirmation)) }
        )
    }
}

@Composable
private fun CategoryFilters(
    selectedCategory: String,
    onSelectCategory: (String) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(listOf("all", "burger", "pizza", "sushi", "healthy")) { category ->
            AssistChip(
                onClick = { onSelectCategory(category) },
                modifier = Modifier.alpha(if (selectedCategory == category) 1f else 0.76f),
                label = { Text(categoryLabel(category)) }
            )
        }
    }
}

@Composable
private fun categoryLabel(categoryId: String): String {
    return when (categoryId) {
        "burger" -> stringResource(R.string.category_burger)
        "pizza" -> stringResource(R.string.category_pizza)
        "sushi" -> stringResource(R.string.category_sushi)
        "healthy" -> stringResource(R.string.category_healthy)
        else -> stringResource(R.string.category_all)
    }
}

@Composable
private fun RestaurantCard(
    restaurant: Restaurant,
    onViewMenu: () -> Unit,
    onRate: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.76f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                RestaurantPhoto(
                    restaurantId = restaurant.id,
                    modifier = Modifier
                        .size(122.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = restaurant.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = restaurant.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CircularBadge(modifier = Modifier.padding(end = 2.dp)) {
                            Text(
                                text = restaurant.deliveryTimeMinutes.toString(),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                                color = MaterialTheme.colorScheme.tertiary,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                        CircularBadge(color = MaterialTheme.colorScheme.primary) {
                            Text(
                                text = "${restaurant.rating}/5",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(
                    R.string.restaurant_meta,
                    restaurant.categoryLabel,
                    restaurant.deliveryTimeMinutes,
                    restaurant.deliveryFee
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items((1..5).toList()) { stars ->
                    AssistChip(
                        onClick = { onRate(stars) },
                        label = { Text(stars.toString()) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            Button(onClick = onViewMenu) {
                Text(stringResource(R.string.view_menu_action))
            }
        }
    }
}
