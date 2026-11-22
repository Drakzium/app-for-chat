package com.noonechat.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.noonechat.app.ui.navigation.Routes
import com.noonechat.app.ui.screens.calls.CallsScreen
import com.noonechat.app.ui.screens.chats.ChatsScreen
import com.noonechat.app.ui.screens.contacts.ContactsScreen
import com.noonechat.app.ui.screens.profile.ProfileScreen
import com.noonechat.app.ui.screens.status.StatusScreen
import com.noonechat.app.ui.theme.Background
import com.noonechat.app.ui.theme.CardBackground
import com.noonechat.app.ui.theme.Cyan
import com.noonechat.app.ui.theme.PrimaryText
import com.noonechat.app.ui.theme.SecondaryText

private data class TabItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

private val tabs = listOf(
    TabItem(Routes.Chats, "Chats", Icons.Filled.ChatBubble),
    TabItem(Routes.Status, "Status", Icons.Filled.Timelapse),
    TabItem(Routes.Contacts, "Contacts", Icons.Filled.Contacts),
    TabItem(Routes.Calls, "Calls", Icons.Filled.Call),
    TabItem(Routes.Profile, "Profile", Icons.Filled.Person)
)

@Composable
fun HomeShell(
    rootNav: NavHostController,
    modifier: Modifier = Modifier
) {
    val tabNav = rememberNavController()
    val backStack by tabNav.currentBackStackEntryAsState()
    val current = backStack?.destination

    Scaffold(
        modifier = modifier.background(Background),
        containerColor = Background,
        bottomBar = {
            NavigationBar(containerColor = CardBackground) {
                tabs.forEach { tab ->
                    val selected = current?.hierarchy?.any { it.route == tab.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            tabNav.navigate(tab.route) {
                                popUpTo(tabNav.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label
                            )
                        },
                        label = { Text(tab.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Cyan,
                            selectedTextColor = PrimaryText,
                            unselectedIconColor = SecondaryText,
                            unselectedTextColor = SecondaryText,
                            indicatorColor = Background
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavHost(
                navController = tabNav,
                startDestination = Routes.Chats
            ) {
                composable(Routes.Chats) {
                    ChatsScreen(
                        onOpenChat = { id -> rootNav.navigate(Routes.chatDetail(id)) },
                        onNewChat = { rootNav.navigate(Routes.NewChat) },
                        onArchived = { rootNav.navigate(Routes.ArchivedChats) }
                    )
                }
                composable(Routes.Status) { StatusScreen() }
                composable(Routes.Contacts) {
                    ContactsScreen(
                        onQr = { rootNav.navigate(Routes.QrScanner) },
                        onRequests = { rootNav.navigate(Routes.ContactRequests) }
                    )
                }
                composable(Routes.Calls) { CallsScreen() }
                composable(Routes.Profile) {
                    ProfileScreen(
                        onPrivacy = { rootNav.navigate(Routes.Privacy) },
                        onSecurity = { rootNav.navigate(Routes.Security) },
                        onNotifications = { rootNav.navigate(Routes.Notifications) },
                        onStorage = { rootNav.navigate(Routes.StorageData) },
                        onAccount = { rootNav.navigate(Routes.Account) },
                        onHelp = { rootNav.navigate(Routes.Help) }
                    )
                }
            }
        }
    }
}
