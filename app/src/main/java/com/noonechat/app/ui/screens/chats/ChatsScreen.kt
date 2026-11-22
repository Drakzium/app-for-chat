package com.noonechat.app.ui.screens.chats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noonechat.app.ui.components.EmptyState
import com.noonechat.app.ui.components.NoOneAvatar
import com.noonechat.app.ui.components.NoOneScreen
import com.noonechat.app.ui.theme.Cyan
import com.noonechat.app.ui.theme.PrimaryText
import com.noonechat.app.ui.theme.SecondaryText

@Composable
fun ChatsScreen(
    onOpenChat: (String) -> Unit,
    onNewChat: () -> Unit,
    onArchived: () -> Unit
) {
    NoOneScreen(
        title = "Chats",
        subtitle = "NO ONE CHAT",
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
        actions = {
            IconButton(onClick = onArchived) {
                Icon(Icons.Filled.Archive, contentDescription = "Archived", tint = PrimaryText)
            }
            IconButton(onClick = onNewChat) {
                Icon(Icons.Filled.AddComment, contentDescription = "New chat", tint = Cyan)
            }
        }
    ) {
        // Placeholder list until messaging phase; demonstrates layout + actions.
        val demo = listOf(
            Triple("demo-1", "Alex Rivera", "Tap to open conversation shell"),
            Triple("demo-2", "Jordan Lee", "Encryption and realtime land next")
        )
        if (demo.isEmpty()) {
            EmptyState(
                title = "No conversations yet",
                subtitle = "Start a chat from the + button."
            )
        } else {
            LazyColumn {
                items(demo.size) { index ->
                    val item = demo[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOpenChat(item.first) }
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        NoOneAvatar(name = item.second, online = index == 0)
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item.second, color = PrimaryText)
                            Spacer(Modifier.height(2.dp))
                            Text(text = item.third, color = SecondaryText)
                        }
                    }
                }
            }
        }
    }
}
