# Miku Sora

App Android de IA local basada en Google AI Edge Gallery. Permite descargar y ejecutar modelos de IA directamente en tu teléfono, sin internet.

## Qué hace la app
- Ejecuta modelos de IA localmente en tu teléfono (Gemma, Qwen y otros compatibles con LiteRT-LM)
- Chat con modelos de lenguaje con renderizado de fórmulas matemáticas (LaTeX/KaTeX)
- Catálogo de modelos de Hugging Face con búsqueda y filtros
- Gestor de descargas con progreso, pausa y reanudación
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
   sdk.dir=C:\\Users\\TU_USUARIO\\AppData\\Local\\Android\\Sdk
   ```
4. Configura Hugging Face OAuth (opcional, para modelos protegidos):
   - Edita `Android/src/app/src/main/java/com/miku/mikusora/common/ProjectConfig.kt`
   - Pon tu Client ID y redirect URI
   - Edita `Android/src/app/build.gradle.kts` línea ~44 con el redirect scheme
5. Compila: `./gradlew :app:assembleDebug`
6. El APK estará en `Android/src/app/build/outputs/apk/debug/app-debug.apk`

## Estructura del proyecto
```
Android/src/
├── app/src/main/
│   ├── java/com/miku/mikusora/     # Código Kotlin
│   │   ├── common/                 # Utilidades comunes
│   │   ├── data/                   # Datos y repositorios
│   │   ├── ui/                     # Pantallas y componentes
│   │   │   ├── common/             # Componentes compartidos
│   │   │   ├── chat/               # Pantalla de chat
│   │   │   ├── modelmanager/       # Gestión de modelos
│   │   │   └── theme/              # Tema y colores
│   │   └── runtime/                # Motor de IA (LiteRT-LM)
│   ├── res/                        # Recursos (strings, layouts, etc.)
│   │   ├── values/                 # Strings en inglés (base)
│   │   └── values-es/              # Strings en español
│   └── assets/                     # Archivos estáticos (KaTeX, etc.)
├── build.gradle.kts                # Configuración de build
└── local.properties                # Ruta del SDK (no subir a Git)
```

## Cómo añadir modelos al catálogo
1. Los modelos se definen en los archivos de configuración del proyecto
2. Para añadir un modelo nuevo, busca los archivos de allowlist en `data/ModelAllowlist.kt`
3. Añade la entrada con: nombre, URL de Hugging Face, tamaño, cuantización, RAM estimada
4. Los modelos marcados como "gated" requieren aceptar licencia en Hugging Face

## Pendientes
- Verificar que el login de Hugging Face funciona (el Client ID está configurado)
- Probar la app en el teléfono (Honor X6b)
- Verificar que KaTeX renderiza fórmulas correctamente
- Añadir más familias de modelos (Qwen, etc.)
- Optimizar rendimiento en dispositivos con 6 GB de RAM
