package org.koreader.launcher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CrashReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent.getStringExtra("title") ?: "Crash report"
        val reason = intent.getStringExtra("reason").orEmpty()
        setContentView(
            TextView(this).apply {
                text = if (reason.isBlank()) title else "$title\n\n$reason"
                setPadding(32, 32, 32, 32)
            }
        )
    }
}
