# Análisis: llama.cpp con archivos GGUF

## Ventajas
- Soporte para más familias de modelos (Llama, Mistral, Phi, etc.)
- Archivos GGUF son más comunes en Hugging Face que .task/.litertlm
- Mayor comunidad y soporte
- Cuantización flexible (q4, q8, etc.)

## Desventajas
- Requiere JNI nativo (C++) en Android
- Mayor complejidad de integración (compilar librerías nativas para arm64-v8a, armeabi-v7a)
- Posible impacto en rendimiento en dispositivos sin NPU (como Honor X6b con Helio G85)
- Duplicación de motor (LiteRT-LM ya funciona con Gemma y Qwen)
- Mayor tamaño de APK
- Mantener dos motores aumenta la carga de mantenimiento

## Esfuerzo
- Alto: requiere crear wrappers JNI, compilar llama.cpp para Android, integrar con el sistema de descargas existente
- Tiempo estimado: varias semanas de desarrollo
- Riesgo alto de incompatibilidades y bugs

## Recomendación
NO implementar por ahora. LiteRT-LM ya soporta Gemma y Qwen, que son suficientes para un dispositivo de 6 GB de RAM. Revisar en el futuro si se necesita soporte para modelos no disponibles en formato .task.

## Modelos recomendados para 6 GB de RAM (Honor X6b)
- Gemma 3 1B Instruct (q4) - 529 MB, ~1 GB RAM
- Qwen 2.5 0.5B Instruct (q8) - 550 MB, ~800 MB RAM
- Qwen 2.5 1.5B Instruct (q8) - 1600 MB, ~2 GB RAM
- Gemma 2 2B Instruct (q4) - 1300 MB, ~1.8 GB RAM
- Gemma 3n E2B - 2048 MB, ~2.5 GB RAM (límite superior)

## Modelos en el catálogo local (models_catalog.json)
| Modelo | Familia | Tamaño | Cuantización | RAM estimada | Licencia | Gated | Recomendado 6GB |
|--------|---------|--------|--------------|--------------|----------|-------|-----------------|
| Gemma 3 1B Instruct | Gemma | 529 MB | q4 | 1024 MB | Gemma | Sí | Sí |
| Gemma 3n E2B | Gemma | 2048 MB | q4 | 2560 MB | Gemma | Sí | Sí |
| Gemma 3n E4B | Gemma | 4096 MB | q4 | 5120 MB | Gemma | Sí | No |
| Qwen 2.5 1.5B Instruct | Qwen | 1600 MB | q8 | 2048 MB | Apache 2.0 | No | Sí |
| Gemma 2 2B Instruct | Gemma | 1300 MB | q4 | 1800 MB | Gemma | Sí | Sí |
| Qwen 2.5 0.5B Instruct | Qwen | 550 MB | q8 | 800 MB | Apache 2.0 | No | Sí |
