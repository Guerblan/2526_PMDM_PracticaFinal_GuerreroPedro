package com.pedro.pideyaapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pedro.pideyaapp.R
import com.pedro.pideyaapp.domain.model.Establishment
import com.pedro.pideyaapp.ui.components.BottomLanguageFooter
import com.pedro.pideyaapp.ui.components.BrandTopBarTitle
import com.pedro.pideyaapp.ui.components.GlowPanel
import com.pedro.pideyaapp.ui.components.ScreenBackdrop
import com.pedro.pideyaapp.ui.viewmodel.PideYaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstablishmentsScreen(
    viewModel: PideYaViewModel,
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    eventId: String,
    eventName: String,
    onBack: () -> Unit,
    onOpenEstablishment: (String, String) -> Unit,
    onOpenCart: () -> Unit
) {
    val catalogState by viewModel.catalogUiState.collectAsState()

    LaunchedEffect(eventId) {
        viewModel.loadEstablishments(eventId, eventName)
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
            }
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
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                text = eventName,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(R.string.establishments_subtitle),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                items(catalogState.establishments) { establishment ->
                    EstablishmentCard(
                        establishment = establishment,
                        onOpenEstablishment = onOpenEstablishment
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
private fun EstablishmentCard(
    establishment: Establishment,
    onOpenEstablishment: (String, String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.78f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = establishment.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = establishment.typeLabel,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = establishment.description,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(14.dp))
            Button(onClick = { onOpenEstablishment(establishment.id, establishment.name) }) {
                Text(stringResource(R.string.view_products_action))
            }
        }
    }
}
