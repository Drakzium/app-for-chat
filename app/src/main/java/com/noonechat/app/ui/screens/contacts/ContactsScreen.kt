package com.noonechat.app.ui.screens.contacts

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
fun ContactsScreen(
    onQr: () -> Unit,
    onRequests: () -> Unit
) {
    NoOneScreen(title = "Contacts", subtitle = "Sync · QR · requests") {
        Text(
            text = "Phone sync uses privacy-preserving HMAC matching. QR and requests are wired in navigation.",
            color = SecondaryText
        )
        Spacer(Modifier.height(16.dp))
        NoOneButton(text = "Scan QR", onClick = onQr)
        Spacer(Modifier.height(12.dp))
        NoOneButton(text = "Contact requests", onClick = onRequests, secondary = true)
    }
}
