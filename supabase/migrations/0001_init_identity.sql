-- Phase 4 baseline: identity tables (RLS enabled; policies expand with auth wiring)
create extension if not exists "pgcrypto";

create table if not exists public.profiles (
    id uuid primary key references auth.users (id) on delete cascade,
    username text unique not null,
    display_name text not null,
    phone_e164 text unique,
    avatar_path text,
    banner_path text,
    bio text default '',
    is_online boolean not null default false,
    last_seen_at timestamptz,
    qr_token text unique not null default encode(gen_random_bytes(16), 'hex'),
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    constraint username_format check (username ~ '^[a-z0-9_]{3,24}$')
);

create table if not exists public.user_devices (
    id uuid primary key default gen_random_uuid(),
    user_id uuid not null references public.profiles (id) on delete cascade,
    device_name text not null,
    platform text not null default 'android',
    push_token text,
    last_active_at timestamptz not null default now(),
    created_at timestamptz not null default now()
);

create table if not exists public.device_sessions (
    id uuid primary key default gen_random_uuid(),
    device_id uuid not null references public.user_devices (id) on delete cascade,
    user_id uuid not null references public.profiles (id) on delete cascade,
    refresh_fingerprint text,
    revoked_at timestamptz,
    created_at timestamptz not null default now()
);

create table if not exists public.user_settings (
    user_id uuid primary key references public.profiles (id) on delete cascade,
    last_seen_visibility text not null default 'contacts',
    read_receipts boolean not null default true,
    contact_sync_enabled boolean not null default true,
    disappearing_default_seconds integer,
    updated_at timestamptz not null default now()
);

create index if not exists idx_profiles_username on public.profiles (username);
create index if not exists idx_profiles_phone on public.profiles (phone_e164);
create index if not exists idx_user_devices_user on public.user_devices (user_id);

alter table public.profiles enable row level security;
alter table public.user_devices enable row level security;
alter table public.device_sessions enable row level security;
alter table public.user_settings enable row level security;

create policy profiles_select_authenticated on public.profiles
    for select to authenticated using (true);

create policy profiles_update_own on public.profiles
    for update to authenticated using (id = auth.uid()) with check (id = auth.uid());

create policy profiles_insert_own on public.profiles
    for insert to authenticated with check (id = auth.uid());

create policy user_devices_own on public.user_devices
    for all to authenticated using (user_id = auth.uid()) with check (user_id = auth.uid());

create policy device_sessions_own on public.device_sessions
    for all to authenticated using (user_id = auth.uid()) with check (user_id = auth.uid());

create policy user_settings_own on public.user_settings
    for all to authenticated using (user_id = auth.uid()) with check (user_id = auth.uid());
