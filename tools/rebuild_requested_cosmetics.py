from pathlib import Path
from PIL import Image

ROOT = Path(__file__).resolve().parents[1]
TEX = ROOT / "src/main/resources/assets/arlightcosmeticscurios/textures/cosmetics"
SIZE = 128


def shade(color, delta):
    r,g,b,a = color
    return tuple(max(0,min(255,c+delta)) for c in (r,g,b)) + (a,)


def canvas(base):
    im = Image.new("RGBA", (SIZE,SIZE), base)
    px = im.load()
    for y in range(SIZE):
        for x in range(SIZE):
            d = 4 if ((x//3 + y//3) & 1)==0 else -3
            px[x,y] = shade(base,d)
    return im


def rect(im, xy, color, checker=True):
    x0,y0,x1,y1 = xy
    px=im.load()
    for y in range(max(0,y0), min(SIZE,y1)):
        for x in range(max(0,x0), min(SIZE,x1)):
            d = (3 if ((x//3+y//3)&1)==0 else -2) if checker else 0
            px[x,y]=shade(color,d)


def save(name, im):
    im.save(TEX / f"{name}.png")


def clear_emissive(name):
    p=TEX/f"{name}_emissive.png"
    if p.exists(): Image.new("RGBA",(SIZE,SIZE),(0,0,0,0)).save(p)


def emissive(name, regions):
    p=TEX/f"{name}_emissive.png"
    if not p.exists(): return
    im=Image.new("RGBA",(SIZE,SIZE),(0,0,0,0))
    for xy,color in regions: rect(im,xy,color,False)
    im.save(p)

# T-Rex: paleta verde coherente y ojos separados del UV de brazos/placas.
im=canvas((67,125,58,255))
rect(im,(0,0,32,32),(73,139,64,255))
rect(im,(0,32,32,64),(205,220,142,255))
rect(im,(32,0,64,32),(65,124,55,255))
rect(im,(64,0,96,32),(82,148,70,255))
rect(im,(0,64,32,96),(117,173,84,255))
rect(im,(32,64,64,96),(64,119,55,255))
rect(im,(64,64,96,96),(232,225,169,255))
rect(im,(96,0,128,32),(108,169,76,255))
rect(im,(32,32,64,64),(61,116,52,255))
rect(im,(64,32,96,64),(48,94,43,255))
rect(im,(0,96,32,128),(68,126,56,255))
rect(im,(32,96,64,128),(62,116,51,255))
rect(im,(64,96,96,128),(53,103,47,255))
rect(im,(112,64,128,80),(20,24,18,255),False)
save("swansito_mini_trex",im)
emissive("swansito_mini_trex",[((112,64,128,80),(80,255,130,150))])

# Creeper clásico: cara negra, sin cejas pintadas.
im=canvas((92,184,73,255))
rect(im,(0,0,32,32),(72,159,61,255))
rect(im,(32,0,64,32),(104,194,82,255))
rect(im,(64,0,96,32),(70,151,58,255))
rect(im,(0,32,32,64),(87,175,68,255))
rect(im,(96,0,128,32),(16,28,15,255),False)
rect(im,(96,32,128,64),(16,28,15,255),False)
save("mobchibi_creeper",im)
clear_emissive("mobchibi_creeper")

# Enderman uniforme, igual al torso; solo ojos violetas.
end=(33,25,56,255)
im=canvas(end)
for xy in [(0,0,32,32),(0,32,32,64),(32,0,64,32),(64,0,96,32),(96,32,128,64)]:
    rect(im,xy,end)
rect(im,(96,0,128,32),(194,74,255,255),False)
save("mobchibi_enderman",im)
emissive("mobchibi_enderman",[((96,0,128,32),(210,90,255,220))])

# Ajolote: cara en rosita claro; branquias celestes.
im=canvas((238,145,179,255))
rect(im,(0,0,32,32),(236,145,180,255))
rect(im,(32,0,64,32),(255,190,210,255))
rect(im,(64,0,96,32),(116,217,225,255))
rect(im,(64,32,96,64),(88,190,207,255))
rect(im,(0,32,32,64),(244,174,198,255))
rect(im,(32,32,64,64),(217,119,162,255))
rect(im,(96,0,128,32),(34,41,50,255),False)
rect(im,(96,32,128,64),(161,75,120,255),False)
save("mobchibi_axolotl",im)
clear_emissive("mobchibi_axolotl")

# Slime: un solo verde claro, cara oscura.
sl=(129,214,116,255)
im=canvas(sl)
rect(im,(0,0,96,96),sl)
rect(im,(96,0,128,32),(45,91,46,255),False)
rect(im,(64,32,96,64),(45,91,46,255),False)
save("mobchibi_slime",im)
clear_emissive("mobchibi_slime")

# Warden: cara completa con ojos y boca, cuernos/núcleo cian.
im=canvas((24,99,105,255))
rect(im,(0,0,32,32),(25,104,111,255))
rect(im,(32,0,64,32),(31,116,122,255))
rect(im,(0,32,32,64),(20,79,86,255))
rect(im,(32,32,64,64),(18,68,76,255))
rect(im,(64,0,96,32),(91,214,209,255))
rect(im,(96,0,128,32),(151,247,229,255))
rect(im,(96,32,128,64),(42,181,171,255))
rect(im,(64,64,96,96),(91,222,205,255))
rect(im,(112,64,128,80),(174,255,241,255),False)
rect(im,(112,80,128,96),(5,35,42,255),False)
save("mobchibi_warden",im)
emissive("mobchibi_warden",[
    ((64,0,96,32),(80,235,226,190)),((96,0,128,32),(170,255,245,220)),
    ((96,32,128,64),(53,215,198,200)),((64,64,96,96),(90,238,216,210)),
    ((112,64,128,80),(190,255,248,240))])

# Zorrito: hocico, pecho/cola y punta blancos; patas oscuras.
im=canvas((205,91,31,255))
rect(im,(0,0,32,32),(211,99,34,255))
rect(im,(32,0,64,32),(226,116,45,255))
rect(im,(64,0,96,32),(246,225,188,255))
rect(im,(0,32,32,64),(222,108,37,255))
rect(im,(32,32,64,64),(248,232,201,255))
rect(im,(64,32,96,64),(71,41,31,255))
rect(im,(96,0,128,32),(66,37,29,255))
rect(im,(112,64,128,80),(27,23,24,255),False)
save("mobchibi_fox",im)
clear_emissive("mobchibi_fox")

# Ranita: atlas totalmente opaco; ojos crema y pupilas negras.
im=canvas((101,175,70,255))
rect(im,(0,0,32,32),(95,168,65,255))
rect(im,(0,32,32,64),(149,205,91,255))
rect(im,(32,0,48,32),(237,239,199,255))
rect(im,(48,0,64,32),(237,239,199,255))
rect(im,(64,0,96,32),(78,145,58,255))
rect(im,(96,0,112,32),(112,187,77,255))
rect(im,(112,64,128,80),(23,29,18,255),False)
save("mobchibi_frog",im)
clear_emissive("mobchibi_frog")

# Allay: azul Minecraft, alas claras, brazos azules y cara legible.
im=canvas((80,177,217,255))
rect(im,(0,0,32,32),(69,166,210,255))
rect(im,(0,32,32,64),(102,204,231,255))
rect(im,(32,0,64,32),(126,221,240,255))
rect(im,(64,0,96,32),(179,239,246,220))
rect(im,(96,0,128,32),(75,173,217,255))
rect(im,(96,32,128,64),(108,209,230,255))
rect(im,(112,64,128,80),(20,61,101,255),False)
rect(im,(112,80,128,96),(31,91,129,255),False)
save("mobchibi_allay",im)
emissive("mobchibi_allay",[((64,0,96,32),(184,246,255,150)),((112,64,128,80),(93,221,255,180))])

# Zombie fiel a Minecraft: piel verde, camiseta cian y pantalón azul oscuro.
im=canvas((53,156,164,255))
rect(im,(0,0,32,32),(56,160,170,255))
rect(im,(0,32,32,64),(54,57,130,255))
rect(im,(32,0,64,32),(92,151,75,255))
rect(im,(64,0,96,32),(87,145,71,255))
rect(im,(96,0,128,32),(42,32,62,255),False)
rect(im,(96,32,128,64),(45,86,49,255))
save("mobchibi_zombie",im)
clear_emissive("mobchibi_zombie")

# Esqueleto: todos los UV pintados, cuencas y boca oscuras.
im=canvas((212,211,198,255))
for xy,c in [((0,0,32,32),(202,201,189,255)),((0,32,32,64),(193,192,181,255)),
             ((16,32,48,64),(198,197,185,255)),((32,0,64,32),(219,218,204,255)),
             ((32,32,64,64),(184,184,175,255)),((48,32,80,64),(190,189,179,255)),
             ((64,0,96,32),(223,222,209,255))]: rect(im,xy,c)
rect(im,(96,0,128,32),(45,45,43,255),False)
rect(im,(112,80,128,96),(55,54,50,255),False)
save("mobchibi_skeleton",im)
clear_emissive("mobchibi_skeleton")

# Pijamas: atlas completo y opaco para evitar que la skin atraviese zonas sin UV.
def dino_pajama(name):
    im=canvas((80,151,70,255))
    rect(im,(0,0,64,24),(82,154,72,255))
    rect(im,(0,24,64,64),(73,137,64,255))
    rect(im,(64,0,96,24),(205,220,148,255))
    rect(im,(64,24,96,64),(60,116,54,255))
    rect(im,(96,0,128,32),(126,185,88,255))
    rect(im,(96,24,128,64),(55,105,48,255))
    rect(im,(112,64,128,80),(24,29,20,255),False)
    return im

for name in ["dino_pajama_hat","dino_pajama_chest","dino_pajama_legs","dino_pajama_feet"]:
    save(name,dino_pajama(name)); clear_emissive(name)

def axo_pajama(name):
    im=canvas((231,139,179,255))
    rect(im,(0,0,64,24),(237,150,188,255))
    rect(im,(0,24,64,64),(224,129,171,255))
    rect(im,(64,0,96,24),(143,218,230,255))
    rect(im,(64,24,96,64),(115,196,218,255))
    rect(im,(96,0,128,32),(180,112,183,255))
    rect(im,(96,24,128,64),(150,92,164,255))
    rect(im,(112,64,128,80),(38,43,55,255),False)
    return im

for name in ["axolotl_pajama_hat","axolotl_pajama_chest","axolotl_pajama_legs","axolotl_pajama_feet"]:
    save(name,axo_pajama(name)); clear_emissive(name)

# Capucha conejita: rosa suave coherente, interior de orejas más claro.
im=canvas((219,142,178,255))
rect(im,(0,0,32,24),(226,154,188,255))
rect(im,(0,24,64,64),(210,127,166,255))
rect(im,(32,0,48,24),(247,188,210,255))
rect(im,(48,0,64,24),(247,188,210,255))
rect(im,(60,24,96,64),(235,164,194,255))
save("bunny_ear_hood",im)
clear_emissive("bunny_ear_hood")

print("Texturas solicitadas reconstruidas; abeja y gallina conservadas sin cambios.")
