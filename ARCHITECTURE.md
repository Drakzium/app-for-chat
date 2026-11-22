# NO ONE CHAT — Architecture

## Overview

Android messaging client with Supabase backend and WebRTC calling.
Package: `com.noonechat.app`

## Technology Stack

| Layer | Choice |
|-------|--------|
| UI | Jetpack Compose + Material 3 (custom theme) |
| Language | Kotlin 2.x |
| Min / Target SDK | 26 / 35 |
| Navigation | Navigation Compose |
| DI | Koin |
| Async | Coroutines + Flow |
| Local DB | Room (cache, offline queue) |
| Remote DB | Supabase PostgreSQL + Realtime |
| Auth | Supabase Auth (phone OTP) |
| Storage | Supabase Storage (E2EE attachments) |
| Push | Firebase Cloud Messaging |
| Calls | WebRTC (1:1 first; SFU later) |
| E2EE | libsignal-protocol (vetted; no custom crypto) |

## Client Modules

```
app/
  ui/theme          Design tokens, theme
  ui/components     Reusable UI kit
  ui/navigation     Nav graph, bottom bar
  ui/screens        Feature screens
  data/remote       Supabase clients, DTOs
  data/local        Room, DataStore, secure prefs
  data/repository   Repository implementations
  domain/model      Domain models
  domain/usecase    Business use cases
  di                Koin modules
  crypto            Signal protocol wrappers
  calls             WebRTC + signaling
  push              FCM handlers
```

## Backend (Supabase)

- Auth: phone OTP + session refresh
- Postgres: identity, contacts, messaging, status, calls, crypto pubkeys, notifications
- RLS on every user-accessible table
- Edge Functions: contact HMAC match, privileged media access, rate limits
- Realtime: conversation channels, presence, typing
- Storage: media buckets with signed/short-lived access; ciphertext only for sensitive media

## Security Decisions

1. Never ship service-role key in the Android app.
2. Private identity/prekeys stay on-device (Android Keystore + encrypted storage).
3. Server stores public keys and ciphertext only.
4. Push payloads avoid plaintext message bodies by default.
5. Contact sync uses keyed HMAC via Edge Function (anti-enumeration + rate limit).
6. View Once / disappearing: enforce expiry and access in DB + Edge Functions.

## Messaging Flow

1. Client encrypts payload (Signal session).
2. Upload encrypted attachment to Storage if needed.
3. Insert message row (ciphertext + metadata) via authenticated client / RLS.
4. Realtime delivers to members; receipts update delivered/read.
5. Offline: Room outbox → retry with idempotent client message IDs.

## Calling Flow (Stage 1)

1. Create `call_sessions` row + signaling via Realtime/Edge.
2. Exchange SDP/ICE through authenticated channel.
3. Media via WebRTC (DTLS-SRTP); STUN + TURN required for NAT.
4. Persist `call_history` on end/miss.

## Scaling Stages

- **S1 (≤100):** managed Postgres, Realtime, Storage, 1:1 WebRTC
- **S2 (≤1k):** indexes, pagination, rate limits, TURN monitoring
- **S3 (≤10k):** pooling, queues, dedicated call infra
- **S4:** partition if needed, SFU, regional DR

## Navigation Map

Main tabs: Chats | Status | Contacts | Calls | Profile  
Stacks: Auth/Onboarding, Chat detail, New Chat/Group, QR, Settings (Privacy, Security, Notifications, Storage, Account, Help), Shared Media, Archived, Contact Requests

## Visual Identity

Dark navy / electric blue tokens defined in `ui/theme/Color.kt` (see design system).
