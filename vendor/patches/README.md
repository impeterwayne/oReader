# Vendor Patches

This directory holds local patches applied to vendored KOReader submodules.

## Structure

```
patches/
├── koreader/           # Patches for vendor/koreader
├── koreader-base/      # Patches for vendor/koreader-base
├── android-luajit-launcher/  # Patches for vendor/android-luajit-launcher
└── README.md           # This file
```

## Applying Patches

After updating submodules, apply all patches:

```bash
cd vendor/koreader && git apply ../../vendor/patches/koreader/*.patch 2>/dev/null; cd ../..
cd vendor/koreader-base && git apply ../../vendor/patches/koreader-base/*.patch 2>/dev/null; cd ../..
cd vendor/android-luajit-launcher && git apply ../../vendor/patches/android-luajit-launcher/*.patch 2>/dev/null; cd ../..
```

## Current Patches

_None yet. Patches will be added as integration requires local modifications._
