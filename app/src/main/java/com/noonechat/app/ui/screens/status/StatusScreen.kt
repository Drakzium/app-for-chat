package com.noonechat.app.ui.screens.status

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noonechat.app.ui.components.NoOneButton
import com.noonechat.app.ui.components.NoOneScreen
import com.noonechat.app.ui.theme.SecondaryText

@Composable
fun StatusScreen() {
    NoOneScreen(title = "Status", subtitle = "24-hour updates") {
        Text(
            text = "Text, image, and video status with privacy controls will connect here.",
            color = SecondaryText
        )
        Spacer(Modifier.height(20.dp))
        NoOneButton(text = "Add status", onClick = { /* Phase 9 */ })
    }
}
