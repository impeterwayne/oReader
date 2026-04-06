package com.genesys.feature.notebook.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID

/**
 * Snack bar configuration.
 */
data class SnackConf(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val duration: Long = 3000
)

/**
 * Simple snack/toast state management bridging Notable's SnackState.
 */
class SnackState {
    fun displaySnack(conf: SnackConf) {
        globalSnackFlow.tryEmit(conf)
    }

    suspend fun <T> showSnackDuring(text: String, block: suspend () -> T): T {
        globalSnackFlow.emit(SnackConf(text = text))
        return block()
    }

    companion object {
        val globalSnackFlow = MutableSharedFlow<SnackConf>(extraBufferCapacity = 1)
        val cancelGlobalSnack = MutableSharedFlow<String>(extraBufferCapacity = 1)

        fun logAndShowError(tag: String, msg: String, e: Throwable? = null) {
            Log.e(tag, msg, e)
            globalSnackFlow.tryEmit(SnackConf(text = "$msg: ${e?.message ?: "unknown"}"))
        }
    }
}

/**
 * Local composition providing snack context to the editor.
 */
val LocalSnackContext = compositionLocalOf<SnackState> {
    SnackState()
}

/**
 * Show a toast/hint message. Replaces Notable's `showHint`.
 */
fun showHint(text: String, scope: CoroutineScope, duration: Long = 3000) {
    Log.d("Hint", text)
    scope.launch {
        SnackState.globalSnackFlow.emit(SnackConf(text = text, duration = duration))
    }
}

/**
 * Show a hint without a CoroutineScope (uses tryEmit).
 */
fun showHint(text: String, duration: Long = 3000) {
    Log.d("Hint", text)
    SnackState.globalSnackFlow.tryEmit(SnackConf(text = text, duration = duration))
}

/**
 * Clickable modifier without the ripple effect, used by toolbar buttons.
 */
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
    )
}

/**
 * Convert dp to pixels.
 */
fun convertDpToPixel(dp: Dp, context: Context): Float {
    return dp.value * context.resources.displayMetrics.density
}
