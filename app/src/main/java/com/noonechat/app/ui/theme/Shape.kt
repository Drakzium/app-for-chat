package com.noonechat.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val NoOneShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

object Radii {
    val bubble = RoundedCornerShape(18.dp)
    val card = RoundedCornerShape(16.dp)
    val button = RoundedCornerShape(14.dp)
    val sheet = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    val avatar = RoundedCornerShape(50)
}
