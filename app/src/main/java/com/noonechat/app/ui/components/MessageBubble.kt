package com.noonechat.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.noonechat.app.ui.theme.BubbleIncoming
import com.noonechat.app.ui.theme.BubbleOutgoing
import com.noonechat.app.ui.theme.PrimaryText
import com.noonechat.app.ui.theme.Radii
import com.noonechat.app.ui.theme.SecondaryText

enum class MessageDeliveryState {
    Pending, Sent, Delivered, Read, Failed
}

@Composable
fun MessageBubble(
    text: String,
    outgoing: Boolean,
    timeLabel: String,
    delivery: MessageDeliveryState = MessageDeliveryState.Sent,
    modifier: Modifier = Modifier
) {
    val bg = if (outgoing) BubbleOutgoing else BubbleIncoming
    Column(
        modifier = modifier
            .widthIn(max = 300.dp)
            .clip(Radii.bubble)
            .background(bg)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalAlignment = if (outgoing) Alignment.End else Alignment.Start
    ) {
        Text(text = text, color = PrimaryText)
        Text(
            text = buildString {
                append(timeLabel)
                if (outgoing) {
                    append(" · ")
                    append(
                        when (delivery) {
                            MessageDeliveryState.Pending -> "Pending"
                            MessageDeliveryState.Sent -> "Sent"
                            MessageDeliveryState.Delivered -> "Delivered"
                            MessageDeliveryState.Read -> "Read"
                            MessageDeliveryState.Failed -> "Failed"
                        }
                    )
                }
            },
            color = SecondaryText,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
