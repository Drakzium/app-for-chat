package com.noonechat.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noonechat.app.ui.theme.CardBackground
import com.noonechat.app.ui.theme.Cyan
import com.noonechat.app.ui.theme.ElectricBlue
import com.noonechat.app.ui.theme.PrimaryText
import com.noonechat.app.ui.theme.Radii

@Composable
fun NoOneAvatar(
    name: String,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    online: Boolean = false
) {
    val initials = name.trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .joinToString("") { it.first().uppercase() }
        .ifBlank { "?" }

    Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(Radii.avatar)
                .background(
                    Brush.linearGradient(listOf(ElectricBlue.copy(alpha = 0.35f), CardBackground))
                )
                .then(
                    if (online) {
                        Modifier.border(1.5.dp, Cyan, Radii.avatar)
                    } else {
                        Modifier
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                color = PrimaryText,
                fontSize = (size.value * 0.32f).sp
            )
        }
    }
}
