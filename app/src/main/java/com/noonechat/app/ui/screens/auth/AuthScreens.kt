package com.noonechat.app.ui.screens.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noonechat.app.ui.components.NoOneButton
import com.noonechat.app.ui.components.NoOneButtonState
import com.noonechat.app.ui.components.NoOneScreen
import com.noonechat.app.ui.components.NoOneTextButton
import com.noonechat.app.ui.theme.CardBackground
import com.noonechat.app.ui.theme.Cyan
import com.noonechat.app.ui.theme.PrimaryText
import com.noonechat.app.ui.theme.SecondaryText

@Composable
fun WelcomeScreen(onContinue: () -> Unit) {
    NoOneScreen(title = "NO ONE CHAT", subtitle = "Private messaging. Electric clarity.") {
        Text(
            text = "Real-time chat, secure calls, and privacy controls — built for Android.",
            color = SecondaryText
        )
        Spacer(Modifier.height(28.dp))
        NoOneButton(text = "Get started", onClick = onContinue)
    }
}

@Composable
fun PhoneScreen(onContinue: (String) -> Unit, onBack: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    NoOneScreen(title = "Phone number", subtitle = "E.164 format", onBack = onBack) {
        DarkField(
            value = phone,
            onValueChange = { phone = it },
            label = "+1 555 000 0000"
        )
        Spacer(Modifier.height(20.dp))
        NoOneButton(
            text = "Send OTP",
            onClick = { onContinue(phone.trim()) },
            state = if (phone.length < 8) NoOneButtonState.Disabled else NoOneButtonState.Idle
        )
    }
}

@Composable
fun OtpScreen(onVerified: () -> Unit, onBack: () -> Unit) {
    var otp by remember { mutableStateOf("") }
    NoOneScreen(title = "Verification", subtitle = "Enter the code", onBack = onBack) {
        DarkField(value = otp, onValueChange = { otp = it.take(6) }, label = "6-digit OTP")
        Spacer(Modifier.height(20.dp))
        NoOneButton(
            text = "Verify",
            onClick = onVerified,
            state = if (otp.length < 6) NoOneButtonState.Disabled else NoOneButtonState.Idle
        )
        NoOneTextButton(text = "Resend code", onClick = { /* Phase 4 */ })
    }
}

@Composable
fun OnboardingSimpleScreen(
    title: String,
    subtitle: String,
    fieldLabel: String,
    buttonLabel: String,
    onContinue: (String) -> Unit,
    onBack: () -> Unit,
    requireValue: Boolean = true
) {
    var value by remember { mutableStateOf("") }
    NoOneScreen(title = title, subtitle = subtitle, onBack = onBack) {
        DarkField(value = value, onValueChange = { value = it }, label = fieldLabel)
        Spacer(Modifier.height(20.dp))
        NoOneButton(
            text = buttonLabel,
            onClick = { onContinue(value.trim()) },
            state = if (requireValue && value.isBlank()) {
                NoOneButtonState.Disabled
            } else {
                NoOneButtonState.Idle
            }
        )
    }
}

@Composable
fun PermissionPromptScreen(
    title: String,
    body: String,
    onAllow: () -> Unit,
    onSkip: () -> Unit,
    onBack: () -> Unit
) {
    NoOneScreen(title = title, onBack = onBack) {
        Text(text = body, color = SecondaryText)
        Spacer(Modifier.height(24.dp))
        NoOneButton(text = "Allow", onClick = onAllow)
        Spacer(Modifier.height(10.dp))
        NoOneButton(text = "Not now", onClick = onSkip, secondary = true)
    }
}

@Composable
private fun DarkField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = PrimaryText,
            unfocusedTextColor = PrimaryText,
            focusedBorderColor = Cyan,
            unfocusedBorderColor = CardBackground,
            focusedLabelColor = Cyan,
            unfocusedLabelColor = SecondaryText,
            cursorColor = Cyan,
            focusedContainerColor = CardBackground,
            unfocusedContainerColor = CardBackground
        )
    )
}
