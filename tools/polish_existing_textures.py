from pathlib import Path
from PIL import Image, ImageEnhance
import colorsys

ROOT = Path(__file__).resolve().parents[1]
TEXTURES = ROOT / "src/main/resources/assets/arlightcosmeticscurios/textures/cosmetics"
TARGETS = {
    "dino_pajama_hat", "dino_pajama_chest", "dino_pajama_legs", "dino_pajama_feet",
    "axolotl_pajama_hat", "axolotl_pajama_chest", "axolotl_pajama_legs", "axolotl_pajama_feet",
    "bunny_ear_hood", "moon_star_cardigan", "cloud_skirt", "cloud_slippers",
    "pastel_heart_backpack", "swansito_mini_trex",
    "mobchibi_creeper", "mobchibi_enderman", "mobchibi_bee", "mobchibi_axolotl",
    "mobchibi_slime", "mobchibi_warden", "mobchibi_chicken", "mobchibi_fox",
    "mobchibi_frog", "mobchibi_allay", "mobchibi_zombie", "mobchibi_skeleton",
}


def clamp(value: float) -> int:
    return max(0, min(255, round(value)))


def polish_base(path: Path) -> None:
    image = Image.open(path).convert("RGBA")
    px = image.load()
    alpha = image.getchannel("A")
    full_opaque = alpha.getextrema() == (255, 255)

    if full_opaque:
        rgb = image.convert("RGB")
        rgb = ImageEnhance.Contrast(rgb).enhance(1.06)
        rgb = ImageEnhance.Color(rgb).enhance(1.05)
        image = Image.merge("RGBA", (*rgb.split(), alpha))
        px = image.load()

    source_alpha = alpha.load()
    width, height = image.size
    for y in range(height):
        for x in range(width):
            r, g, b, a = px[x, y]
            if a == 0:
                continue
            checker = 1.025 if ((x // 2 + y // 2) & 1) == 0 else 0.985
            edge_light = 1.0
            if not full_opaque:
                top = source_alpha[x, max(0, y - 1)]
                left = source_alpha[max(0, x - 1), y]
                bottom = source_alpha[x, min(height - 1, y + 1)]
                right = source_alpha[min(width - 1, x + 1), y]
                if top == 0 or left == 0:
                    edge_light *= 1.07
                if bottom == 0 or right == 0:
                    edge_light *= 0.91
            factor = checker * edge_light
            px[x, y] = (clamp(r * factor), clamp(g * factor), clamp(b * factor), a)
    image.save(path)


def polish_emissive(base_path: Path, glow_path: Path) -> None:
    if not glow_path.exists():
        return
    base = Image.open(base_path).convert("RGBA")
    glow = Image.open(glow_path).convert("RGBA")
    bp = base.load()
    gp = glow.load()
    nonzero = sum(1 for value in glow.getchannel("A").getdata() if value > 0)
    broad = nonzero > 420
    for y in range(glow.height):
        for x in range(glow.width):
            gr, gg, gb, ga = gp[x, y]
            if ga == 0:
                continue
            br, bg, bb, ba = bp[x, y]
            h, s, v = colorsys.rgb_to_hsv(br / 255.0, bg / 255.0, bb / 255.0)
            if broad and not (v > 0.58 and s > 0.16):
                gp[x, y] = (0, 0, 0, 0)
                continue
            alpha = ga * (0.60 if broad else 0.82)
            gp[x, y] = (max(gr, br), max(gg, bg), max(gb, bb), clamp(alpha))
    glow.save(glow_path)


for name in sorted(TARGETS):
    base = TEXTURES / f"{name}.png"
    if not base.exists():
        continue
    polish_base(base)
    polish_emissive(base, TEXTURES / f"{name}_emissive.png")
    print("polished", name)
