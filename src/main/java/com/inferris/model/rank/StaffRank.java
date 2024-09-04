package com.inferris.model.rank;

import com.inferris.utils.DisplayTag;
import net.md_5.bungee.api.ChatColor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum StaffRank {
    NONE(0, null),
    HELPER(1, ChatColor.AQUA + "[Helper]"),
    MODERATOR(2, ChatColor.GREEN + "[Mod]"),
    ADMIN(3, DisplayTag.ADMIN.getDisplay()) // No need to call .toString()
    ;

    private static final Collection<StaffRank> VALUES = Arrays.asList(values());
    private static final Map<Integer, StaffRank> BY_ID = VALUES.stream().collect(Collectors.toMap(StaffRank::getId, Function.identity()));

    public static Collection<StaffRank> getValues() {
        return VALUES;
    }

    public static StaffRank getById(int id) {
        for (StaffRank rank : values()) {
            if (rank.getId() == id) {
                return rank;
            }
        }
        return NONE;  // Default if no match
    }

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
