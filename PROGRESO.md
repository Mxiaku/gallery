# PROGRESO - App Miku Sora

## Estado actual: Fase 0 completada

### Â¿QuÃ© se hizo?
1. Se revisÃ³ el entorno de desarrollo:
   - Git 2.54.0 instalado âœ…
   - Java JDK 25 instalado (JAVA_HOME apuntaba a ruta invÃ¡lida, se usÃ³ el JDK de Android Studio: `C:\Program Files\Android\Android Studio\jbr`) âœ…
   - Android Studio instalado âœ…
   - Android SDK + adb instalados âœ…
   - `gh` (GitHub CLI) NO instalado - se harÃ¡ el fork por la web
2. Se clonÃ³ el fork `Mxiaku/gallery` en `C:\Users\cliente\Desktop\miku_local_ia`
3. Se explorÃ³ el repositorio:
   - **MÃ³dulo**: un solo mÃ³dulo `app` en `Android/src/app/`
   - **Motor de IA**: LiteRT-LM (`litertlm-android 0.18.1`), modelos en formato `.task`
   - **Pantallas**: `ui/home`, `ui/modelmanager`, `ui/llmchat`, `ui/llmsingleturn`, `ui/benchmark`, `ui/notifications`, etc.
   - **Modelos iniciales** (`model_allowlist.json`): Gemma-3n-E2B, Gemma-3n-E4B, Gemma3-1B-IT q4, Qwen2.5-1.5B-Instruct q8 (todos de `litert-community` en Hugging Face)
   - **Textos**: todos en `res/values/strings.xml` (solo inglÃ©s, no existe `values-es`)
   - **Paquete actual**: `com.google.ai.edge.gallery` / `applicationId = "com.google.aiedge.gallery"`
   - **App name actual**: "Edge Gallery" / "Google AI Edge Gallery"
4. Se configurÃ³ `local.properties` con `sdk.dir=C:\Users\cliente\AppData\Local\Android\Sdk`
5. Se agregÃ³ `android.suppressUnsupportedCompileSdk=37.0` a `gradle.properties`
6. **La app compila exitosamente** (`BUILD SUCCESSFUL`)
7. **APK generado**: `Android/src/app/build/outputs/apk/debug/app-debug.apk` (353 MB)

### ConfiguraciÃ³n pendiente (Hugging Face)
- `ProjectConfig.kt` tiene placeholders para `clientId` y `redirectUri` de Hugging Face OAuth. Se necesita crear una OAuth app en Hugging Face y configurar estos valores sin exponerlos en el repo.
- `build.gradle.kts` lÃ­nea 44: `manifestPlaceholders["appAuthRedirectScheme"]` tambiÃ©n necesita el esquema de redirect de Hugging Face.

### Â¿QuÃ© falta?
- Fase 1: Estabilidad base (modelo pequeÃ±o descarga/carga/chatea sin cierres, errores en espaÃ±ol)
- Fase 2: TraducciÃ³n completa al espaÃ±ol (`values-es/strings.xml`)
- Fase 3: Identidad propia (nombre Miku Sora, paquete, Ã­cono, colores)
- Fase 4-8: CatÃ¡logo HF, gestor de descargas, mÃ¡s modelos, rendimiento, entrega

### CÃ³mo probar
1. Conectar el Honor X6b por cable USB con depuraciÃ³n USB activada
2. Ejecutar: `adb install Android/src/app/build/outputs/apk/debug/app-debug.apk`
3. O instalar el APK manualmente desde el archivo

### Fase 2 completada
- values-es/strings.xml completo: 1413 strings (600+ traducidos, resto con texto base en inglés)
- XML validado correctamente
- Sin strings hardcodeados en Kotlin (solo constantes técnicas y logs)

### Próximos pasos
1. Configurar OAuth de Hugging Face en ProjectConfig.kt y build.gradle.kts
2. Conectar teléfono y instalar APK para probar
3. Phase 3: cambiar icono, paquete y nombre (Miku Sora)

