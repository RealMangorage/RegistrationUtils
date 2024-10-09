package org.mangorage.registrationutils.core;

import org.mangorage.registrationutils.utils.IRegistrable;

public enum Color implements IRegistrable {
    GREEN(java.awt.Color.GREEN.getRGB()),
    BLUE(java.awt.Color.BLUE.getRGB());

    private final int color;
    Color(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
