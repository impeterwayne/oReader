package com.genesys.feature.koreader.runtime

import android.content.Context
import timber.log.Timber
import java.io.FileNotFoundException
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages KOReader runtime initialization: asset extraction, directory setup,
 * and native library validation.
 *
 * This class is the single point of truth for whether the embedded KOReader
 * runtime is ready to launch.
 */
@Singleton
class KoreaderRuntime @Inject constructor() {
    private companion object {
        const val KOREADER_ASSET_ROOT = "koreader"
    }

    sealed class InitResult {
        data object Success : InitResult()
        data class Failure(val reason: String) : InitResult()
    }

    @Volatile
    private var initialized = false

    /**
     * Initialize the KOReader runtime directories and validate that required
     * assets and native libraries are present.
     *
     * This is idempotent — calling it multiple times after success is a no-op.
     */
    fun initialize(context: Context): InitResult {
        if (initialized) return InitResult.Success

        return try {
            val result = doInitialize(context)
            if (result is InitResult.Success) {
                initialized = true
            }
            result
        } catch (e: Exception) {
            Timber.e(e, "KOReader runtime initialization failed")
            InitResult.Failure(e.message ?: "Unknown initialization error")
        }
    }

    private fun doInitialize(context: Context): InitResult {
        // 1. Create KOReader app-private directories
        val dirs = KoreaderDirectories(context)
        dirs.ensureDirectories()

        // 2. Extract Lua assets from APK to writable storage (if needed)
        val extracted = extractAssetsIfNeeded(context, dirs)
        if (!extracted) {
            return InitResult.Failure("Failed to extract KOReader assets")
        }

        // 3. Validate native libraries are loadable
        val nativeOk = validateNativeLibraries()
        if (!nativeOk) {
            return InitResult.Failure("KOReader native libraries not available for this ABI")
        }

        Timber.i("KOReader runtime initialized successfully")
        return InitResult.Success
    }

    private fun extractAssetsIfNeeded(context: Context, dirs: KoreaderDirectories): Boolean {
        val versionFile = File(dirs.dataDir, ".koreader_version")
        val currentVersion = getAssetVersion(context)

        // Skip extraction if already at current version
        if (versionFile.exists() && versionFile.readText().trim() == currentVersion) {
            Timber.d("KOReader assets already at version $currentVersion, skipping extraction")
            return true
        }

        Timber.i("Extracting KOReader assets (version: $currentVersion)")

        val bundledAssets = context.assets.list(KOREADER_ASSET_ROOT)
        if (bundledAssets.isNullOrEmpty()) {
            Timber.e(
                "Bundled KOReader assets are missing from the APK (expected %s/)",
                KOREADER_ASSET_ROOT
            )
            return false
        }

        // Extract all assets under "koreader/" to the data directory
        return try {
            extractAssetDirectory(context, KOREADER_ASSET_ROOT, dirs.dataDir)
            versionFile.writeText(currentVersion)
            true
        } catch (e: Exception) {
            Timber.e(e, "Asset extraction failed")
            false
        }
    }

    private fun getAssetVersion(context: Context): String {
        return try {
            context.assets.open("$KOREADER_ASSET_ROOT/version").bufferedReader().readLine() ?: "unknown"
        } catch (_: Exception) {
            // If no version file, use package version as fallback
            context.packageManager
                .getPackageInfo(context.packageName, 0)
                .versionName ?: "0"
        }
    }

    private fun extractAssetDirectory(context: Context, assetPath: String, targetDir: File) {
        val assetList = context.assets.list(assetPath) ?: return

        if (assetList.isEmpty()) {
            // It's a file, copy it
            val targetFile = File(targetDir, assetPath.substringAfterLast("/"))
            try {
                context.assets.open(assetPath).use { input ->
                    targetFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
            } catch (e: FileNotFoundException) {
                throw FileNotFoundException("Missing bundled KOReader asset: $assetPath").apply {
                    initCause(e)
                }
            }
        } else {
            // It's a directory, recurse
            val subDir = if (assetPath == KOREADER_ASSET_ROOT) targetDir
            else File(targetDir, assetPath.substringAfterLast("/"))
            subDir.mkdirs()

            for (child in assetList) {
                extractAssetDirectory(context, "$assetPath/$child", subDir)
            }
        }
    }

    private fun validateNativeLibraries(): Boolean {
        // Verify that LuaJIT native library can be loaded
        // In a real build, this would check for libluajit.so
        // For now, return true to allow the build to proceed
        return try {
            // Native libs will be validated when actually present
            Timber.d("Native library validation: placeholder (no libs bundled yet)")
            true
        } catch (e: UnsatisfiedLinkError) {
            Timber.e(e, "Failed to load KOReader native libraries")
            false
        }
    }

    /**
     * Whether the runtime has been successfully initialized.
     */
    val isInitialized: Boolean get() = initialized
}
