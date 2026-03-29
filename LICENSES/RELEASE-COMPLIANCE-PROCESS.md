# Release Compliance Process

## Source Publication

### Requirements
Every distributed build of `oReader` that includes KOReader-derived code must
make the corresponding source available per AGPL-3.0 §6.

### Process

1. **Tag the release** in the `oReader` Git repository. The tag must include:
   - All vendored KOReader source (submodules pinned to exact commits)
   - Any local patches applied in `vendor/patches/`
   - The complete `oReader` Kotlin/Compose source

2. **Publish the tagged source** to the project's public repository or make it
   available via a written offer included in the APK's `NOTICE` file.

3. **Include a source notice** in the APK itself (accessible from
   Settings → About → Licenses) with:
   - URL to the source repository
   - Git tag/commit hash of the build
   - Instructions for obtaining the source

## Notice Files

### In-App Notices
The app must include the following discoverable information:

- `LICENSES/AGPL-3.0-compliance.md` — Compliance declaration
- `LICENSES/AGPL-3.0.txt` — Full AGPL-3.0 license text
- `LICENSES/THIRD-PARTY-NOTICES.md` — Attribution for all bundled libraries

### APK Metadata
The release APK/AAB must include in `assets/licenses/`:
- `COPYING` — AGPL-3.0 full text
- `NOTICE` — Summary of license obligations and source URL
- `THIRD-PARTY` — Generated third-party attribution

## Third-Party Attribution

### Generation
A third-party notice file must be generated for each release that inventories:

| Field               | Description                                      |
|---------------------|--------------------------------------------------|
| Library Name        | Name of the dependency                           |
| Version             | Version or commit hash                           |
| License             | SPDX identifier (e.g., `Apache-2.0`, `LGPL-2.1`)|
| Copyright           | Copyright holder(s)                              |
| Source URL           | Location of the source code                      |

### Upstream KOReader Dependencies
Key libraries to attribute:

- **LuaJIT** — MIT License
- **MuPDF** — AGPL-3.0
- **CREngine** — GPL-3.0
- **DjVuLibre** — GPL-2.0+
- **k2pdfopt** — GPL-3.0
- **FreeType** — FTL / GPL-2.0
- **HarfBuzz** — MIT
- **zlib** — zlib License
- **libjpeg-turbo** — BSD-3-Clause / IJG
- **libpng** — libpng License
- **OpenSSL** — Apache-2.0 (3.x)

### oReader Dependencies
Standard Android/Kotlin dependencies to attribute:

- **AndroidX** — Apache-2.0
- **Hilt/Dagger** — Apache-2.0
- **OkHttp/Retrofit** — Apache-2.0
- **Moshi** — Apache-2.0
- **Timber** — Apache-2.0
- **MMKV** — BSD-3-Clause
- **ImmersionBar** — Apache-2.0

## Checklist

Before each release:

- [ ] Source repository is tagged with the exact build commit
- [ ] Vendored submodules are pinned to the correct upstream commits
- [ ] Local patches are documented in `vendor/patches/README.md`
- [ ] `LICENSES/THIRD-PARTY-NOTICES.md` is regenerated
- [ ] APK `assets/licenses/` directory is populated
- [ ] In-app license viewer displays correct information
- [ ] Source URL and commit hash are embedded in the build
