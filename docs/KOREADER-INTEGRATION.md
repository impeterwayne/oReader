# KOReader Integration — Maintainer Guide

## Overview

oReader embeds the KOReader reading runtime as a subsystem. This document
covers the upstream sync workflow, local patch points, and release obligations.

## Architecture

```
oReader (Compose App Shell)
├── app/                    — Main application, MainActivity, AppShell navigation
├── feature/koreader/       — KOReader host module
│   ├── host/               — KoreaderActivity (lifecycle boundary)
│   ├── bridge/             — Storage, intent, and reading state bridges
│   ├── runtime/            — Runtime initialization, directories, asset extraction
│   └── di/                 — Hilt dependency injection
├── vendor/                 — Vendored upstream sources
│   └── android-luajit-launcher/ — Android NativeActivity for LuaJIT
└── LICENSES/               — Compliance documentation
```

## Runtime Distribution

oReader uses prebuilt KOReader runtime APKs from official GitHub releases.
The `feature/koreader` module downloads and extracts assets and native libraries
at build time.

The `vendor/android-luajit-launcher` directory contains the launcher Activity
source code that bridges between the Compose app shell and the KOReader runtime.

### Updating KOReader Version

Edit `feature/koreader/build.gradle.kts`:

```kotlin
val koreaderReleaseTag = "v2026.03"  // Update this
val koreaderArtifactSha256 = "..."   // Update checksum from GitHub release
```

## Integration Points

| Component         | File / Area                              | Purpose                          |
|-------------------|------------------------------------------|----------------------------------|
| Host boundary     | `feature/koreader/host/KoreaderActivity` | Lifecycle management             |
| Storage bridge    | `feature/koreader/bridge/`               | URI → file path conversion       |
| Runtime init      | `feature/koreader/runtime/`              | Asset extraction, dir setup      |
| Launch contracts  | `feature/koreader/host/KoreaderContracts`| Type-safe Activity launch APIs   |
| App navigation    | `app/.../navigation/AppShell.kt`         | Reader tab → KOReader routing    |
| Intent handling   | `app/.../MainActivity.kt`               | ACTION_VIEW → KOReader handoff   |


## Release Obligations

### Before Each Release

1. ✅ Source repository tagged with exact build commit
2. ✅ KOReader release version documented in build.gradle.kts
3. ✅ `LICENSES/THIRD-PARTY-NOTICES.md` generated
4. ✅ APK `assets/licenses/` populated
5. ✅ AGPL-3.0 source availability ensured

### License Files

- `LICENSES/AGPL-3.0-compliance.md` — Compliance declaration
- `LICENSES/RELEASE-COMPLIANCE-PROCESS.md` — Release checklist
- `LICENSES/DEVICE-ABI-MATRIX.md` — Supported devices and ABIs

## Build Configuration

### Convention Plugin

The `codebase.android.koreader` convention plugin configures:
- ABI filters (default: `arm64-v8a`)
- JNI library source directories
- Asset packaging for KOReader Lua runtime

Override ABI filters:
```properties
# gradle.properties
KOREADER_ABI_FILTERS=arm64-v8a,armeabi-v7a
```

### Module Dependencies

```
app → feature:koreader → core:common
```

The koreader module is intentionally **not** a feature module (doesn't use
`codebase.android.feature`) because it doesn't need Compose, model, or domain
dependencies. It uses `codebase.android.koreader` + `codebase.android.hilt`.

## Troubleshooting

### Runtime doesn't initialize
- Check logcat for `KoreaderRuntime` tag
- Verify assets are present in the APK: `unzip -l app.apk | grep koreader`
- Ensure storage permissions are granted on pre-API 33 devices

### Reader opens KOReader file manager
- Confirm the reader tab has granted storage access before opening a book
- Verify incoming files are added to the in-app library instead of being launched directly
- Check the reader library refreshes after storage permission or incoming file events

### Native libraries not found
- Check build output for extracted libraries
- Verify ABI matches the device: `adb shell getprop ro.product.cpu.abi`
- Confirm the downloaded APK matches the expected SHA-256 checksum
