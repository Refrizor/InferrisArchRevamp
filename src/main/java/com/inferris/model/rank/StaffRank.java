package com.inferris.model.rank;

import com.inferris.utils.DisplayTag;
import net.md_5.bungee.api.ChatColor;

public enum StaffRank {
    NONE(1, null),
    HELPER(2, ChatColor.AQUA + "[Helper]"),
    MODERATOR(3, ChatColor.GREEN + "[Mod]"),
    ADMIN(4, DisplayTag.ADMIN.getDisplay()) // No need to call .toString()
    ;

    private final int id;
    private final String displayTag;  // New field for the display tag

    StaffRank(int id, String displayTag) {
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
