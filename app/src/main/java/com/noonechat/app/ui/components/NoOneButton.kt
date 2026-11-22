package com.noonechat.app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.noonechat.app.ui.theme.BrandGradient
import com.noonechat.app.ui.theme.CardBackground
import com.noonechat.app.ui.theme.Error
import com.noonechat.app.ui.theme.PrimaryText
import com.noonechat.app.ui.theme.Radii
import com.noonechat.app.ui.theme.SecondaryText

enum class NoOneButtonState {
    Idle, Loading, Disabled, Error
}

@Composable
fun NoOneButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: NoOneButtonState = NoOneButtonState.Idle,
    fillMaxWidth: Boolean = true,
    secondary: Boolean = false,
    errorLabel: String? = null
) {
    val haptics = LocalHapticFeedback.current
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(if (pressed) 0.97f else 1f, label = "btnScale")
    val enabled = state == NoOneButtonState.Idle || state == NoOneButtonState.Error
    val label = if (state == NoOneButtonState.Error && !errorLabel.isNullOrBlank()) {
        errorLabel
    } else {
        text
    }

    val background: Brush = when {
        state == NoOneButtonState.Error -> Brush.linearGradient(listOf(Error, Error))
        secondary -> Brush.linearGradient(listOf(CardBackground, CardBackground))
        !enabled -> Brush.linearGradient(listOf(CardBackground, CardBackground))
        else -> BrandGradient
    }

    Box(
        modifier = modifier
            .then(if (fillMaxWidth) Modifier.fillMaxWidth() else Modifier)
            .scale(scale)
            .height(52.dp)
            .clip(Radii.button)
            .background(background)
            .clickable(
                enabled = enabled,
                role = Role.Button,
                interactionSource = interaction,
                indication = null
            ) {
                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            NoOneButtonState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    color = PrimaryText,
                    strokeWidth = 2.dp
                )
            }
            else -> {
                Text(
                    text = label,
                    color = if (enabled) PrimaryText else SecondaryText,
                    modifier = Modifier.padding(PaddingValues(horizontal = 16.dp))
                )
            }
        }
    }
}

@Composable
fun NoOneTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = PrimaryText
) {
    Row(
        modifier = modifier
            .clip(Radii.button)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, color = if (enabled) color else SecondaryText)
    }
}
