package com.noonechat.app.data.session

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("no_one_session")

class SessionStore(private val context: Context) {
    private val authenticatedKey = booleanPreferencesKey("authenticated")
    private val userIdKey = stringPreferencesKey("user_id")

    val isAuthenticated: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[authenticatedKey] == true
    }

    val userId: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[userIdKey]
    }

    suspend fun setAuthenticated(userId: String) {
        context.dataStore.edit { prefs ->
            prefs[authenticatedKey] = true
            prefs[userIdKey] = userId
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
