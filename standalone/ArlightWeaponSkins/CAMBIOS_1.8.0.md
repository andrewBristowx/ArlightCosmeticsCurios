# ArlightWeaponSkins 1.8.0 — renderer explícito y entrega compatible

- Registra de forma explícita un `IClientItemExtensions`/BEWLR para los doce objetos GeckoLib.
- Mantiene `GeoItem#createGeoRenderer` como respaldo, pero evita depender únicamente del descubrimiento dinámico.
- Lee el identificador del objeto Curios desde `BuiltInRegistries.ITEM`, no desde `Item#toString()`.
- Registra diagnósticos de renderer, selección, objeto de reemplazo y presencia de modelo, geometría, textura y animación.
- Conserva los hooks de primera persona, tercera persona, inventario y render de bajo nivel.
- La entrega incluye exactamente ChatClient 5.3.0 y CosmeticsCurios 1.17.0 usados durante la compilación.
