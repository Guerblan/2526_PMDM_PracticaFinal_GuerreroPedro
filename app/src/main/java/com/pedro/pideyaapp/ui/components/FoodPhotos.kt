package com.pedro.pideyaapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pedro.pideyaapp.R

@Composable
fun ProductPhoto(
    productId: String,
    modifier: Modifier = Modifier
) {
    ImageCard(
        imageRes = imageForProduct(productId),
        modifier = modifier
    )
}

@Composable
fun RestaurantPhoto(
    restaurantId: String,
    modifier: Modifier = Modifier
) {
    ImageCard(
        imageRes = imageForRestaurant(restaurantId),
        modifier = modifier
    )
}

@Composable
private fun ImageCard(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        )
    }
}

@DrawableRes
fun imageForProduct(productId: String): Int {
    return when (productId) {
        "menu_1" -> R.drawable.menu_1_burger
        "menu_2" -> R.drawable.menu_2_fries
        "menu_3" -> R.drawable.menu_3_carbonara
        "menu_4" -> R.drawable.menu_4_margherita
        "menu_5" -> R.drawable.menu_5_sushi
        "menu_6" -> R.drawable.menu_6_gyoza
        "menu_7" -> R.drawable.menu_7_bowl
        "menu_8" -> R.drawable.menu_8_smoothie
        else -> R.drawable.menu_1_burger
    }
}

@DrawableRes
fun imageForRestaurant(restaurantId: String): Int {
    return when (restaurantId) {
        "rest_1" -> R.drawable.menu_1_burger
        "rest_2" -> R.drawable.menu_4_margherita
        "rest_3" -> R.drawable.menu_5_sushi
        "rest_4" -> R.drawable.menu_7_bowl
        else -> R.drawable.menu_1_burger
    }
}
