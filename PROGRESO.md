# PROGRESO - App Miku Sora

Dispositivo objetivo: Honor X6b (Helio G85, 6 GB RAM, sin NPU, Android 16).
Paquete: com.miku.mikusora | Nombre: Miku Sora

## Compilado y probado por el usuario (en Android Studio + Honor X6b)
- Fase 0: Entorno configurado, app compila, APK generado
- Fase 1: Base estable (app funciona en Honor X6b)
- Fase 2: Traducción COMPLETA (1370/1370 strings en español, XML válido)
- Fase 3: Identidad propia (paquete com.miku.mikusora, nombre Miku Sora)
- Hugging Face OAuth configurado (Client ID en ProjectConfig.kt, redirect URI en build.gradle.kts)

## Sin verificar (implementado, no se pudo compilar en este entorno)
- Fase 3B: MathText.kt + WebViewPool.kt (pool máximo 4 WebViews), MathAwareText.kt conectado a chat
  - Instrucción LaTeX en SystemPromptHelper
  - Detección de dinero ($5, $10) evitada
  - Modo claro/oscuro soportado
- Fase 4: Filtros por tipo (texto/visión/audio), compatible con teléfono, tamaño (<1GB, 1-3GB, >3GB)
  - Tarjetas muestran tamaño desde model.siblings
  - models_catalog.json con 6 modelos (Gemma, Qwen)
  - JSON local como respaldo offline (estructura lista, carga por Context pendiente)
- Fase 5: Verificación de integridad de descarga (tamaño + SHA-256 hash)
  - ERROR_TYPE_INTEGRITY_FAILED añadido
  - Mensajes de error en español
- Fase 7: Métricas de rendimiento (LiveMetricsBar), tema claro/oscuro, ajustes contexto/temperatura
- Bloque 5: Textos hardcodeados movidos a strings.xml (SkillTesterBottomSheet, etc.)

## Pendiente (no implementado)
- Carga real de models_catalog.json desde assets (requiere Context, no disponible en ViewModel)
- "Compatible con mi teléfono" usando RAM real del dispositivo (isDeviceCompatible() ya existe)
- Verificación sin internet (modo avión) con modelo ya descargado
- Liberación explícita de modelo al cambiar/cerrar chat (cleanupModel ya existe en ModelManagerViewModel)
- APK no generado (Gradle se cuelga en este entorno)
- Login Hugging Face no verificado en teléfono

## Pendiente paralelo - Login Hugging Face
- El login se activa al descargar un modelo gated (como Gemma 3 1B)
- Client ID configurado: 3898acc4-d33d-46b8-9676-f40984ddf07b
- Redirect URI: com.miku.mikusora://oauth
- Intent-filter en AndroidManifest correcto
- Para probar: Model Manager -> descargar Gemma 3 1B -> debería pedir login

## Pasos para compilar y probar en Android Studio
1. Abrir proyecto en Android Studio
2. Build > Make Project (verificar que compila)
3. Build > Generate Signed Bundle / APK > APK (generar APK de prueba)
4. Conectar Honor X6b por USB con depuración USB activada
5. Instalar APK: adb install app-debug.apk (o instalar manualmente)
6. Probar chat: "Resuelve x^2 + 3x + 2 = 0" -> verificar fórmulas LaTeX
7. Probar catálogo: Model Manager -> filtrar por tipo, compatible, tamaño
8. Probar descarga: Gemma 3 1B -> login HF -> descargar -> verificar integridad
9. Probar modo avión: desconectar internet -> verificar modelo funciona
10. Verificar que no queden textos en inglés en las pantallas

## Archivos creados/modificados
- AUDITORIA.md: tabla comparativa requisitos vs estado real
- PENDIENTES_TRADUCCION.md: lista de 581 strings traducidos (0 pendientes)
- assets/katex/: KaTeX para renderizado offline (katex.min.js, katex.min.css, index.html)
- MathText.kt, WebViewPool.kt: renderizado matemático con pool de 4 WebViews
- MathAwareText.kt: detección de fórmulas con conexión al chat
- MessageBodyText.kt: conectado a MathAwareText
- models_catalog.json: catálogo curado offline con 6 modelos
- docs/llamacpp_analysis.md: análisis de llama.cpp/GGUF (no recomendado)
- README.md: documentación en español
- SystemPromptHelper.kt: instrucción LaTeX añadida al prompt de sistema
- DownloadWorker.kt: verificación de integridad (tamaño + SHA-256)
- Consts.kt: ERROR_TYPE_INTEGRITY_FAILED, KEY_MODEL_DOWNLOAD_EXPECTED_HASH
- HfExploreScreen.kt: filtros por tipo/compatibles/tamaño, tarjetas con tamaño
- HfExploreViewModel.kt: onTypeFilterChanged, onCompatibleOnlyChanged, onSizeFilterChanged
- HfModelDetailsSheet.kt: ModelStatsRow con tamaño
- ProjectConfig.kt: Client ID y redirect URI de Hugging Face
