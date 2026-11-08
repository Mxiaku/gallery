package com.miku.mikusora.ui.common

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
  val escapedText = JSONObject.quote(text)

  AndroidView(
    factory = { context ->
      WebView(context).apply {
        settings.javaScriptEnabled = true
        settings.allowFileAccess = true
        settings.domStorageEnabled = true
        loadUrl("file:///android_asset/katex/index.html")
      }
    },
    update = { webView ->
      webView.evaluateJavascript("renderMath($escapedText)", null)
    },
    modifier = modifier,
  )
}
