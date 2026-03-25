package com.genesys.codebase

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import net.timelegend.chaka.viewer.DocumentActivity

class LibraryActivity : ComponentActivity() {

    private var selectingDocument = false

    private val openDocument =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            selectingDocument = false

            if (uri == null) {
                finish()
                return@registerForActivityResult
            }

            val intent = Intent(this, DocumentActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
                addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                action = Intent.ACTION_VIEW
                setDataAndType(uri, contentResolver.getType(uri))
                putExtra("$packageName.ReturnToLibraryActivity", 1)
            }

            startActivity(intent)

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectingDocument = false
    }

    override fun onStart() {
        super.onStart()

        if (!selectingDocument) {
            selectingDocument = true
            openDocument.launch(
                arrayOf(
                    "application/pdf",
                    "application/vnd.ms-xpsdocument",
                    "application/oxps",
                    "application/x-cbz",
                    "application/vnd.comicbook+zip",
                    "application/epub+zip",
                    "application/x-fictionbook",
                    "application/x-mobipocket-ebook",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                    "application/vnd.openxmlformats-officedocument.presentationml.presentation",
                    "text/html",
                    "text/plain",
                    "application/x-gzip",
                    "application/zip",
                    "application/octet-stream",
                )
            )
        }
    }
}
