# NO ONE CHAT — Project Progress

## Current phase

**Phase 4 next** — Phases 1–3 foundation complete (docs, design system, navigation shell).

## Technology stack

- Kotlin + Jetpack Compose + Material 3
- Navigation Compose, Koin, DataStore
- Supabase Kotlin SDK (Auth/Postgrest/Realtime/Storage) — client optional until secrets set
- Room, WebRTC, FCM, libsignal — planned

## Implemented features

- [x] Repo audit (was empty)
- [x] `ARCHITECTURE.md`, `SETUP.md`, `PROJECT_PROGRESS.md`
- [x] Android app module `com.noonechat.app` (min 26 / target 35)
- [x] Design tokens + theme (navy / electric blue)
- [x] UI kit: buttons (idle/loading/disabled/error), avatar, message bubble, skeletons, screens
- [x] Auth/onboarding UI flow (Welcome → Phone → OTP → profile fields → permissions → Home)
- [x] Main tabs: Chats, Status, Contacts, Calls, Profile
- [x] Secondary routes: New Chat/Group, QR, Requests, Archived, settings stubs, chat detail shell
- [x] SessionStore (DataStore) + Koin DI + optional SupabaseProvider
- [x] SQL migration stub: `supabase/migrations/0001_init_identity.sql`

## Incomplete features

- Live Supabase phone OTP / sessions
- Profiles + Storage uploads
- Contacts / QR / HMAC sync
- Real-time messaging + offline queue
- E2EE (libsignal)
- Status / View Once / disappearing
- Groups
- Voice/video calls
- FCM notifications
- Full RLS coverage for remaining tables
- Device testing

## Modified files (this session)

- Docs: `ARCHITECTURE.md`, `PROJECT_PROGRESS.md`, `SETUP.md`, `.gitignore`
- Gradle: root + `app/build.gradle.kts`, wrapper props + jar, `gradlew.bat`
- App sources under `app/src/main/java/com/noonechat/app/`
- Resources + manifest
- `supabase/migrations/0001_init_identity.sql`
- `app/secrets.properties.example`

## Known bugs / blockers

- **No JDK / Android SDK on this machine** — cannot compile or run APK here.
- Gradle wrapper present; first Android Studio sync still required for full toolchain.
- Supabase secrets not configured yet (`app/secrets.properties`).

## Testing results

- Static scaffold only. No emulator/device run yet.

## Pending tasks

1. Install JDK 17 + Android Studio SDK; open project; Gradle sync; run debug.
2. Create Supabase project; copy anon URL/key to `app/secrets.properties`.
3. Apply `0001_init_identity.sql`; enable Phone Auth.
4. **Phase 4:** Wire OTP send/verify to Supabase Auth; persist session; gate Home.
5. Continue Phase 5+ per master spec.

## Important technical decisions

- Package `com.noonechat.app`; Koin DI; Compose-only UI
- Supabase client does not crash app when secrets missing (UI-dev friendly)
- Service-role key never in client
- Resume rule: read this file + `ARCHITECTURE.md` before re-auditing

## Next implementation steps

1. User: Studio sync + secrets.
2. Agent: Phase 4 AuthRepository (sendOtp/verifyOtp), session gate in `AppNavHost`, profile row creation after verify.
