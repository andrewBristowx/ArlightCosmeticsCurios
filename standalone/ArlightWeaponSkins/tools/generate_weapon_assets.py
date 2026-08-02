#!/usr/bin/env python3
"""Genera los 12 modelos GeckoLib v1.2 con geometría, UV y transforms de ítem."""

from __future__ import annotations

import json
from pathlib import Path
from PIL import Image, ImageDraw

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "src/main/resources/assets/arlightweaponskins"
CURIOS_MODELS = ROOT / "src/main/resources/assets/arlightcosmeticscurios/models/item"
TEXTURE_SIZE = 128
TILE = 32
TOOLS = ("sword", "pickaxe", "axe", "shovel", "hoe", "bow")

PALETTES = {
    "somita": [
        (24, 14, 27), (48, 20, 44), (82, 25, 65), (126, 31, 84),
        (183, 38, 100), (221, 43, 75), (246, 82, 145), (255, 163, 202),
        (239, 202, 226), (112, 12, 31), (255, 71, 116), (255, 220, 235),
    ],
    "pony": [
        (35, 36, 83), (54, 61, 125), (69, 123, 194), (80, 190, 235),
        (139, 225, 251), (244, 113, 168), (255, 174, 205), (229, 220, 255),
        (235, 252, 250), (116, 78, 68), (177, 130, 103), (255, 244, 196),
    ],
}


def clamp(value: int) -> int:
    return max(0, min(255, value))


def shift(color: tuple[int, int, int], amount: int) -> tuple[int, int, int, int]:
    return tuple(clamp(channel + amount) for channel in color) + (255,)


