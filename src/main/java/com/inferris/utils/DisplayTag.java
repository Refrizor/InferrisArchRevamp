package com.inferris.utils;

import net.md_5.bungee.api.ChatColor;

public enum DisplayTag {
    ADMIN(ChatColor.DARK_GRAY + "⎰" + ColorPalette.RED + "Admin" + ChatColor.DARK_GRAY + "⎱"),
    MOD(ChatColor.GREEN + "[Mod]"),
    HELPER(ChatColor.AQUA + "[Helper]"),
    SUPPORT2(ChatColor.BLUE + "[Support 2]"),
    SUPPORTER(ChatColor.YELLOW + "[Supporter]");

    private final String display;

    DisplayTag(String display) {
        this.display = ChatUtils.translateAlternateColorCodes('&', display);
    }

    public String getDisplay() {
        return display;
    }

    @Override
    public String toString() {
        return display;
    }
}