package com.noonechat.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noonechat.app.ui.theme.Background
import com.noonechat.app.ui.theme.PrimaryText
import com.noonechat.app.ui.theme.SecondaryText
import com.noonechat.app.ui.theme.SurfaceGlow

@Composable
fun NoOneScreen(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onBack: (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .background(SurfaceGlow)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (onBack != null) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryText
                        )
                    }
                }
                Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp)) {
                    Text(text = title, color = PrimaryText)
                    if (!subtitle.isNullOrBlank()) {
                        Text(text = subtitle, color = SecondaryText)
                    }
                }
                actions?.invoke()
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                content = content
            )
        }
    }
}

@Composable
fun FeaturePlaceholder(
    title: String,
    body: String,
    onBack: (() -> Unit)? = null,
    primaryActionLabel: String? = null,
    onPrimaryAction: (() -> Unit)? = null
) {
    NoOneScreen(title = title, onBack = onBack, subtitle = "Coming in a later phase") {
        Text(text = body, color = SecondaryText)
        Spacer(Modifier.height(24.dp))
        if (primaryActionLabel != null && onPrimaryAction != null) {
            NoOneButton(text = primaryActionLabel, onClick = onPrimaryAction)
        }
    }
}
