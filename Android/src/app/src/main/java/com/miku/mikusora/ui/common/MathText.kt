package com.miku.mikusora.ui.common

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.runtime.Composable
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

  // Reuse a single WebView instance per composable to avoid memory leaks
  val context = LocalContext.current
  val webView = remember {
    WebView(context).apply {
      settings.javaScriptEnabled = true
      settings.allowFileAccess = true
      settings.domStorageEnabled = true
      loadUrl("file:///android_asset/katex/index.html")
    }
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
