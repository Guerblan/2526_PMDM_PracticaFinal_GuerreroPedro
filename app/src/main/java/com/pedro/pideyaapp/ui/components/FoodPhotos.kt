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
fun EstablishmentPhoto(
    establishmentId: String,
    modifier: Modifier = Modifier
) {
    ImageCard(
        imageRes = imageForEstablishment(establishmentId),
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
        "prod_1", "prod_2", "prod_3" -> R.drawable.pideya_food_classics
        "prod_4", "prod_5", "prod_6" -> R.drawable.pideya_food_bites
        "prod_7", "prod_8", "prod_9" -> R.drawable.pideya_food_tasting
        "prod_10", "prod_11", "prod_12" -> R.drawable.pideya_food_classics
        "prod_13", "prod_14", "prod_15" -> R.drawable.pideya_food_pizza
        "prod_16", "prod_17", "prod_18" -> R.drawable.pideya_food_drinks
        "prod_19", "prod_20", "prod_21" -> R.drawable.pideya_food_bites
        "prod_22", "prod_23", "prod_24" -> R.drawable.pideya_food_drinks
        "prod_25", "prod_26", "prod_27" -> R.drawable.pideya_food_drinks
        else -> R.drawable.pideya_food_classics
    }
}

@DrawableRes
fun imageForEstablishment(establishmentId: String): Int {
    return when (establishmentId) {
        "est_1", "est_8" -> R.drawable.pideya_food_drinks
        "est_2", "est_7" -> R.drawable.pideya_food_bites
        "est_3", "est_9" -> R.drawable.pideya_food_tasting
        "est_4" -> R.drawable.pideya_food_classics
        "est_5" -> R.drawable.pideya_food_pizza
        "est_6" -> R.drawable.pideya_food_drinks
        else -> R.drawable.pideya_food_classics
    }
}
