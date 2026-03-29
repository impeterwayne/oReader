# Device and ABI Support Matrix

## Initial ABI Support

| ABI           | Priority  | Status   | Notes                                          |
|---------------|-----------|----------|-------------------------------------------------|
| `arm64-v8a`   | Primary   | Required | All modern Android/e-ink devices               |
| `armeabi-v7a` | Secondary | Deferred | Legacy 32-bit devices; add if needed            |
| `x86_64`      | Optional  | Deferred | Emulator/Chromebook; useful for development     |
| `x86`         | Optional  | Deferred | Legacy emulators only                           |

### Rationale
- `arm64-v8a` covers all modern Onyx Boox, Kobo (with Android), and most
  Android tablets/phones from 2017 onwards.
- Starting with a single ABI keeps the initial APK size manageable and
  simplifies native build validation.
- Additional ABIs can be added incrementally once the runtime is proven stable.

## Target Devices

### Primary Targets (Must Work)

| Device Class     | Example Devices               | Android Version | Notes                    |
|------------------|-------------------------------|-----------------|--------------------------|
| Onyx Boox E-Ink  | Tab Ultra C, Note Air 3, etc. | Android 11-13   | Primary target platform  |
| Android Tablet   | Generic ARM64 tablet          | Android 10+     | Secondary validation     |

### Secondary Targets (Should Work)

| Device Class     | Example Devices               | Android Version | Notes                    |
|------------------|-------------------------------|-----------------|--------------------------|
| Android Phone    | Generic ARM64 phone           | Android 10+     | Not optimized for small  |
| Android Emulator | x86_64 AVD                    | API 30+         | Development only         |

### Out of Scope (Initial Release)

| Device Class     | Reason                                                |
|------------------|-------------------------------------------------------|
| Kobo (non-Android)| Requires separate KOReader distribution model        |
| Kindle            | Requires separate KOReader distribution model        |
| PocketBook        | Requires separate KOReader distribution model        |

## Android Version Requirements

| Parameter       | Value         | Rationale                                    |
|-----------------|---------------|----------------------------------------------|
| `minSdk`        | 24 (Android 7)| Matches current oReader minimum              |
| `targetSdk`     | 36            | Matches current oReader target               |
| `compileSdk`    | 36            | Matches current oReader compile SDK          |

## NDK Requirements

| Parameter       | Value         | Rationale                                    |
|-----------------|---------------|----------------------------------------------|
| NDK Version     | r26+          | LuaJIT and koreader-base compatibility       |
| CMake Version   | 3.22+         | Required for native build scripts            |

## Decision

- **Initial release ABI:** `arm64-v8a` only
- **Primary device target:** Onyx Boox e-ink tablets (Android 11+)
- **Distribution channel:** Sideloaded APK (no Play Store initially)
- **Expansion plan:** Add `armeabi-v7a` if user demand exists; add `x86_64` for
  emulator development convenience.
