# Auditoría - App Miku Sora

## Fase 3B - Renderizado matemático

| Requisito | Estado | Archivos |
|-----------|--------|----------|
| Renderizar fórmulas en chat | Parcial | MathText.kt, MathAwareText.kt creados pero NO conectados al chat |
| Normalizar delimitadores | Parcial | MathAwareText.kt detecta $...$, $$...$$, \(...\), \[...\] |
| Evitar falsos positivos con dinero | Parcial | Regex en MathAwareText.kt (no probado) |
| Streaming sin parpadeos | Falta | No implementado |
| Texto original si fórmula falla | Parcial | KaTeX throwOnError: false en index.html |
| KaTeX sin internet (assets) | Implementado | assets/katex/ (JS, CSS, HTML) |
| Modo claro/oscuro | Falta | MathText.kt tiene parámetro darkMode pero no se usa |
| Copiar texto de fórmula | Falta | No implementado |
| Instrucción LaTeX en prompt | Implementado | SystemPromptHelper.kt |

## Fase 4 - Catálogo tipo Hugging Face

| Requisito | Estado | Archivos |
|-----------|--------|----------|
| Pantalla de catálogo | Existe | HfExploreScreen.kt, HfExploreViewModel.kt |
| Búsqueda por nombre | Existe | HfExploreScreen.kt |
| Tarjetas: nombre, tamaño, formato, cuantización, RAM, licencia | Parcial | HfModelDetailsSheet.kt (parcial) |
| Filtro por tamaño | Falta | No implementado |
| Filtro por tipo (texto, visión, audio) | Falta | No implementado |
| Filtro "compatible con mi teléfono" | Falta | No implementado |
| API pública de Hugging Face | Existe | HuggingFaceApiClient.kt |
| JSON local de respaldo | Falta | No existe archivo JSON local |
| Pantalla de detalle | Existe | HfModelDetailsSheet.kt |
| Marca modelos con licencia/login | Parcial | HfModelDetailsSheet.kt (parcial) |

## Fase 5 - Gestor de descargas

| Requisito | Estado | Archivos |
|-----------|--------|----------|
| Barra de progreso | Existe | DownloadWorker.kt, ModelDownloader.kt |
| Velocidad y tiempo restante | Parcial | No verificado |
| Pausar, reanudar, cancelar | Existe | DownloadWorker.kt |
| Sobrevivir a cerrar app | Existe | WorkManager |
| Verificar espacio libre | Falta | No implementado |
| Integridad del archivo (tamaño/hash) | Falta | No implementado |
| Pantalla "Mis modelos" | Parcial | ModelManager.kt (parcial) |
| Importar modelo local | Existe | ModelImportDialog.kt |
| Mensajes de error en español | Parcial | strings.xml (parcial) |

## Fase 6 - Más modelos

| Requisito | Estado | Archivos |
|-----------|--------|----------|
| Soporte Gemma | Existe | model_allowlist.json, ModelAllowlist.kt |
| Soporte Qwen | Existe | model_allowlist.json (Qwen2.5-1.5B) |
| Más familias | Falta | Solo Gemma y Qwen |
| Marca modelos para 6 GB RAM | Falta | No implementado |
| Análisis llama.cpp/GGUF | Falta | No documentado |

## Fase 7 - Rendimiento y pulido

| Requisito | Estado | Archivos |
|-----------|--------|----------|
| Tiempo de carga, TTFT, tokens/seg | Existe | LiveMetricsBar.kt, MessageLatency.kt |
| Liberar memoria | Parcial | No verificado |
| Ajustes: contexto, temperatura | Existe | SettingsDialog.kt, Config.kt |
| Tema claro/oscuro | Existe | Theme.kt, ThemeSettings.kt |
| Funcionar sin internet | Parcial | No verificado |
| Textos en inglés restantes | Parcial | values-es/strings.xml (816 sin traducir) |

## Fase 8 - Entrega

| Requisito | Estado | Archivos |
|-----------|--------|----------|
| APK de prueba | Falta | No generado (Gradle se cuelga) |
| README.md en español | Implementado | README.md |
| Repositorio limpio | Parcial | .gitignore actualizado |
| PROGRESO.md actualizado | Parcial | Necesita actualización honesta |

## Pendiente paralelo - Login Hugging Face

| Requisito | Estado | Archivos |
|-----------|--------|----------|
| Client ID configurado | Implementado | ProjectConfig.kt |
| Redirect URI configurado | Implementado | ProjectConfig.kt, build.gradle.kts |
| Botón de login visible | Falta | Investigar causa |
