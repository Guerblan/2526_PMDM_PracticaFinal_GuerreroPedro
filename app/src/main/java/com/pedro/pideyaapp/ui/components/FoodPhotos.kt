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
        "prod_1", "prod_2", "prod_3" -> R.drawable.menu_1_burger
        "prod_4", "prod_5", "prod_6" -> R.drawable.menu_2_fries
        "prod_7", "prod_8", "prod_9" -> R.drawable.menu_3_carbonara
        "prod_10", "prod_11", "prod_12" -> R.drawable.menu_1_burger
        "prod_13", "prod_14", "prod_15" -> R.drawable.menu_4_margherita
        "prod_16", "prod_17", "prod_18" -> R.drawable.menu_8_smoothie
        "prod_19", "prod_20", "prod_21" -> R.drawable.menu_2_fries
        "prod_22", "prod_23", "prod_24" -> R.drawable.menu_8_smoothie
        "prod_25", "prod_26", "prod_27" -> R.drawable.menu_8_smoothie
        else -> R.drawable.menu_1_burger
    }
}

@DrawableRes
fun imageForRestaurant(restaurantId: String): Int {
    return when (restaurantId) {
        "est_1", "est_8" -> R.drawable.menu_8_smoothie
        "est_2", "est_7" -> R.drawable.menu_2_fries
        "est_3", "est_9" -> R.drawable.menu_3_carbonara
        "est_4" -> R.drawable.menu_1_burger
        "est_5" -> R.drawable.menu_4_margherita
        "est_6" -> R.drawable.menu_8_smoothie
        else -> R.drawable.menu_1_burger
    }
}
