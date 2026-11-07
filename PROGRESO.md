# PROGRESO - App Miku Sora

## Estado actual: Fase 0 completada

### ¿Qué se hizo?
1. Se revisó el entorno de desarrollo:
   - Git 2.54.0 instalado ✅
   - Java JDK 25 instalado (JAVA_HOME apuntaba a ruta inválida, se usó el JDK de Android Studio: `C:\Program Files\Android\Android Studio\jbr`) ✅
   - Android Studio instalado ✅
   - Android SDK + adb instalados ✅
   - `gh` (GitHub CLI) NO instalado - se hará el fork por la web
2. Se clonó el fork `Mxiaku/gallery` en `C:\Users\cliente\Desktop\miku_local_ia`
3. Se exploró el repositorio:
   - **Módulo**: un solo módulo `app` en `Android/src/app/`
   - **Motor de IA**: LiteRT-LM (`litertlm-android 0.18.1`), modelos en formato `.task`
   - **Pantallas**: `ui/home`, `ui/modelmanager`, `ui/llmchat`, `ui/llmsingleturn`, `ui/benchmark`, `ui/notifications`, etc.
   - **Modelos iniciales** (`model_allowlist.json`): Gemma-3n-E2B, Gemma-3n-E4B, Gemma3-1B-IT q4, Qwen2.5-1.5B-Instruct q8 (todos de `litert-community` en Hugging Face)
   - **Textos**: todos en `res/values/strings.xml` (solo inglés, no existe `values-es`)
   - **Paquete actual**: `com.google.ai.edge.gallery` / `applicationId = "com.google.aiedge.gallery"`
   - **App name actual**: "Edge Gallery" / "Google AI Edge Gallery"
4. Se configuró `local.properties` con `sdk.dir=C:\Users\cliente\AppData\Local\Android\Sdk`
5. Se agregó `android.suppressUnsupportedCompileSdk=37.0` a `gradle.properties`
6. **La app compila exitosamente** (`BUILD SUCCESSFUL`)
7. **APK generado**: `Android/src/app/build/outputs/apk/debug/app-debug.apk` (353 MB)

### Configuración pendiente (Hugging Face)
- `ProjectConfig.kt` tiene placeholders para `clientId` y `redirectUri` de Hugging Face OAuth. Se necesita crear una OAuth app en Hugging Face y configurar estos valores sin exponerlos en el repo.
- `build.gradle.kts` línea 44: `manifestPlaceholders["appAuthRedirectScheme"]` también necesita el esquema de redirect de Hugging Face.

### ¿Qué falta?
- Fase 1: Estabilidad base (modelo pequeño descarga/carga/chatea sin cierres, errores en español)
- Fase 2: Traducción completa al español (`values-es/strings.xml`)
- Fase 3: Identidad propia (nombre Miku Sora, paquete, ícono, colores)
- Fase 4-8: Catálogo HF, gestor de descargas, más modelos, rendimiento, entrega

### Cómo probar
1. Conectar el Honor X6b por cable USB con depuración USB activada
2. Ejecutar: `adb install Android/src/app/build/outputs/apk/debug/app-debug.apk`
3. O instalar el APK manualmente desde el archivo

### Próximo paso
Pedir al usuario que conecte su teléfono por cable para instalar y probar la app original.
