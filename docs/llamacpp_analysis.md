# Análisis: llama.cpp con archivos GGUF

## Ventajas
- Soporte para más modelos (Llama, Mistral, Phi, etc.)
- Archivos GGUF son más comunes en Hugging Face
- Mayor comunidad y soporte

## Desventajas
- Requiere JNI nativo (C++) en Android
- Mayor complejidad de integración
- Posible impacto en rendimiento en dispositivos sin NPU
- Duplicación de motor (LiteRT-LM ya funciona)
- Mayor tamaño de APK

## Esfuerzo
- Alto: requiere crear wrappers JNI, compilar librerías nativas para Android (arm64-v8a, armeabi-v7a)
- Tiempo estimado: semanas de desarrollo
- Riesgo alto de incompatibilidades

## Recomendación
NO implementar por ahora. LiteRT-LM ya soporta Gemma y Qwen, que son suficientes para un dispositivo de 6 GB de RAM. Revisar en el futuro si se necesita soporte para modelos no disponibles en formato .task.

## Modelos recomendados para 6 GB de RAM
- Gemma 3 1B Instruct (q4) - 529 MB, ~1 GB RAM
- Qwen 2.5 1.5B Instruct (q8) - 1600 MB, ~2 GB RAM
- Gemma 3n E2B - 2048 MB, ~2.5 GB RAM (límite superior)
