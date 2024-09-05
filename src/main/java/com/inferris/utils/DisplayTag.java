package com.inferris.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public enum DisplayTag {
    ADMIN(createTaggedComponent("⎰", "Admin", "⎱", ColorPalette.RED.getColor(), ColorPalette.DARK_GRAY.getColor())),
    MOD(createSimpleComponent("[Mod]", ColorPalette.BLUE.getColor())),
    HELPER(createSimpleComponent("[Helper]", ColorPalette.GREEN.getColor())),
    SUPPORT2(createSimpleComponent("[Support 2]", ColorPalette.BLUE.getColor())),
    SUPPORTER(createSimpleComponent("[Supporter]", ColorPalette.GREEN.getColor()));

    private final Component displayComponent;

    DisplayTag(Component displayComponent) {
        this.displayComponent = displayComponent;
    }

    public Component getDisplayComponent() {
        return displayComponent;
    }

    // Helper method for components with tags (e.g. ⎰Admin⎱)
    private static Component createTaggedComponent(String leftBracket, String text, String rightBracket, TextColor mainColor, TextColor tagColor) {
        return Component.text(leftBracket)
                .color(tagColor)
                .append(Component.text(text).color(mainColor))
                .append(Component.text(rightBracket).color(tagColor));
    }

    // Helper method for simpler components (no tags, just text)
    private static Component createSimpleComponent(String text, TextColor color) {
        return Component.text(text).color(color);
    }

    @Override
    public String toString() {
        return displayComponent.toString(); // Might be useful for logging or fallback
    }
}