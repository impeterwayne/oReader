# Vendor Upstream Sync Strategy

## Overview

KOReader upstream sources are vendored as Git submodules under `vendor/`.

## Submodules

| Submodule                      | Path                              | Upstream                                                |
|--------------------------------|-----------------------------------|---------------------------------------------------------|
| koreader                       | `vendor/koreader`                 | https://github.com/koreader/koreader                    |
| koreader-base                  | `vendor/koreader-base`            | https://github.com/koreader/koreader-base               |
| android-luajit-launcher        | `vendor/android-luajit-launcher`  | https://github.com/koreader/android-luajit-launcher     |

## Initial Clone

```bash
git clone --recurse-submodules <oReader-repo-url>
# or after a regular clone:
git submodule update --init --recursive
```

## Updating Upstream

### Routine Update

```bash
# 1. Check for upstream updates
cd vendor/koreader
git fetch origin
git log --oneline HEAD..origin/master

# 2. Update to a specific release tag
git checkout v2024.11  # example release tag

# 3. Repeat for koreader-base and android-luajit-launcher
cd ../koreader-base
git fetch origin && git checkout <matching-commit>

cd ../android-luajit-launcher
git fetch origin && git checkout <matching-commit>

# 4. Return to oReader root and commit
cd ../..
git add vendor/
git commit -m "vendor: update KOReader to v2024.11"
```

### Checking Compatibility

Before updating, verify:
1. koreader-base version matches what koreader expects (check koreader's Makefile/submodule refs)
2. android-luajit-launcher version is compatible with koreader-base
3. Build succeeds with `./gradlew app:assembleDebug`
4. Basic smoke test passes on target device

## Local Patches

Local patches to vendored code should be **avoided** whenever possible. When
necessary:

1. Create patches in `vendor/patches/<component>/`
2. Name patches with a numeric prefix: `001-description.patch`
3. Document each patch in `vendor/patches/README.md`
4. Apply patches after submodule update: `git apply vendor/patches/<component>/*.patch`

### Patch Format

```bash
# Generate a patch from within a submodule
cd vendor/koreader
git diff > ../../vendor/patches/koreader/001-custom-storage-path.patch

# Apply patches after update
cd vendor/koreader
git apply ../../vendor/patches/koreader/*.patch
```

## Version Pinning

The `.gitmodules` file tracks submodule URLs. Exact commits are recorded in
the parent repo's Git tree. Always commit submodule pointer changes explicitly.

## CI Considerations

- CI must run `git submodule update --init --recursive` before building
- Submodule checkout may require authenticated access if repos become private
- Cache submodule clones to speed up CI builds
