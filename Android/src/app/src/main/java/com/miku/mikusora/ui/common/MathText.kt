package com.miku.mikusora.ui.common

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.json.JSONObject

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MathText(
  text: String,
  modifier: Modifier = Modifier,
  darkMode: Boolean = false,
) {
  val textColor = if (darkMode) "#E0E0E0" else "#000000"
  val bgColor = if (darkMode) "#1C1C1E" else "#FFFFFF"
  val escapedText = JSONObject.quote(text)

  val context = LocalContext.current

  // Acquire WebView from shared pool (max 4 instances)
  val webView = remember {
    WebViewPool.acquire(context)
  }

  // Release WebView back to pool when this composable leaves composition
  DisposableEffect(webView) {
    onDispose {
      if (webView != null) {
        WebViewPool.release(webView)
      }
    }
  }

  // Si no hay WebView disponible (pool lleno), no renderizamos KaTeX y mostramos texto plano
  if (webView == null) {
    androidx.compose.material3.Text(
      text = text,
      modifier = modifier,
      color = androidx.compose.ui.graphics.Color(
        if (darkMode) 0xFFE0E0E0.toInt() else 0xFF000000.toInt()
      ),
    )
    return@Composable
  }

  AndroidView(
    factory = { webView },
    update = { wv ->
      wv.evaluateJavascript(
        "document.body.style.color='$textColor'; document.body.style.backgroundColor='$bgColor'; renderMath($escapedText)",
        null
      )
    },
    modifier = modifier,
  )
}