def face_uv(index: int) -> dict:
    x = (index % 4) * TILE
    y = (index // 4) * TILE
    face = {"uv": [x, y], "uv_size": [TILE, TILE]}
    return {name: dict(face) for name in ("north", "east", "south", "west", "up", "down")}


def cube(origin, size, material=0, *, pivot=None, rotation=None, inflate=None, mirror=False) -> dict:
    result = {"origin": list(origin), "size": list(size), "uv": face_uv(material)}
    if pivot is not None:
        result["pivot"] = list(pivot)
    if rotation is not None:
        result["rotation"] = list(rotation)
    if inflate is not None:
        result["inflate"] = inflate
    if mirror:
        result["mirror"] = True
    return result


def segment(x, y, z, sx, sy, sz, material, angle=0, pivot=None):
    pivot = pivot or (x + sx / 2, y, z + sz / 2)
    return cube((x, y, z), (sx, sy, sz), material, pivot=pivot, rotation=(0, 0, angle))


def common_bones(theme: str) -> list[dict]:
    dark = 0
    metal = 2 if theme == "somita" else 1
    wrap = 3 if theme == "somita" else 2
    glow = 10 if theme == "somita" else 4
    pale = 8
    pommel = 4 if theme == "somita" else 5
    handle = [cube((-1.15, -2.2, -1.15), (2.3, 14.7, 2.3), metal)]
    for y in (-1.4, 1.2, 3.8, 6.4, 9.0):
        handle.append(cube((-1.48, y, -1.48), (2.96, 1.15, 2.96), wrap))
        handle.append(cube((-1.0, y + 0.25, -1.72), (2.0, 0.55, 0.55), glow))
    handle.extend([
        cube((-2.25, 11.0, -1.55), (4.5, 2.0, 3.1), dark),
        cube((-2.75, 11.65, -1.25), (5.5, 0.75, 2.5), pale),
        cube((-2.25, -4.4, -1.5), (4.5, 2.6, 3.0), pommel,
             pivot=(0, -3.1, 0), rotation=(0, 0, 45)),
        cube((-1.1, -5.05, -1.8), (2.2, 2.0, 3.6), glow,
             pivot=(0, -3.1, 0), rotation=(0, 0, 45)),
        cube((-0.55, -4.35, -2.1), (1.1, 1.1, 0.55), pale,
             pivot=(0, -3.1, 0), rotation=(0, 0, 45)),
    ])
    return [
        {"name": "root", "pivot": [0, 0, 0]},
        {"name": "handle", "parent": "root", "pivot": [0, 5, 0], "cubes": handle},
        {"name": "core", "parent": "root", "pivot": [0, 14.5, -1.8], "cubes": [
            cube((-3.1, 11.4, -1.8), (6.2, 6.2, 3.0), dark,
                 pivot=(0, 14.5, 0), rotation=(0, 0, 45)),
            cube((-2.35, 12.15, -2.25), (4.7, 4.7, 1.2), 5 if theme == "somita" else 5,
                 pivot=(0, 14.5, 0), rotation=(0, 0, 45)),
            cube((-1.45, 13.05, -2.7), (2.9, 2.9, 0.65), 10 if theme == "somita" else 11,
                 pivot=(0, 14.5, 0), rotation=(0, 0, 45)),
        ]},
    ]


def themed_accents(theme: str, y: float = 14.2) -> list[dict]:
    if theme == "somita":
        left = [
            segment(-8.0, y - 0.7, -0.75, 6.1, 1.25, 1.5, 0, -15, (-2, y, 0)),
            segment(-8.6, y - 3.4, -0.65, 4.9, 3.0, 1.3, 3, -31, (-2, y, 0)),
            segment(-8.2, y - 5.0, -0.55, 2.2, 3.2, 1.1, 6, -43, (-2, y, 0)),
            segment(-6.0, y - 3.1, -0.9, 1.1, 2.8, 1.8, 4, -22, (-2, y, 0)),
            segment(-9.0, y - 1.0, -0.6, 1.3, 2.3, 1.2, 8, -18, (-2, y, 0)),
        ]
        right = [
            segment(1.9, y - 0.7, -0.75, 6.1, 1.25, 1.5, 0, 15, (2, y, 0)),
            segment(3.7, y - 3.4, -0.65, 4.9, 3.0, 1.3, 3, 31, (2, y, 0)),
            segment(6.0, y - 5.0, -0.55, 2.2, 3.2, 1.1, 6, 43, (2, y, 0)),
            segment(4.9, y - 3.1, -0.9, 1.1, 2.8, 1.8, 4, 22, (2, y, 0)),
            segment(7.7, y - 1.0, -0.6, 1.3, 2.3, 1.2, 8, 18, (2, y, 0)),
        ]
    else:
        # Cabeza de ajolote, tres branquias por lado y cuernos segmentados.
        left = [
            cube((-4.1, y - 1.7, -2.55), (2.2, 3.4, 1.3), 5),
            segment(-6.2, y + 0.6, -1.45, 3.0, 1.0, 1.4, 5, 24, (-3.2, y, 0)),
            segment(-6.5, y - 0.3, -1.4, 3.2, 1.0, 1.3, 6, 8, (-3.2, y, 0)),
            segment(-6.1, y - 1.5, -1.35, 2.8, 1.0, 1.2, 5, -22, (-3.2, y, 0)),
            segment(-3.4, y + 1.1, -1.2, 1.3, 4.2, 1.4, 9, -13, (-2.7, y + 1.2, 0)),
            segment(-3.1, y + 4.4, -1.1, 1.0, 2.8, 1.2, 10, -24, (-2.6, y + 4.4, 0)),
        ]
        right = [
            cube((1.9, y - 1.7, -2.55), (2.2, 3.4, 1.3), 5),
            segment(3.2, y + 0.6, -1.45, 3.0, 1.0, 1.4, 5, -24, (3.2, y, 0)),
            segment(3.3, y - 0.3, -1.4, 3.2, 1.0, 1.3, 6, -8, (3.2, y, 0)),
            segment(3.3, y - 1.5, -1.35, 2.8, 1.0, 1.2, 5, 22, (3.2, y, 0)),
            segment(2.1, y + 1.1, -1.2, 1.3, 4.2, 1.4, 9, 13, (2.7, y + 1.2, 0)),
            segment(2.1, y + 4.4, -1.1, 1.0, 2.8, 1.2, 10, 24, (2.6, y + 4.4, 0)),
        ]
        face = [
            cube((-2.7, y - 2.1, -2.1), (5.4, 4.2, 2.4), 8),
            cube((-1.65, y - 0.65, -2.65), (0.65, 0.65, 0.5), 0),
            cube((1.0, y - 0.65, -2.65), (0.65, 0.65, 0.5), 0),
            cube((-0.45, y - 1.25, -2.7), (0.9, 0.45, 0.45), 5),
        ]
        left.extend(face)
    return [
        {"name": "accent_left", "parent": "root", "pivot": [-2, y, 0], "cubes": left},
        {"name": "accent_right", "parent": "root", "pivot": [2, y, 0], "cubes": right},
    ]


def head_sword(theme: str) -> list[dict]:
    blade, edge, glow = ((2, 7, 10) if theme == "somita" else (3, 8, 5))
    cubes = [
        cube((-3.2, 15.8, -1.0), (6.4, 13.5, 2.0), blade),
        cube((-2.2, 28.3, -0.8), (4.4, 5.0, 1.6), edge, pivot=(0, 30, 0), rotation=(0, 0, 45)),
        cube((-1.0, 16.2, -1.35), (2.0, 14.6, 0.7), glow),
        segment(-4.1, 18.1, -0.8, 2.0, 10.2, 1.6, edge, 8, (0, 16, 0)),
        segment(2.1, 18.1, -0.8, 2.0, 10.2, 1.6, edge, -8, (0, 16, 0)),
        cube((-5.2, 14.9, -1.25), (10.4, 2.0, 2.5), 0),
        segment(-7.0, 14.0, -1.0, 3.0, 1.5, 2.0, edge, -26, (-4.4, 15.6, 0)),
        segment(4.0, 14.0, -1.0, 3.0, 1.5, 2.0, edge, 26, (4.4, 15.6, 0)),
    ]
    return [{"name": "tool_head", "parent": "root", "pivot": [0, 16, 0], "cubes": cubes}]


def head_pickaxe(theme: str) -> list[dict]:
    main, edge, glow = ((2, 7, 10) if theme == "somita" else (3, 8, 5))
    cubes = [cube((-3.1, 14.9, -1.5), (6.2, 4.0, 3.0), 0)]
    for side in (-1, 1):
        angle = -side
        x = -3.0 if side < 0 else 3.0
        cubes.extend([
            segment(x - (6.4 if side < 0 else 0), 15.1, -1.25, 6.4, 2.5, 2.5, main, 12 * angle, (x, 16, 0)),
            segment(x - (10.2 if side < 0 else -6.0), 13.5, -1.05, 4.2, 2.2, 2.1, edge, 25 * angle, (x + 5.5 * side, 15.5, 0)),
            segment(x - (12.8 if side < 0 else -10.0), 10.8, -0.8, 2.8, 3.6, 1.6, glow, 38 * angle, (x + 9.5 * side, 13.5, 0)),
            segment(x - (13.9 if side < 0 else -12.0), 8.3, -0.65, 1.8, 3.3, 1.3, edge, 48 * angle, (x + 11.5 * side, 11.2, 0)),
        ])
    return [{"name": "tool_head", "parent": "root", "pivot": [0, 16, 0], "cubes": cubes}]


def head_axe(theme: str) -> list[dict]:
    main, edge, glow = ((2, 7, 10) if theme == "somita" else (3, 8, 5))
    cubes = [cube((-2.8, 13.8, -1.7), (5.6, 7.0, 3.4), 0)]
    for side in (-1, 1):
        x = -10.0 if side < 0 else 2.2
        cubes.extend([
            cube((x, 14.3, -1.35), (7.8, 8.2, 2.7), main),
            segment(x - (1.0 if side < 0 else -5.8), 13.0, -1.1, 3.0, 10.6, 2.2, edge, -7 * side, (x + 3.9, 18, 0)),
            segment(x + 1.0, 20.3, -0.95, 5.8, 2.4, 1.9, glow, 12 * side, (x + 3.9, 19, 0)),
            segment(x + 1.0, 13.4, -0.95, 5.8, 2.4, 1.9, glow, -12 * side, (x + 3.9, 17, 0)),
        ])
    return [{"name": "tool_head", "parent": "root", "pivot": [0, 17, 0], "cubes": cubes}]


def head_shovel(theme: str) -> list[dict]:
    main, edge, glow = ((2, 7, 10) if theme == "somita" else (3, 8, 5))
    cubes = [
        cube((-2.0, 14.0, -1.2), (4.0, 6.5, 2.4), 0),
        cube((-6.5, 19.0, -1.5), (13.0, 8.2, 3.0), main),
        segment(-5.3, 25.5, -1.25, 10.6, 4.4, 2.5, edge, 45, (0, 27.0, 0)),
        segment(-7.2, 19.7, -1.1, 2.4, 6.5, 2.2, edge, -10, (-5.5, 21.0, 0)),
        segment(4.8, 19.7, -1.1, 2.4, 6.5, 2.2, edge, 10, (5.5, 21.0, 0)),
        cube((-3.8, 20.0, -1.9), (7.6, 5.5, 0.7), glow),
        cube((-1.4, 22.0, -2.3), (2.8, 2.8, 0.55), 5 if theme == "somita" else 11,
             pivot=(0, 23.4, 0), rotation=(0, 0, 45)),
    ]
    return [{"name": "tool_head", "parent": "root", "pivot": [0, 20, 0], "cubes": cubes}]


def head_hoe(theme: str) -> list[dict]:
    main, edge, glow = ((2, 7, 10) if theme == "somita" else (3, 8, 5))
    cubes = [cube((-2.6, 13.8, -1.5), (5.2, 6.5, 3.0), 0)]
    # Hoja curva en forma de media luna.
    cubes.extend([
        segment(-1.0, 17.2, -1.25, 8.0, 3.0, 2.5, main, -8, (0, 17, 0)),
        segment(5.0, 14.5, -1.15, 5.0, 3.0, 2.3, main, -28, (6.0, 17, 0)),
        segment(7.8, 11.0, -1.0, 3.2, 4.4, 2.0, edge, -44, (8.0, 14, 0)),
        segment(8.8, 8.3, -0.8, 2.0, 3.6, 1.6, glow, -55, (9.2, 11, 0)),
        segment(1.3, 20.1, -0.85, 6.2, 1.5, 1.7, edge, -8, (1.0, 19, 0)),
        segment(-7.0, 15.0, -1.0, 5.4, 2.5, 2.0, main, 20, (-2.0, 17, 0)),
    ])
    return [{"name": "tool_head", "parent": "root", "pivot": [0, 17, 0], "cubes": cubes}]


def head_bow(theme: str) -> list[dict]:
    main, edge, glow = ((2, 7, 10) if theme == "somita" else (3, 8, 5))
    cubes = [cube((-2.2, 11.0, -1.65), (4.4, 7.0, 3.3), 0)]
    parts = [
        (-5.4, 16.0, 4.4, 7.5, 22), (-8.2, 21.8, 3.8, 7.0, 38), (-9.8, 27.0, 2.7, 5.4, 52),
        (1.0, 4.2, 4.4, 7.5, -22), (4.6, -1.2, 3.8, 7.0, -38), (7.2, -5.3, 2.7, 5.4, -52),
    ]
    for index, (x, y, sx, sy, angle) in enumerate(parts):
        cubes.append(segment(x, y, -1.25, sx, sy, 2.5, main if index % 2 == 0 else edge, angle, (0, 14, 0)))
        cubes.append(segment(x + 0.5, y + 0.6, -1.65, max(1.0, sx - 1.0), max(1.5, sy - 1.2), 0.55, glow, angle, (0, 14, 0)))
    cubes.extend([
        segment(-8.8, -2.2, 0.0, 0.42, 32.8, 0.42, 8 if theme == "somita" else 8, 0, (0, 14, 0)),
        cube((-1.2, 13.0, -2.5), (2.4, 2.4, 0.6), 5 if theme == "somita" else 5,
             pivot=(0, 14.2, 0), rotation=(0, 0, 45)),
    ])
    return [{"name": "tool_head", "parent": "root", "pivot": [0, 14, 0], "cubes": cubes}]


HEAD_BUILDERS = {
    "sword": head_sword,
    "pickaxe": head_pickaxe,
    "axe": head_axe,
    "shovel": head_shovel,
    "hoe": head_hoe,
    "bow": head_bow,
}


def geometry(theme: str, tool: str) -> dict:
    bones = common_bones(theme)
    bones.extend(HEAD_BUILDERS[tool](theme))
    bones.extend(themed_accents(theme, 14.3 if tool != "bow" else 13.8))
    return {
        "format_version": "1.12.0",
        "minecraft:geometry": [{
            "description": {
                "identifier": f"geometry.arlightweapons.{theme}_{tool}",
                "texture_width": TEXTURE_SIZE,
                "texture_height": TEXTURE_SIZE,
                "visible_bounds_width": 4.4,
                "visible_bounds_height": 5.2,
                "visible_bounds_offset": [0, 1.25, 0],
            },
            "bones": bones,
        }],
    }


def animation(theme: str, tool: str) -> dict:
    wing = 3.5 if theme == "somita" else 2.0
    return {
        "format_version": "1.8.0",
        "animations": {
            f"animation.arlightweapons.{theme}_{tool}.idle": {
                "loop": True,
                "animation_length": 2.4,
                "bones": {
                    "core": {"scale": {"0.0": [1, 1, 1], "1.2": [1.08, 1.08, 1.08], "2.4": [1, 1, 1]}},
                    "accent_left": {"rotation": {"0.0": [0, 0, 0], "1.2": [0, 0, -wing], "2.4": [0, 0, 0]}},
                    "accent_right": {"rotation": {"0.0": [0, 0, 0], "1.2": [0, 0, wing], "2.4": [0, 0, 0]}},
                },
            }
        },
    }


def draw_tile(draw: ImageDraw.ImageDraw, index: int, color: tuple[int, int, int], theme: str) -> None:
    x0 = (index % 4) * TILE
    y0 = (index // 4) * TILE
    for y in range(TILE):
        shade = int(20 * (1 - y / (TILE - 1))) - 7
        for x in range(TILE):
            checker = 4 if ((x // 4 + y // 4) % 2 == 0) else -3
            draw.point((x0 + x, y0 + y), fill=shift(color, shade + checker))
    draw.rectangle((x0, y0, x0 + TILE - 1, y0 + TILE - 1), outline=shift(color, 35), width=2)
    draw.line((x0 + 3, y0 + 3, x0 + TILE - 5, y0 + 3), fill=shift(color, 48), width=2)
    draw.line((x0 + 3, y0 + 4, x0 + 3, y0 + TILE - 5), fill=shift(color, 25), width=2)
    draw.line((x0 + 5, y0 + TILE - 4, x0 + TILE - 4, y0 + TILE - 4), fill=shift(color, -38), width=2)
    # Cada material tiene una lectura distinta: metal, gema, membrana/cristal, tela o brillo.
    if index in (2, 3):
        for p in range(5, 28, 7):
            draw.line((x0 + p, y0 + 4, x0 + p - 4, y0 + 28), fill=shift(color, -18), width=1)
    if index in (4, 5, 10, 11):
        draw.polygon([(x0 + 16, y0 + 4), (x0 + 28, y0 + 16), (x0 + 16, y0 + 28), (x0 + 4, y0 + 16)],
                     outline=shift(color, 65), fill=shift(color, 8))
        draw.polygon([(x0 + 16, y0 + 7), (x0 + 24, y0 + 16), (x0 + 16, y0 + 23), (x0 + 8, y0 + 16)],
                     fill=shift(color, 28))
        draw.rectangle((x0 + 13, y0 + 10, x0 + 16, y0 + 13), fill=(255, 255, 255, 230))
    if theme == "somita" and index == 6:
        draw.line((x0 + 3, y0 + 4, x0 + 28, y0 + 28), fill=shift(color, -70), width=2)
        draw.line((x0 + 12, y0 + 13, x0 + 8, y0 + 27), fill=shift(color, -58), width=2)
        draw.line((x0 + 20, y0 + 21, x0 + 27, y0 + 11), fill=shift(color, -58), width=2)
    if theme == "pony" and index in (3, 4):
        draw.line((x0 + 3, y0 + 27, x0 + 27, y0 + 3), fill=(235, 255, 255, 235), width=2)
        draw.line((x0 + 8, y0 + 29, x0 + 29, y0 + 8), fill=(170, 236, 255, 210), width=2)
    if theme == "pony" and index == 8:
        draw.rectangle((x0 + 7, y0 + 10, x0 + 10, y0 + 13), fill=(22, 25, 50, 255))
        draw.rectangle((x0 + 21, y0 + 10, x0 + 24, y0 + 13), fill=(22, 25, 50, 255))
        draw.rectangle((x0 + 14, y0 + 17, x0 + 17, y0 + 19), fill=(238, 105, 161, 255))


def texture(theme: str, tool: str, output: Path) -> None:
    image = Image.new("RGBA", (TEXTURE_SIZE, TEXTURE_SIZE), (0, 0, 0, 0))
    draw = ImageDraw.Draw(image)
    for index, color in enumerate(PALETTES[theme]):
        draw_tile(draw, index, color, theme)
    # Marca diminuta específica en un rincón del atlas para distinguir cada archivo durante depuración.
    tool_index = TOOLS.index(tool)
    draw.rectangle((116, 116, 122, 122), fill=shift(PALETTES[theme][5], tool_index * 4))
    image.save(output)


def item_model(tool: str) -> dict:
    size = {"sword": 0.48, "pickaxe": 0.46, "axe": 0.43, "shovel": 0.43, "hoe": 0.46, "bow": 0.49}[tool]
    gui = {"sword": 0.43, "pickaxe": 0.40, "axe": 0.38, "shovel": 0.38, "hoe": 0.40, "bow": 0.43}[tool]
    return {
        "parent": "builtin/entity",
        "gui_light": "front",
        "display": {
            "gui": {"rotation": [24, 225, 0], "translation": [0, -1.2, 0], "scale": [gui, gui, gui]},
            "ground": {"rotation": [0, 0, 0], "translation": [0, 2.2, 0], "scale": [0.30, 0.30, 0.30]},
            "fixed": {"rotation": [0, 180, 0], "translation": [0, -0.5, 0], "scale": [0.43, 0.43, 0.43]},
            "thirdperson_righthand": {"rotation": [0, 90, 55], "translation": [0, 1.8, 0.35], "scale": [size, size, size]},
            "thirdperson_lefthand": {"rotation": [0, -90, -55], "translation": [0, 1.8, 0.35], "scale": [size, size, size]},
            "firstperson_righthand": {"rotation": [0, -90, 25], "translation": [1.0, 2.7, 1.0], "scale": [size * 1.14] * 3},
            "firstperson_lefthand": {"rotation": [0, 90, -25], "translation": [1.0, 2.7, 1.0], "scale": [size * 1.14] * 3},
        },
    }


def main() -> None:
    for directory in ("geo", "animations", "models/item", "textures/item"):
        (ASSETS / directory).mkdir(parents=True, exist_ok=True)
    CURIOS_MODELS.mkdir(parents=True, exist_ok=True)
    for theme in PALETTES:
        for tool in TOOLS:
            visual = f"{theme}_{tool}"
            (ASSETS / "geo" / f"{visual}.geo.json").write_text(
                json.dumps(geometry(theme, tool), ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
            (ASSETS / "animations" / f"{visual}.animation.json").write_text(
                json.dumps(animation(theme, tool), ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
            model = item_model(tool)
            model_text = json.dumps(model, ensure_ascii=False, indent=2) + "\n"
            (ASSETS / "models/item" / f"{visual}.json").write_text(model_text, encoding="utf-8")
            (CURIOS_MODELS / f"{theme}_weapon_{tool}.json").write_text(model_text, encoding="utf-8")
            texture(theme, tool, ASSETS / "textures/item" / f"{visual}.png")


if __name__ == "__main__":
    main()
