package com.miku.mikusora.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/** Detects whether [text] contains COMPLETE LaTeX math delimiters. */
fun containsCompleteMath(text: String): Boolean {
  // Check for complete $$...$$, \(...\), \[...\] first (unambiguous)
  val blockMath = Regex("""\$\$[\s\S]+?\$\$""")
  val parenMath = Regex("""\\\([\s\S]+?\\\)""")
  val bracketMath = Regex("""\\\[[\s\S]+?\\\]""")

  if (blockMath.containsMatchIn(text) || parenMath.containsMatchIn(text) || bracketMath.containsMatchIn(text)) {
    return true
  }

  // For inline $...$, avoid false positives with money ($5, $10, etc.)
  // Only match if the content between $ looks like math (contains operators, letters, or LaTeX commands)
  val inlineMath = Regex("""(?<!\$)\$(?!\$)([^$\n]+?)\$(?!\$)""")
  val matches = inlineMath.findAll(text)
  for (match in matches) {
    val content = match.groupValues[1].trim()
    // Skip if it looks like money (just a number with optional decimal)
    if (content.matches(Regex("""\d+(\.\d{1,2})?"""))) continue
    // Skip if it's just a currency amount like $5 or $10.50
    if (content.matches(Regex("""\d[\d,\.]*"""))) continue
    // Must contain at least one math-like character (operator, letter, backslash, ^, _, {, })
    if (content.contains(Regex("""[a-zA-Z\\^_{}\+\-\*/=<>]"""))) {
      return true
    }
  }
  return false
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
