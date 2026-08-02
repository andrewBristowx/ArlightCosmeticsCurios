# Arlight Weapon Skins 1.3.0

- Sustituye el `ModifyVariable` anterior por dos inyecciones cancelables en `ItemRenderer`.
- Usa directamente el UUID de la entidad renderizada para primera y tercera persona.
- Usa el jugador local en GUI, barra rápida, marcos y renders sin entidad propietaria.
- Reintenta enlazar con `ArlightChatClient` si la clase todavía no estaba disponible en el primer render.
- Conserva los doce modelos refinados, las seis categorías y las selecciones existentes.
