package com.pedro.pideyaapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pedro.pideyaapp.R
import com.pedro.pideyaapp.ui.components.BrandPill
import com.pedro.pideyaapp.ui.components.BrandHero
import com.pedro.pideyaapp.ui.components.GlowPanel
import com.pedro.pideyaapp.ui.components.ScreenBackdrop
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    isLogged: Boolean,
    onNavigateToLogin: () -> Unit,
    onNavigateToEvents: () -> Unit
) {
    LaunchedEffect(isLogged) {
        delay(900)
        if (isLogged) onNavigateToEvents() else onNavigateToLogin()
    }

    ScreenBackdrop {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                BrandPill(text = stringResource(R.string.brand_tag_live))
                BrandPill(text = stringResource(R.string.brand_tag_fast))
            }
            Spacer(modifier = Modifier.height(18.dp))
            BrandHero()
            Spacer(modifier = Modifier.height(24.dp))
            GlowPanel(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.splash_subtitle),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}
