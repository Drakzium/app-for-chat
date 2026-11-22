package com.noonechat.app.di

import com.noonechat.app.data.remote.SupabaseProvider
import com.noonechat.app.data.session.SessionStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { SessionStore(androidContext()) }
    single { SupabaseProvider.create() }
}
