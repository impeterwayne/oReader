# KOReader JNI Libraries

Place pre-built KOReader native libraries here, organized by ABI:

```
jniLibs/
├── arm64-v8a/
│   ├── libluajit.so
│   ├── libkoreader.so
│   └── ... (other .so files from koreader-base build)
```

These libraries are produced by the KOReader native build process.
See `vendor/SYNC-STRATEGY.md` for build instructions.
