package com.noonechat.app.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.noonechat.app.ui.theme.Background
import com.noonechat.app.ui.theme.CardBackground
import com.noonechat.app.ui.theme.Cyan
import com.noonechat.app.ui.theme.PrimaryBlue
import com.noonechat.app.ui.theme.PrimaryText
import com.noonechat.app.ui.theme.Radii
import com.noonechat.app.ui.theme.SecondaryText

@Composable
fun FullScreenLoader(message: String = "Loading…") {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Cyan, strokeWidth = 3.dp, modifier = Modifier.size(40.dp))
            Spacer(Modifier.height(16.dp))
            Text(text = message, color = SecondaryText)
        }
    }
}

@Composable
fun SkeletonLine(modifier: Modifier = Modifier, height: Int = 14) {
    val transition = rememberInfiniteTransition(label = "skeleton")
    val alpha by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.75f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "skeletonAlpha"
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
            .clip(Radii.button)
            .background(
                Brush.horizontalGradient(
                    listOf(
                        CardBackground.copy(alpha = alpha),
                        PrimaryBlue.copy(alpha = alpha * 0.25f),
                        CardBackground.copy(alpha = alpha)
                    )
                )
            )
    )
}

@Composable
fun ChatRowSkeleton() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        SkeletonLine(Modifier.fillMaxWidth(0.45f), height = 16)
        Spacer(Modifier.height(8.dp))
        SkeletonLine(Modifier.fillMaxWidth(0.75f), height = 12)
    }
}

@Composable
fun EmptyState(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, color = PrimaryText)
        Spacer(Modifier.height(8.dp))
        Text(text = subtitle, color = SecondaryText)
    }
}
