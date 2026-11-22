package com.noonechat.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.noonechat.app.ui.components.FeaturePlaceholder
import com.noonechat.app.ui.components.FullScreenLoader
import com.noonechat.app.ui.components.MessageBubble
import com.noonechat.app.ui.components.MessageDeliveryState
import com.noonechat.app.ui.components.NoOneScreen
import com.noonechat.app.ui.screens.auth.OnboardingSimpleScreen
import com.noonechat.app.ui.screens.auth.OtpScreen
import com.noonechat.app.ui.screens.auth.PermissionPromptScreen
import com.noonechat.app.ui.screens.auth.PhoneScreen
import com.noonechat.app.ui.screens.auth.WelcomeScreen
import com.noonechat.app.ui.screens.home.HomeShell
import kotlinx.coroutines.delay

@Composable
fun AppNavHost(
    startAuthenticated: Boolean = false,
    modifier: Modifier = Modifier
) {
    val nav = rememberNavController()
    var splashDone by remember { mutableStateOf(false) }

    if (!splashDone) {
        FullScreenLoader(message = "NO ONE CHAT")
        LaunchedEffect(Unit) {
            delay(700)
            splashDone = true
        }
        return
    }

    val start = if (startAuthenticated) Routes.Home else Routes.Welcome

    NavHost(
        navController = nav,
        startDestination = start,
        modifier = modifier
    ) {
        composable(Routes.Welcome) {
            WelcomeScreen(onContinue = { nav.navigate(Routes.Phone) })
        }
        composable(Routes.Phone) {
            PhoneScreen(
                onContinue = { nav.navigate(Routes.Otp) },
                onBack = { nav.popBackStack() }
            )
        }
        composable(Routes.Otp) {
            OtpScreen(
                onVerified = { nav.navigate(Routes.ProfilePhoto) },
                onBack = { nav.popBackStack() }
            )
        }
        composable(Routes.ProfilePhoto) {
            OnboardingSimpleScreen(
                title = "Profile picture",
                subtitle = "URL or skip for now",
                fieldLabel = "Image URL (optional)",
                buttonLabel = "Continue",
                onContinue = { nav.navigate(Routes.Username) },
                onBack = { nav.popBackStack() },
                requireValue = false
            )
        }
        composable(Routes.Username) {
            OnboardingSimpleScreen(
                title = "Username",
                subtitle = "Unique handle",
                fieldLabel = "username",
                buttonLabel = "Continue",
                onContinue = { nav.navigate(Routes.DisplayName) },
                onBack = { nav.popBackStack() }
            )
        }
        composable(Routes.DisplayName) {
            OnboardingSimpleScreen(
                title = "Display name",
                subtitle = "How friends see you",
                fieldLabel = "Display name",
                buttonLabel = "Continue",
                onContinue = { nav.navigate(Routes.ContactsPermission) },
                onBack = { nav.popBackStack() }
            )
        }
        composable(Routes.ContactsPermission) {
            PermissionPromptScreen(
                title = "Contacts",
                body = "Find friends who already use NO ONE CHAT. Matching uses a privacy-preserving HMAC Edge Function.",
                onAllow = { nav.navigate(Routes.NotificationsPermission) },
                onSkip = { nav.navigate(Routes.NotificationsPermission) },
                onBack = { nav.popBackStack() }
            )
        }
        composable(Routes.NotificationsPermission) {
            PermissionPromptScreen(
                title = "Notifications",
                body = "Stay aware of messages and calls. Push payloads avoid plaintext by default.",
                onAllow = {
                    nav.navigate(Routes.Home) {
                        popUpTo(Routes.Welcome) { inclusive = true }
                    }
                },
                onSkip = {
                    nav.navigate(Routes.Home) {
                        popUpTo(Routes.Welcome) { inclusive = true }
                    }
                },
                onBack = { nav.popBackStack() }
            )
        }

        composable(Routes.Home) {
            HomeShell(rootNav = nav)
        }

        composable(
            route = Routes.ChatDetail,
            arguments = listOf(navArgument("conversationId") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("conversationId").orEmpty()
            NoOneScreen(title = "Chat", subtitle = id, onBack = { nav.popBackStack() }) {
                MessageBubble(
                    text = "Conversation shell ready. Realtime + E2EE come next.",
                    outgoing = false,
                    timeLabel = "now",
                    delivery = MessageDeliveryState.Delivered
                )
            }
        }

        composable(Routes.NewChat) {
            FeaturePlaceholder(
                title = "New chat",
                body = "Pick a contact or start a conversation after contacts sync.",
                onBack = { nav.popBackStack() },
                primaryActionLabel = "New group",
                onPrimaryAction = { nav.navigate(Routes.NewGroup) }
            )
        }
        composable(Routes.NewGroup) {
            FeaturePlaceholder(
                title = "New group",
                body = "Group creation, roles, and group encryption land in Phase 10.",
                onBack = { nav.popBackStack() }
            )
        }
        composable(Routes.ArchivedChats) {
            FeaturePlaceholder(
                title = "Archived chats",
                body = "Archived conversations will list here.",
                onBack = { nav.popBackStack() }
            )
        }
        composable(Routes.QrScanner) {
            FeaturePlaceholder(
                title = "QR scanner",
                body = "Scan a user QR to preview profile and send a contact request.",
                onBack = { nav.popBackStack() }
            )
        }
        composable(Routes.ContactRequests) {
            FeaturePlaceholder(
                title = "Contact requests",
                body = "Accept or reject incoming contact requests.",
                onBack = { nav.popBackStack() }
            )
        }

        composable(Routes.Privacy) {
            FeaturePlaceholder("Privacy", "Last seen, read receipts, status privacy.", { nav.popBackStack() })
        }
        composable(Routes.Security) {
            FeaturePlaceholder("Security", "Devices, key verification, session revoke.", { nav.popBackStack() })
        }
        composable(Routes.Notifications) {
            FeaturePlaceholder("Notifications", "Channels and preference toggles.", { nav.popBackStack() })
        }
        composable(Routes.StorageData) {
            FeaturePlaceholder("Storage and data", "Media cache, usage, cleanup.", { nav.popBackStack() })
        }
        composable(Routes.Account) {
            FeaturePlaceholder("Account", "Logout, devices, delete account.", { nav.popBackStack() })
        }
        composable(Routes.Help) {
            FeaturePlaceholder("Help and support", "FAQ and support contact.", { nav.popBackStack() })
        }
    }
}
