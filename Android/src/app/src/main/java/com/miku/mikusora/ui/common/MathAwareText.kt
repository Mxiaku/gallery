package com.miku.mikusora.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/** Detects whether [text] contains LaTeX math delimiters. */
fun containsMath(text: String): Boolean {
  // Check for $$...$$, $...$, \(...\), \[...\]
  val blockMath = Regex("""\$\$[\s\S]+?\$\$""")
  val inlineMath = Regex("""(?<!\$)\$(?!\$)[^$\n]+?\$(?!\$)""")
  val parenMath = Regex("""\\\([\s\S]+?\\\)""")
  val bracketMath = Regex("""\\\[[\s\S]+?\\\]""")
  return blockMath.containsMatchIn(text) ||
    inlineMath.containsMatchIn(text) ||
    parenMath.containsMatchIn(text) ||
    bracketMath.containsMatchIn(text)
}

/** Composable that renders text with math support when math delimiters are detected. */
@Composable
fun MathAwareText(
  text: String,
  modifier: Modifier = Modifier,
  darkMode: Boolean = false,
  fallback: @Composable (String, Modifier) -> Unit,
) {
  if (containsMath(text)) {
    MathText(text = text, modifier = modifier, darkMode = darkMode)
  } else {
    fallback(text, modifier)
  }
}
