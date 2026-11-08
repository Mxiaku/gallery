package com.miku.mikusora.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/** Detects whether [text] contains COMPLETE LaTeX math delimiters. */
fun containsCompleteMath(text: String): Boolean {
  // Check for complete $$...$$, $...$, \(...\), \[...\]
  val blockMath = Regex("""\$\$[\s\S]+?\$\$""")
  val inlineMath = Regex("""(?<!\$)\$(?!\$)[^$\n]+?\$(?!\$)""")
  val parenMath = Regex("""\\\([\s\S]+?\\\)""")
  val bracketMath = Regex("""\\\[[\s\S]+?\\\]""")
  return blockMath.containsMatchIn(text) ||
    inlineMath.containsMatchIn(text) ||
    parenMath.containsMatchIn(text) ||
    bracketMath.containsMatchIn(text)
}

/** Detects whether [text] contains ANY math delimiter (complete or incomplete). */
fun containsAnyMath(text: String): Boolean {
  return text.contains("$") || text.contains("\\(") || text.contains("\\[")
}

/** Composable that renders text with math support when complete math delimiters are detected. */
@Composable
fun MathAwareText(
  text: String,
  modifier: Modifier = Modifier,
  darkMode: Boolean = false,
  fallback: @Composable (String, Modifier) -> Unit,
) {
  // Only render math when delimiters are complete (avoids flickering during streaming)
  if (containsCompleteMath(text)) {
    MathText(text = text, modifier = modifier, darkMode = darkMode)
  } else {
    fallback(text, modifier)
  }
}
