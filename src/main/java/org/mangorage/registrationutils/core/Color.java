package org.mangorage.registrationutils.core;


import org.mangorage.registrationutils.utils.IRegistryEnum;

public enum Color implements IRegistryEnum {
    GREEN(java.awt.Color.GREEN.getRGB()),
    BLUE(java.awt.Color.BLUE.getRGB());

    private final int color;
    Color(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String getSubId() {
        return toString().toLowerCase();
    }
}
