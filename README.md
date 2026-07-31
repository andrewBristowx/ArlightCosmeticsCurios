# ArlightCosmeticsCurios 1.10.0 — Pets & Pajamas Overhaul

Mod NeoForge 1.21.1 que registra los objetos Curios y renderiza los cosméticos físicos del servidor Pony0n.

## Mascotas

- Mini T-Rex y las doce mascotas MobChibi conservan sus identificadores y modelos.
- Estados visuales `idle`, caminar, correr, nadar y volar.
- Movimiento suavizado por UUID del propietario.
- Recolocación al teletransportarse, cambiar de dimensión o cambiar de mascota.
- Escala y altura ajustadas por especie.
- Animación completa cerca, simplificada a media distancia y ocultación lejos.
- Sin entidades, colisiones ni carga adicional en el servidor.

## Pijamas y ropa

- Capuchas de dinosaurio y ajolote menos voluminosas.
- Torso, mangas, cintura, piernas y pantuflas con menor separación del jugador.
- Puños y parche frontal para dar mejor lectura al conjunto.
- Ajuste automático de brazos slim y clásico.
- La pieza cosmética coincidente se oculta al equipar armadura real.
- Conjunto adicional `cloud_bunny_pajama`: capucha conejita, cardigan lunar, falda nube y pantuflas.

## Texturas y rendimiento

Las texturas existentes fueron pulidas sin cambiar sus rutas ni UV: contraste pixelado suave, costuras más limpias y máscaras emisivas menos invasivas. Los emisivos vacíos se conservan cuando la pieza no debe brillar.

La configuración local se crea en:

`config/arlightcosmeticscurios-client.properties`

Opciones principales:

```properties
render-distance=40
full-animation-distance=18
pet-follow-smoothing=0.16
pet-side=auto
hide-cosmetics-under-armor=true
particles=full
```

`particles` acepta `full`, `reduced` u `off`; `pet-side` acepta `auto`, `left` o `right`.

## Compilación

Usa Java 21. Se incluye el wrapper completo:

- Windows: `gradlew.bat build`
- Linux/macOS: `./gradlew build`

El JAR esperado es `build/libs/ArlightCosmeticsCurios-1.10.0.jar`.
