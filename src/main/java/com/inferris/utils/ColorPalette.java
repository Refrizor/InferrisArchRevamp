package com.inferris.utils;

import net.kyori.adventure.text.format.TextColor;

public final class ColorPalette {

    // Predefined colors using TextColor from Adventure API
    public static final ColorPalette RESET = new ColorPalette(TextColor.color(255, 255, 255));
    public static final ColorPalette BLACK = new ColorPalette(TextColor.color(0, 0, 0));
    public static final ColorPalette RED = new ColorPalette(TextColor.color(255, 88, 51));
    public static final ColorPalette DARK_GRAY = new ColorPalette(TextColor.color(70, 70, 70));
    public static final ColorPalette GREEN = new ColorPalette(TextColor.color(85, 255, 85));
    public static final ColorPalette BLUE = new ColorPalette(TextColor.color(85, 85, 255));

    private final TextColor color; // Stores the actual TextColor object

    // Constructor accepting a TextColor object
    private ColorPalette(TextColor color) {
        this.color = color;
    }

    // Getter for the TextColor object
    public TextColor getColor() {
        return color;
    }

    // Converts the color to a Minecraft-friendly hex string format
    @Override
    public String toString() {
        String hex = String.format("%06x", color.value() & 0xFFFFFF); // Get the 6-digit hex code
        StringBuilder minecraftHex = new StringBuilder("§x");

        // Loop through the hex characters to format it with § for Minecraft
        for (char hexChar : hex.toCharArray()) {
            minecraftHex.append('§').append(hexChar);
        }

        return minecraftHex.toString(); // Outputs something like §x§f§f§5§8§3§3 for #ff5833
    }

    // Static factory method for creating custom colors
    public static ColorPalette fromRGB(int r, int g, int b) {
        return new ColorPalette(TextColor.color(r, g, b));
    }

    // Static factory method for creating from hex string
    public static ColorPalette fromHexString(String hex) {
        TextColor textColor = TextColor.fromHexString(hex);
        return textColor != null ? new ColorPalette(textColor) : null;
    }
}