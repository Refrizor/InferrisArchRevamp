package com.inferris.model.rank;

import net.md_5.bungee.api.ChatColor;

public enum SupporterRank {
    NONE(1, null),
    SUPPORTER(2, ChatColor.AQUA + "[Supporter]"),
    SUPPORTER2(3, ChatColor.AQUA + "[Supporter 2 Test]"),
    ;

    private final int id;
    private final String displayTag;  // New field for the display tag

    SupporterRank(int id, String displayTag) {
        this.id = id;
        this.displayTag = displayTag;
    }

    public int getId() {
        return id;
    }

    public String getDisplayTag() {
        return displayTag;
    }
}
