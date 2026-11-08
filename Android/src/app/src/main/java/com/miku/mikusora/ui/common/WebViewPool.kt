package com.miku.mikusora.ui.common

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebView
import java.util.ArrayDeque
import java.util.concurrent.ConcurrentHashMap

/**
 * Pool pequeño y reutilizable de WebViews para renderizado KaTeX.
 * Limita el número de WebViews vivos a 4 para evitar fugas de memoria
 * en chats largos con muchas fórmulas matemáticas.
 */
object WebViewPool {
  private val MAX_WEBVIEWS = 4
  private val available = ArrayDeque<WebView>()
  private val inUse = ConcurrentHashMap<WebView, Boolean>()

  @SuppressLint("SetJavaScriptEnabled")
  fun acquire(context: Context): WebView? {
    // Reutilizar uno disponible
    val reused = available.poll()
    if (reused != null) {
      inUse[reused] = true
      return reused
    }
    // Crear uno nuevo si hay cupo
    if (inUse.size < MAX_WEBVIEWS) {
      val wv = WebView(context).apply {
        settings.javaScriptEnabled = true
        settings.allowFileAccess = true
        settings.domStorageEnabled = true
        loadUrl("file:///android_asset/katex/index.html")
      }
      inUse[wv] = true
      return wv
    }
    // Pool lleno: no hay WebView disponible
    return null
  }

  fun release(webView: WebView) {
    inUse.remove(webView)
    if (available.size < MAX_WEBVIEWS) {
      available.offer(webView)
    }
    // Si el pool de disponibles está lleno, el WebView se descarta para ser recolectado por el GC
  }

  fun activeCount(): Int {
    return inUse.size
  }
}
