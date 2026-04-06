package org.koreader.launcher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            TextView(this).apply {
                text = "Test activity"
                setPadding(32, 32, 32, 32)
            }
        )
    }
}
