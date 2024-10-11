package org.mangorage.registrationutils.core;


import org.mangorage.registrationutils.utils.IRegistryEnum;

public enum Color implements IRegistryEnum {
    // Predefined colors
    RED(0xFF0000, "red"),
    GREEN(0x00FF00, "green"),
    BLUE(0x0000FF, "blue"),
    YELLOW(0xFFFF00, "yellow"),
    ORANGE(0xFFA500, "orange"),
    PURPLE(0x800080, "purple"),
    PINK(0xFFB6C1, "pink"),
    BROWN(0xA52A2A, "brown"),
    GRAY(0x808080, "gray"),
    BLACK(0x000000, "black"),
    WHITE(0xFFFFFF, "white"),
    // Additional colors
    INDIGO(0x4B0082, "indigo"),
    VIOLET(0xEE82EE, "violet"),
    GOLD(0xDAA520, "gold"),
    SILVER(0xC0C0C0, "silver"),
    LIME(0x00FF00, "lime"),
    CYAN(0x00FFFF, "cyan"),
    MAGENTA(0xFF00FF, "magenta"),
    TEAL(0x008080, "teal"),
    NAVY(0x000080, "navy"),
    MAROON(0x800000, "maroon"),
    OLIVE(0x808000, "olive"),
    AQUA(0x00FFFF, "aqua"),
    FUCHSIA(0xFF00FF, "fuchsia"),
    LIMEGREEN(0x00FF00, "limegreen"),
    DARKGREEN(0x006400, "darkgreen"),
    DARKBLUE(0x00008B, "darkblue"),
    DARKRED(0x8B0000, "darkred"),
    LIGHTSALMON(0x7FF5A9, "lightsalmon"),
    LIGHTBLUE(0xADD8E6, "lightblue"),
    LIGHTGREEN(0x90EE90, "lightgreen"),
    LIGHTGRAY(0xD3D3D3, "lightgray"),
    DARKGRAY(0xA9A9A9, "darkgray"),
    DARKGREY(0xA9A9A9, "darkgrey"),
    MEDIUMBLUE(0x0000CD, "mediumblue"),
    MEDIUMGREEN(0x00FA9A, "mediumgreen"),
    MEDIUMGRAY(0x7F7F7F, "mediumgray"),
    MEDIUMGREY(0x7F7F7F, "mediumgrey"),
    SLATEBLUE(0x483D8B, "slateblue"),
    SLATEGRAY(0x708090, "slategray"),
    SLATEGREY(0x708090, "slategrey"),
    POWDERBLUE(0xB0E0E6, "powderblue"),
    LIGHTSKYBLUE(0x87CEEB, "lightskyblue"),
    SKYBLUE(0x87CEEB, "skyblue"),
    DEEPSKYBLUE(0x00BFFF, "deepskyblue"),
    DODGERBLUE(0x1E90FF, "dodgerblue"),
    TURQUOISE(0x40E0D0, "turquoise"),
    CHARTREUSE(0x7FFF00, "chartreuse"),
    FORESTGREEN(0x228B22, "forestgreen"),
    SEAGREEN(0x2E8B57, "seagreen"),
    OLIVEDRAB(0x6B8E23, "olivedrab"),
    DARKSEAGREEN(0x8FBC8F, "darkseagreen"),
    LIGHTSEAGREEN(0x20B2AA, "lightseagreen"),
    DARKORANGE(0xFF8C00, "darkorange"),
    CHOCOLATE(0xD2691E, "chocolate"),
    FIREBRICK(0xB22222, "firebrick"),
    CRIMSON(0xDC143C, "crimson"),
    GAINSBORO(0xDCDCDC, "gainsboro"),
    FLORALWHITE(0xFFFAFA, "floralwhite"),
    GHOSTWHITE(0xF8F8FF, "ghostwhite"),
    HONEYDEW(0xF0FFF0, "honeydew"),
    IVORY(0xFFFFF0, "ivory"),
    LAVENDERBLUSH(0xFFFFF0, "lavenderblush"),
    LEMONCHIFFON(0xFFFAFA, "lemonchiffon"),
    MINTCREAM(0xF5FFFA, "mintcream"),
    MISTYROSE(0xFFFFE4, "mistyrose"),
    MOCCASIN(0xFFFFE4, "moccasin"),
    NAVAJOWHITE(0xFFDEAD, "navajowhite"),
    OLDLACE(0xF0E68C, "oldlace"),
    PALEGOLDENROD(0xEEE8AA, "palegoldenrod"),
    PALEGREEN(0x98FB98, "palegreen"),
    PALETURQUOISE(0xAFEEEE, "paleturquoise"),
    PALEVIOLETRED(0xDB7093, "palevioletred"),
    PAPAYAWHIP(0xFFEFD5, "papayawhip"),
    PEACHPUFF(0xFFDAB9, "peachpuff"),
    PERU(0xCD853F, "peru"),
    SANDYBROWN(0xF4A460, "sandybrown"),
    SEASHELL(0xFFF5EE, "seashell"),
    SIENNA(0xA0522D, "sienna"),
    SMOKEWHITE(0xE0E0E0, "smokewhite"),
    SNOW(0xFFFFF0, "snow"),
    TAN(0xD2B48C, "tan"),
    THISTLE(0xD8BFD8, "thistle"),
    TOMATO(0xFF6347, "tomato"),
    WHEAT(0xF5DEB3, "wheat");


    private final int color;
    private final String name;

    Color(int color, String name) {
        this.color = color;
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String getSubId() {
        return name;
    }
}
