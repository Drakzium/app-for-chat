package com.noonechat.app.ui.screens.calls

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.noonechat.app.ui.components.EmptyState
import com.noonechat.app.ui.components.NoOneScreen
import com.noonechat.app.ui.theme.SecondaryText

@Composable
fun CallsScreen() {
    NoOneScreen(title = "Calls", subtitle = "Voice · video · history") {
        Text(
            text = "WebRTC calling and call history land in Phase 11–14.",
            color = SecondaryText
        )
        EmptyState(
            title = "No recent calls",
            subtitle = "Incoming, outgoing, and missed calls will appear here."
        )
    }
}
