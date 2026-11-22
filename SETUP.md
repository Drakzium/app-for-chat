# NO ONE CHAT — Setup

## Prerequisites

1. Android Studio Ladybug or newer (or compatible)
2. JDK 17
3. Android SDK 35 + build-tools
4. Physical device or emulator (API 26+)
5. Supabase project (Auth phone, Postgres, Storage, Edge Functions)
6. Firebase project (FCM) — for push phase
7. TURN/STUN credentials — for calling phase

## Clone / open

1. Open this folder in Android Studio.
2. Let Gradle sync create/use the wrapper.
3. Create `local.properties` if missing:

```properties
sdk.dir=C\:\\Users\\<YOU>\\AppData\\Local\\Android\\Sdk
```

## App secrets (do not commit)

Create `app/secrets.properties` (gitignored) or use Gradle secrets:

```properties
SUPABASE_URL=https://YOUR_PROJECT.supabase.co
SUPABASE_ANON_KEY=your_anon_key
```

Service-role key stays server-only (Edge Functions / CI), never in the APK.

## Supabase

1. Create project.
2. Enable Phone Auth / OTP provider as required by region.
3. Apply SQL migrations from `supabase/migrations/` (added as phases progress).
4. Enable RLS on all user tables.
5. Create Storage buckets: `avatars`, `banners`, `attachments`, `status` (private + signed URLs).
6. Deploy Edge Functions when introduced (contact match, media access).

## Build

```bash
./gradlew :app:assembleDebug
```

Install debug APK on two devices/accounts for messaging/call tests.

## Environment checklist

- [ ] JDK 17
- [ ] Android SDK 35
- [ ] `local.properties`
- [ ] Supabase URL + anon key
- [ ] Phone OTP test numbers (Supabase dashboard)
- [ ] FCM `google-services.json` (notifications phase)
- [ ] TURN server (calls phase)
