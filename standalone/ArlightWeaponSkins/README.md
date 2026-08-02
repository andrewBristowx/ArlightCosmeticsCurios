# ArlightWeaponSkins 1.8.0

Mod cliente NeoForge 1.21.1 que reemplaza únicamente el render de una herramienta o arma. El `ItemStack` original no se edita: conserva estadísticas, durabilidad, encantamientos, nombre y habilidades del mod que lo creó.

## Sets iniciales

- `somita_weapon_set`: espada, pico, hacha, pala, azada y arco 3D.
- `pony_weapon_set`: espada, pico, hacha, pala, azada y arco 3D.

La selección llega desde las seis ranuras independientes de ArlightCosmetics 1.17.0 por el canal usado por ArlightChatClient 5.3.0.

## Rediseño 1.2.0

- 36 a 50 cubos por herramienta, frente a 17 a 21 en la versión inicial.
- Atlas UV RGBA de 128×128 para cada modelo.
- Somita: metal oscuro, rubí, alas de murciélago, corazones y brillo carmesí.
- Pony0n: cristal acuático, cara y branquias de ajolote, cuernos, corazones y tonos pastel.
- Transformaciones distintas para GUI, suelo, marcos, primera persona y ambas manos en tercera persona.
- Miniaturas centradas y reducidas para que no se corten en el ropero.

## Historial de corrección de render

- Intercepta las dos sobrecargas reales de `ItemRenderer#renderStatic` de Minecraft 1.21.1.
- Renderiza el `ItemStack` cosmético y cancela el modelo normal solo cuando existe una skin equipada.
- Resuelve directamente al dueño de la herramienta en primera y tercera persona.
- Usa la selección del jugador local en inventario, barra rápida, marcos y objetos tirados.
- Reintenta el puente de ChatClient si todavía no estaba cargado durante el primer render.

## Compatibilidad

La detección usa primero las etiquetas estándar de Minecraft y después nombres comunes como `katana`, `rapier`, `battleaxe`, `scythe` o `longbow`. El archivo `config/arlightweaponskins-compat.properties` permite añadir IDs exactos de otros mods o excluir excepciones.

## Instalación de prueba

1. Cliente: GeckoLib 4.9+, ArlightChatClient 5.3.0, ArlightCosmeticsCurios 1.17.0 y ArlightWeaponSkins 1.8.0.
2. Servidor: ArlightCosmetics 1.17.0. ArlightWeaponSkins 1.8.0 también debe estar en el servidor porque registra los objetos físicos de las ranuras Curios.
3. Concede uno de los permisos de los sets y reclámalo desde `/cosmeticos`.
4. Equipa la apariencia; el cambio debe verse en inventario, primera persona, tercera persona, marcos y objetos tirados.

Armas con un render completamente propio pueden requerir incluir su ID en compatibilidad o una integración específica.


## Corrección 1.8.0

El renderer GeckoLib se registra explícitamente mediante NeoForge y la entrega incluye los binarios exactos de ChatClient y CosmeticsCurios usados para compilar.
