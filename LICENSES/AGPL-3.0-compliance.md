# AGPL-3.0 Compliance Declaration

## Decision

`oReader` embeds the KOReader runtime, which is licensed under the
**GNU Affero General Public License v3.0 (AGPL-3.0)**.

As a combined work, `oReader` **accepts AGPL-3.0 distribution requirements**.
All releases of the combined application must satisfy the following obligations:

## Obligations

1. **Source Availability** — Every distributed binary (APK/AAB) of `oReader`
   that includes KOReader-derived code must be accompanied by, or include a
   written offer for, the complete corresponding source code of the combined
   work.

2. **License Preservation** — AGPL-3.0 license text must be preserved in the
   distributed application and in source repositories.

3. **Notice Retention** — All copyright and license notices from upstream
   KOReader, koreader-base, and android-luajit-launcher must be retained
   unmodified in source and binary distributions.

4. **Modification Disclosure** — Any modifications to AGPL-licensed source
   files must be clearly marked with the nature and date of the change, as
   required by AGPL-3.0 §5.

5. **Network Use** — If `oReader` offers interaction through a computer
   network, the application must offer all users interacting with it remotely
   the opportunity to receive the corresponding source (AGPL-3.0 §13). As a
   local document reader, this clause is **not currently triggered**.

## Scope

The following vendored components fall under AGPL-3.0:

| Component                  | Upstream Repository                                     | License    |
|----------------------------|---------------------------------------------------------|------------|
| koreader                   | https://github.com/koreader/koreader                    | AGPL-3.0   |
| koreader-base              | https://github.com/koreader/koreader-base               | AGPL-3.0   |
| android-luajit-launcher    | https://github.com/nickel-halide/android-luajit-launcher| AGPL-3.0   |

### Third-Party Sub-Dependencies

KOReader bundles additional third-party libraries (MuPDF, CREngine, DjVuLibre,
LuaJIT, k2pdfopt, etc.) under their own licenses. A complete third-party
attribution file must be generated and included with each release build. See
`LICENSES/THIRD-PARTY-NOTICES.md`.

## oReader-Original Code

Code written specifically for `oReader` (Kotlin/Compose host shell, bridge
modules, build-logic) that does not derive from AGPL-licensed sources may be
dual-licensed or separately licensed at the project owner's discretion,
**provided** the combined work as distributed satisfies AGPL-3.0 for the
KOReader-derived portions.

## Confirmation

- **Decision Date:** 2026-03-29
- **Decision:** AGPL-3.0 compatible distribution is accepted.
- **Rationale:** KOReader provides a mature, production-grade reading
  experience. The licensing terms are acceptable for the intended distribution
  channels (sideloaded APK for Android/e-ink devices).
