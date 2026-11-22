package com.noonechat.app.ui.navigation

object Routes {
    const val Splash = "splash"
    const val Welcome = "welcome"
    const val Phone = "auth/phone"
    const val Otp = "auth/otp"
    const val ProfilePhoto = "onboarding/photo"
    const val Username = "onboarding/username"
    const val DisplayName = "onboarding/display_name"
    const val ContactsPermission = "onboarding/contacts_permission"
    const val NotificationsPermission = "onboarding/notifications_permission"

    const val Home = "home"
    const val Chats = "chats"
    const val Status = "status"
    const val Contacts = "contacts"
    const val Calls = "calls"
    const val Profile = "profile"

    const val NewChat = "chats/new"
    const val NewGroup = "chats/new_group"
    const val ChatDetail = "chats/{conversationId}"
    const val ArchivedChats = "chats/archived"
    const val SharedMedia = "chats/{conversationId}/media"

    const val QrScanner = "contacts/qr"
    const val ContactRequests = "contacts/requests"

    const val Privacy = "settings/privacy"
    const val Security = "settings/security"
    const val Notifications = "settings/notifications"
    const val StorageData = "settings/storage"
    const val Account = "settings/account"
    const val Help = "settings/help"

    fun chatDetail(id: String) = "chats/$id"
    fun sharedMedia(id: String) = "chats/$id/media"
}
