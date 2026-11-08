# Miku Sora

App Android de IA local basada en Google AI Edge Gallery. Permite descargar y ejecutar modelos de IA directamente en tu teléfono, sin internet.

## Qué hace la app
- Ejecuta modelos de IA localmente en tu teléfono (Gemma, Qwen y otros compatibles con LiteRT-LM)
- Chat con modelos de lenguaje con renderizado de fórmulas matemáticas (LaTeX/KaTeX)
- Catálogo de modelos de Hugging Face con búsqueda y filtros (tipo, compatible con teléfono, tamaño)
- Gestor de descargas con progreso, pausa, reanudación y verificación de integridad
- Funciona sin internet una vez descargado el modelo
- Soporte para texto, visión y audio

## Cómo compilarla

### Requisitos
- Android Studio (última versión)
- JDK 17+ (se usa el JDK de Android Studio)
- Android SDK (API 34+)
- Teléfono Android con 6+ GB de RAM (recomendado)

### Pasos
1. Clona el repositorio: `git clone https://github.com/Mxiaku/gallery.git`
2. Abre el proyecto en Android Studio
3. Configura `Android/src/local.properties` con la ruta de tu SDK:
   ```
   sdk.dir=C:\Users\TU_USUARIO\AppData\Local\Android\Sdk
   ```
4. Configura Hugging Face OAuth (opcional, para modelos protegidos):
   - Edita `Android/src/app/src/main/java/com/miku/mikusora/common/ProjectConfig.kt`
   - Pon tu Client ID y redirect URI
   - Edita `Android/src/app/build.gradle.kts` con el redirect scheme `com.miku.mikusora`
5. Compila: Build > Make Project en Android Studio (o `./gradlew :app:assembleDebug`)
6. Genera APK: Build > Generate Signed Bundle / APK > APK
7. El APK estará en `Android/src/app/build/outputs/apk/debug/app-debug.apk`

## Estructura del proyecto
```
Android/src/
├── app/src/main/
│   ├── java/com/miku/mikusora/     # Código Kotlin
│   │   ├── common/                 # Utilidades comunes (WebViewPool, MathText, SystemPromptHelper)
│   │   ├── data/                   # Datos y repositorios
│   │   ├── ui/                     # Pantallas y componentes
│   │   │   ├── common/             # Componentes compartidos
│   │   │   ├── chat/               # Pantalla de chat
│   │   │   ├── modelmanager/       # Gestión de modelos
│   │   │   └── theme/              # Tema y colores
│   │   └── worker/                 # Descargas (DownloadWorker con verificación de integridad)
│   ├── res/                        # Recursos (strings, layouts, etc.)
│   │   ├── values/                 # Strings en inglés (base)
│   │   └── values-es/              # Strings en español (100% traducido)
│   └── assets/                     # Archivos estáticos (KaTeX, models_catalog.json)
├── build.gradle.kts                # Configuración de build
└── local.properties                # Ruta del SDK (no subir a Git)
```

## Cómo añadir modelos al catálogo
1. Los modelos se definen en `Android/src/app/src/main/assets/models_catalog.json`
2. Para añadir un modelo nuevo, agrega una entrada con: nombre, familia, tamaño, formato, cuantización, RAM estimada, tipo, licencia, gated, recommended_6gb, url
3. Los modelos marcados como "gated" requieren aceptar licencia en Hugging Face
4. Los modelos con `recommended_6gb: true` se marcan como recomendados para 6 GB de RAM

## Cómo probar en tu teléfono (Honor X6b)
1. Conecta el teléfono por USB con depuración USB activada
2. Instala el APK: `adb install app-debug.apk`
3. Abre la app y prueba:
   - Chat: "Resuelve x^2 + 3x + 2 = 0" (verifica fórmulas LaTeX)
   - Catálogo: Model Manager -> filtrar por tipo, compatible, tamaño
   - Descarga: Gemma 3 1B -> login HF -> descargar
   - Modo avión: desconecta internet -> verifica que el modelo funciona

## Pendientes
- Verificar que el login de Hugging Face funciona en el teléfono
- Probar KaTeX renderizando fórmulas en chats largos
- Verificar descarga completa con integridad (tamaño + hash)
- Carga real de models_catalog.json desde assets como respaldo offline
