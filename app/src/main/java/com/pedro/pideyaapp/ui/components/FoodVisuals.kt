package com.pedro.pideyaapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun ScreenBackdrop(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val lineStrong = MaterialTheme.colorScheme.outline.copy(alpha = 0.08f)
    val lineSoft = MaterialTheme.colorScheme.outline.copy(alpha = 0.06f)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surface
                    )
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawLine(
                color = lineStrong,
                start = Offset(size.width * 0.08f, 0f),
                end = Offset(size.width * 0.55f, size.height),
                strokeWidth = 3f
            )
            drawLine(
                color = lineSoft,
                start = Offset(size.width * 0.62f, 0f),
                end = Offset(size.width * 0.92f, size.height),
                strokeWidth = 2f
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.18f),
                            Color.Transparent
                        ),
                        center = Offset(120f, 80f),
                        radius = 520f
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f),
                            Color.Transparent
                        ),
                        center = Offset(900f, 260f),
                        radius = 560f
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.10f),
                            Color.Transparent
                        ),
                        center = Offset(420f, 1200f),
                        radius = 700f
                    )
                )
        )
        content()
    }
}

@Composable
fun FoodArtwork(
    key: String,
    modifier: Modifier = Modifier
) {
    val palette = artworkPalette(key)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        palette.accent.copy(alpha = 0.35f),
                        palette.background
                    )
                )
            )
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            val plateRadius = size.minDimension * 0.33f
            val center = Offset(size.width * 0.5f, size.height * 0.48f)

            drawCircle(
                color = palette.leaf.copy(alpha = 0.22f),
                radius = size.minDimension * 0.42f,
                center = Offset(size.width * 0.25f, size.height * 0.18f)
            )
            drawCircle(
                color = palette.accent.copy(alpha = 0.18f),
                radius = size.minDimension * 0.32f,
                center = Offset(size.width * 0.82f, size.height * 0.22f)
            )

            repeat(4) { index ->
                val x = (0.12f + index * 0.2f) * size.width
                val y = if (index % 2 == 0) size.height * 0.82f else size.height * 0.14f
                drawOval(
                    color = palette.leaf.copy(alpha = 0.9f),
                    topLeft = Offset(x, y),
                    size = Size(size.width * 0.09f, size.height * 0.16f)
                )
            }

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF2B3246), Color(0xFF131826)),
                    center = center,
                    radius = plateRadius
                ),
                radius = plateRadius,
                center = center
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.08f),
                radius = plateRadius,
                center = center,
                style = Stroke(width = 5f)
            )

            drawArc(
                color = Color.White.copy(alpha = 0.16f),
                startAngle = 210f,
                sweepAngle = 95f,
                useCenter = false,
                topLeft = Offset(center.x - plateRadius * 0.8f, center.y - plateRadius * 0.86f),
                size = Size(plateRadius * 1.55f, plateRadius * 1.3f),
                style = Stroke(width = 6f)
            )

            repeat(5) { index ->
                val pieceOffset = Offset(
                    center.x - plateRadius * 0.45f + index * plateRadius * 0.22f,
                    center.y - plateRadius * 0.12f + (index % 2) * 10f
                )
                rotate(
                    degrees = -25f + index * 4f,
                    pivot = Offset(
                        pieceOffset.x + plateRadius * 0.16f,
                        pieceOffset.y + plateRadius * 0.08f
                    )
                ) {
                    drawRoundRect(
                        brush = Brush.linearGradient(
                            colors = listOf(palette.foodStart, palette.foodEnd)
                        ),
                        topLeft = pieceOffset,
                        size = Size(plateRadius * 0.32f, plateRadius * 0.15f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(18f, 18f)
                    )
                }
            }

            repeat(3) { index ->
                val leafPath = Path().apply {
                    moveTo(center.x - 12f + index * 22f, center.y + 18f + index * 6f)
                    quadraticTo(
                        center.x + 10f + index * 24f,
                        center.y - 6f + index * 8f,
                        center.x + 20f + index * 18f,
                        center.y + 30f + index * 10f
                    )
                    quadraticTo(
                        center.x + 2f + index * 8f,
                        center.y + 18f + index * 12f,
                        center.x - 12f + index * 22f,
                        center.y + 18f + index * 6f
                    )
                }
                drawPath(leafPath, palette.leaf)
            }

            drawCircle(
                color = Color(0xFFFFF2C4),
                radius = plateRadius * 0.13f,
                center = Offset(center.x + plateRadius * 0.28f, center.y - plateRadius * 0.2f)
            )
            drawCircle(
                color = Color(0x80FFF9E3),
                radius = plateRadius * 0.085f,
                center = Offset(center.x + plateRadius * 0.38f, center.y - plateRadius * 0.26f)
            )
        }
    }
}

private data class ArtworkPalette(
    val background: Color,
    val accent: Color,
    val leaf: Color,
    val foodStart: Color,
    val foodEnd: Color
)

private fun artworkPalette(key: String): ArtworkPalette {
    return when (abs(key.hashCode()) % 4) {
        0 -> ArtworkPalette(
            background = Color(0xFF1B1F2D),
            accent = Color(0xFFFF6B5E),
            leaf = Color(0xFF73D56B),
            foodStart = Color(0xFFFFA06E),
            foodEnd = Color(0xFFD94A2D)
        )
        1 -> ArtworkPalette(
            background = Color(0xFF18202A),
            accent = Color(0xFFF36A5A),
            leaf = Color(0xFF87C95B),
            foodStart = Color(0xFFF5B15A),
            foodEnd = Color(0xFFE4572E)
        )
        2 -> ArtworkPalette(
            background = Color(0xFF1E1D29),
            accent = Color(0xFFFF7A66),
            leaf = Color(0xFF69C17D),
            foodStart = Color(0xFFFF8F70),
            foodEnd = Color(0xFFC43B33)
        )
        else -> ArtworkPalette(
            background = Color(0xFF171C27),
            accent = Color(0xFFFF7562),
            leaf = Color(0xFF7BCB6F),
            foodStart = Color(0xFFF0A24D),
            foodEnd = Color(0xFFE16035)
        )
    }
}

@Composable
fun GlowPanel(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.58f))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.22f),
                shape = RoundedCornerShape(28.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun CircularBadge(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color.copy(alpha = 0.18f))
            .border(1.dp, color.copy(alpha = 0.34f), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
