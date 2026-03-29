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
├── vendor/                 — Vendored upstream sources (Git submodules)
│   ├── koreader/           — KOReader Lua application
│   ├── koreader-base/      — Native rendering engines (MuPDF, CREngine, etc.)
│   └── android-luajit-launcher/ — Android NativeActivity for LuaJIT
└── LICENSES/               — Compliance documentation
```

## Upstream Sync Workflow

See `vendor/SYNC-STRATEGY.md` for detailed instructions.

### Quick Reference

```bash
# Update to a new KOReader release
cd vendor/koreader && git fetch origin && git checkout v2024.11
cd ../koreader-base && git fetch origin && git checkout <matching-commit>
cd ../android-luajit-launcher && git fetch origin && git checkout <matching-commit>
cd ../..
git add vendor/
git commit -m "vendor: update KOReader to v2024.11"

# Apply local patches after update
git apply vendor/patches/koreader/*.patch
git apply vendor/patches/koreader-base/*.patch
git apply vendor/patches/android-luajit-launcher/*.patch
```

## Local Patch Points

### Current Integration Points

| Component         | File / Area                              | Purpose                          |
|-------------------|------------------------------------------|----------------------------------|
| Host boundary     | `feature/koreader/host/KoreaderActivity` | Lifecycle management             |
| Storage bridge    | `feature/koreader/bridge/`               | URI → file path conversion       |
| Runtime init      | `feature/koreader/runtime/`              | Asset extraction, dir setup      |
| Launch contracts  | `feature/koreader/host/KoreaderContracts`| Type-safe Activity launch APIs   |
| App navigation    | `app/.../navigation/AppShell.kt`         | Reader tab → KOReader routing    |
| Intent handling   | `app/.../MainActivity.kt`               | ACTION_VIEW → KOReader handoff   |

### Patch Guidelines

1. **Prefer wrappers over edits** — Wrap upstream behavior in bridge code
   rather than editing KOReader Lua/C sources directly.
2. **Keep patches narrow** — When vendor edits are necessary, change as few
   lines as possible and document the reason.
3. **Track patches** — All vendor patches go in `vendor/patches/` with
   numbered filenames and README documentation.
4. **Test after sync** — After updating submodules, verify: build succeeds,
   runtime boots, document opens, back-navigation works.

## Release Obligations

### Before Each Release

1. ✅ Source repository tagged with exact build commit
2. ✅ Vendored submodules pinned to correct upstream commits
3. ✅ Local patches documented in `vendor/patches/README.md`
4. ✅ `LICENSES/THIRD-PARTY-NOTICES.md` generated
5. ✅ APK `assets/licenses/` populated
6. ✅ AGPL-3.0 source availability ensured

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
- Check `feature/koreader/src/main/jniLibs/<abi>/` contains `.so` files
- Verify ABI matches the device: `adb shell getprop ro.product.cpu.abi`

### Upstream sync conflicts
- Check `vendor/patches/README.md` for known patch points
- Re-apply patches after checkout: `git apply vendor/patches/<component>/*.patch`
