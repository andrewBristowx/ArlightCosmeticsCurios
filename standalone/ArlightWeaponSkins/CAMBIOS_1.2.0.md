# Cambios de ArlightWeaponSkins 1.2.0

Esta versión corrige los dos problemas observados en el ropero: la posición excesivamente alta del modelo sostenido y la falta de detalle visible en las texturas.

## Modelos

- Se reconstruyeron las doce geometrías de Somita y Pony0n.
- Los picos ahora tienen cabezales curvos por segmentos, núcleo central, mango envuelto y remate temático.
- Espadas, hachas, palas, azadas y arcos recibieron siluetas propias y dejaron de compartir una base demasiado simple.
- Los adornos temáticos son geometría real: alas y gemas para Somita; rostro, branquias y cuernos de ajolote para Pony0n.

## Texturas

- Resolución aumentada de 64×64 a 128×128.
- Los cubos ya no toman un único píxel de color: cada cara utiliza un mosaico UV completo con sombreado y patrones.
- Se añadieron materiales diferenciados para metal, filo, gema, membrana, cristal, brillo, tela y cuerno.

## Posición y escala

- `gui`: miniatura centrada y reducida para las tarjetas del ropero.
- `thirdperson_righthand` y `thirdperson_lefthand`: herramienta alineada con la mano y menos grande.
- `firstperson_righthand` y `firstperson_lefthand`: escala propia para evitar que cubra la pantalla.
- `ground` y `fixed`: presentación específica para objetos tirados y marcos.

No se cambió la lógica de selección, la clasificación de herramientas ni las seis secciones independientes de Cosmetics 1.16.0.
