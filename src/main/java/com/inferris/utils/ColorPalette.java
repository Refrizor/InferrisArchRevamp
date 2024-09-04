package com.inferris.utils;

import java.awt.*;

public final class ColorPalette {
    public static final char COLOR_CHAR = '§'; // Used to format the color in strings (optional)

    // Predefined colors
    public static final ColorPalette BLACK = new ColorPalette(new Color(0, 0, 0));
    public static final ColorPalette RED = new ColorPalette(new Color(255, 88, 51));
    public static final ColorPalette GREEN = new ColorPalette(new Color(85, 255, 85));
    public static final ColorPalette BLUE = new ColorPalette(new Color(85, 85, 255));

    private final Color color; // Stores the actual Color object

    // Constructor without the 'code' field
    private ColorPalette(Color color) {
        this.color = color;
    }

    // Optional getter for the actual Color object
    public Color getColor() {
        return color;
    }

    // Converts the color to a string format, here for example purposes using hex
    @Override
    public String toString() {
        String hex = String.format("%06x", color.getRGB() & 0xFFFFFF); // Get the 6-digit hex code
        StringBuilder minecraftHex = new StringBuilder("§x");

        for (char hexChar : hex.toCharArray()) {
            minecraftHex.append('§').append(hexChar);
        }

        return minecraftHex.toString(); // Outputs §x§f§f§5§8§3§3 for #ff5833
    }

    // Strip color codes from strings (if needed)
    public static String stripColor(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll(COLOR_CHAR + "[0-9A-Fa-fK-Ok-or]", "");
    }

    // Translate color codes in text using alternate color chars
    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        return textToTranslate.replace(altColorChar, COLOR_CHAR);
    }
}