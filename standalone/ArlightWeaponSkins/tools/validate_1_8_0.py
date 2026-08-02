#!/usr/bin/env python3
from pathlib import Path
import json
root=Path(__file__).resolve().parents[1]
def need(c,m):
    if not c: raise SystemExit(m)
def read(p): return (root/p).read_text(encoding='utf-8')
need("version = '1.8.0'" in read('build.gradle'),'gradle version')
need('version="${version}"' in read('src/main/resources/META-INF/neoforge.mods.toml'),'toml expansion')
need('WeaponSkinClientExtensions' in read('src/main/java/com/arlight/weaponskins/client/WeaponSkinClientExtensions.java'),'client extensions')
need('RegisterClientExtensionsEvent' in read('src/main/java/com/arlight/weaponskins/client/WeaponSkinClientExtensions.java'),'client extension event')
need('BuiltInRegistries.ITEM.getKey' in read('src/main/java/com/arlight/weaponskins/WeaponSkinSelection.java'),'registry id selection')
need('RECURSO AUSENTE' in read('src/main/java/com/arlight/weaponskins/WeaponSkinDiagnostics.java'),'asset diagnostics')
for theme in ('somita','pony'):
  for tool in ('sword','pickaxe','axe','shovel','hoe','bow'):
    visual=f'{theme}_{tool}'
    alias=f'{theme}_weapon_{tool}'
    for p in [f'src/main/resources/assets/arlightweaponskins/geo/{visual}.geo.json',
              f'src/main/resources/assets/arlightweaponskins/animations/{visual}.animation.json',
              f'src/main/resources/assets/arlightweaponskins/textures/item/{visual}.png',
              f'src/main/resources/assets/arlightcosmeticscurios/models/item/{alias}.json']:
      need((root/p).is_file(),f'missing {p}')
    model=json.loads(read(f'src/main/resources/assets/arlightcosmeticscurios/models/item/{alias}.json'))
    need(model.get('parent')=='builtin/entity',f'alias model not builtin/entity: {alias}')
print('validate_1_8_0: OK')
