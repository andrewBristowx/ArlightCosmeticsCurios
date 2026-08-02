# Cambios 1.6.0

- Anade un hook final de primera persona sobre la llamada real a `ItemRenderer#renderStatic`.
- Impide que otros renderizadores del modpack restauren el pico original despues de la primera sustitucion.
- Mantiene el hook anterior como respaldo y conserva la sustitucion general para tercera persona e inventarios.
- No modifica el `ItemStack` real, sus estadisticas, durabilidad, encantamientos ni habilidades.
