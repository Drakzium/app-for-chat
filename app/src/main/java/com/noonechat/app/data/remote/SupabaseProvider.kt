package com.noonechat.app.data.remote

import com.noonechat.app.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage

/**
 * Provides Supabase only when secrets are configured.
 * UI shell runs without keys; Phase 4 auth must call [requireClient].
 */
class SupabaseProvider {
    val isConfigured: Boolean
        get() = BuildConfig.SUPABASE_URL.isNotBlank() && BuildConfig.SUPABASE_ANON_KEY.isNotBlank()

    val client: SupabaseClient? by lazy {
        if (!isConfigured) return@lazy null
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_ANON_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Realtime)
            install(Storage)
        }
    }

    fun requireClient(): SupabaseClient {
        return client
            ?: error("Missing SUPABASE_URL / SUPABASE_ANON_KEY in app/secrets.properties")
    }

    companion object {
        fun create(): SupabaseProvider = SupabaseProvider()
    }
}
