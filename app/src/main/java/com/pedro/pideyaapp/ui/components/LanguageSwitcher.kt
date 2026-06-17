package com.pedro.pideyaapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.pedro.pideyaapp.R

@Composable
fun BottomLanguageFooter(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectorDescription = stringResource(R.string.language_selector)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Box(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .weight(1f)
                .semantics { contentDescription = selectorDescription }
                .clip(RoundedCornerShape(26.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.28f))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.16f),
                    shape = RoundedCornerShape(26.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                LanguageToggle(
                    selectedLanguage = selectedLanguage,
                    onLanguageSelected = onLanguageSelected
                )
            }
        }
    }
}

@Composable
private fun LanguageToggle(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.94f),
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f)
                    )
                )
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.18f),
                shape = RoundedCornerShape(999.dp)
            )
            .padding(horizontal = 6.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LanguageFlagButton(
            languageTag = "es",
            label = stringResource(R.string.language_spanish),
            selected = selectedLanguage == "es",
            onClick = onLanguageSelected
        ) {
            SpainFlag()
        }
        LanguageFlagButton(
            languageTag = "en",
            label = stringResource(R.string.language_english),
            selected = selectedLanguage == "en",
            onClick = onLanguageSelected
        ) {
            UkFlag()
        }
    }
}

@Composable
private fun LanguageFlagButton(
    languageTag: String,
    label: String,
    selected: Boolean,
    onClick: (String) -> Unit,
    flag: @Composable () -> Unit
) {
    val alpha = if (selected) 1f else 0.34f
    val borderColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.55f) else Color.Transparent
    val fillColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.14f) else Color.Transparent

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(fillColor)
            .border(1.dp, borderColor, RoundedCornerShape(999.dp))
            .clickable { onClick(languageTag) }
            .padding(horizontal = 6.dp, vertical = 3.dp)
            .alpha(alpha),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .semantics { contentDescription = label },
            contentAlignment = Alignment.Center
        ) {
            flag()
        }
    }
}

@Composable
private fun SpainFlag() {
    Box(
        modifier = Modifier
            .size(26.dp)
            .clip(CircleShape)
            .background(Color(0xFFC60B1E))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(11.dp)
                .align(Alignment.Center)
                .background(Color(0xFFFFC400))
        )
    }
}

@Composable
private fun UkFlag() {
    Canvas(
        modifier = Modifier
            .size(26.dp)
            .clip(CircleShape)
    ) {
        drawCircle(Color(0xFF012169))

        val whiteStroke = size.minDimension * 0.18f
        val redStroke = size.minDimension * 0.08f

        drawLine(
            color = Color.White,
            start = androidx.compose.ui.geometry.Offset(0f, 0f),
            end = androidx.compose.ui.geometry.Offset(size.width, size.height),
            strokeWidth = whiteStroke,
            cap = StrokeCap.Butt
        )
        drawLine(
            color = Color.White,
            start = androidx.compose.ui.geometry.Offset(size.width, 0f),
            end = androidx.compose.ui.geometry.Offset(0f, size.height),
            strokeWidth = whiteStroke,
            cap = StrokeCap.Butt
        )
        drawLine(
            color = Color(0xFFC8102E),
            start = androidx.compose.ui.geometry.Offset(0f, 0f),
            end = androidx.compose.ui.geometry.Offset(size.width, size.height),
            strokeWidth = redStroke,
            cap = StrokeCap.Butt
        )
        drawLine(
            color = Color(0xFFC8102E),
            start = androidx.compose.ui.geometry.Offset(size.width, 0f),
            end = androidx.compose.ui.geometry.Offset(0f, size.height),
            strokeWidth = redStroke,
            cap = StrokeCap.Butt
        )
        drawLine(
            color = Color.White,
            start = androidx.compose.ui.geometry.Offset(size.width / 2f, 0f),
            end = androidx.compose.ui.geometry.Offset(size.width / 2f, size.height),
            strokeWidth = size.minDimension * 0.26f,
            cap = StrokeCap.Butt
        )
        drawLine(
            color = Color.White,
            start = androidx.compose.ui.geometry.Offset(0f, size.height / 2f),
            end = androidx.compose.ui.geometry.Offset(size.width, size.height / 2f),
            strokeWidth = size.minDimension * 0.26f,
            cap = StrokeCap.Butt
        )
        drawLine(
            color = Color(0xFFC8102E),
            start = androidx.compose.ui.geometry.Offset(size.width / 2f, 0f),
            end = androidx.compose.ui.geometry.Offset(size.width / 2f, size.height),
            strokeWidth = size.minDimension * 0.12f,
            cap = StrokeCap.Butt
        )
        drawLine(
            color = Color(0xFFC8102E),
            start = androidx.compose.ui.geometry.Offset(0f, size.height / 2f),
            end = androidx.compose.ui.geometry.Offset(size.width, size.height / 2f),
            strokeWidth = size.minDimension * 0.12f,
            cap = StrokeCap.Butt
        )
    }
}
