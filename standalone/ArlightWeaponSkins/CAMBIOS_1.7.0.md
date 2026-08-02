# Cambios 1.7.0

- Intercepta también `ItemRenderer#render`, el último nivel usado por herramientas con renderer propio.
- Recalcula el `BakedModel` para el ItemStack visual de GeckoLib antes de dibujarlo.
- Usa prioridad alta para impedir que otro renderer restaure después el pico original.
- Activa el contexto del dueño en tercera persona mediante `ItemInHandLayerMixin`.
- Conserva sin cambios el ItemStack real, estadísticas, encantamientos, durabilidad y habilidades.
