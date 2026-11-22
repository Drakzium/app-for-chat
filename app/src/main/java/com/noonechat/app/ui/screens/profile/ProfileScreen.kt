package com.noonechat.app.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noonechat.app.ui.components.NoOneAvatar
import com.noonechat.app.ui.components.NoOneScreen
import com.noonechat.app.ui.theme.PrimaryText
import com.noonechat.app.ui.theme.SecondaryText

@Composable
fun ProfileScreen(
    onPrivacy: () -> Unit,
    onSecurity: () -> Unit,
    onNotifications: () -> Unit,
    onStorage: () -> Unit,
    onAccount: () -> Unit,
    onHelp: () -> Unit
) {
    NoOneScreen(title = "Profile", subtitle = "You") {
        NoOneAvatar(name = "You", size = 72.dp, online = true)
        Spacer(Modifier.height(12.dp))
        Text("Display name", color = PrimaryText)
        Text("@username · edit in Phase 5", color = SecondaryText)
        Spacer(Modifier.height(24.dp))
        SettingsRow("Privacy", onPrivacy)
        SettingsRow("Security", onSecurity)
        SettingsRow("Notifications", onNotifications)
        SettingsRow("Storage and data", onStorage)
        SettingsRow("Account management", onAccount)
        SettingsRow("Help and support", onHelp)
    }
}

@Composable
private fun SettingsRow(label: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp)
    ) {
        Text(text = label, color = PrimaryText)
    }
}
