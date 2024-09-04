package com.inferris.model.rank;

import net.md_5.bungee.api.ChatColor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum DonorRank {
    NONE(0, null),
    SUPPORTER(1, ChatColor.AQUA + "[Supporter]"),
    SUPPORTER2(2, ChatColor.AQUA + "[Supporter +]"),
    ;

    private static final Collection<DonorRank> VALUES = Arrays.asList(values());
    private static final Map<Integer, DonorRank> BY_ID = VALUES.stream().collect(Collectors.toMap(DonorRank::getId, Function.identity()));

    public static Collection<DonorRank> getValues() {
        return VALUES;
    }

    public static DonorRank getById(int id) {
        for (DonorRank rank : values()) {
            if (rank.getId() == id) {
                return rank;
            }
        }
        return NONE;  // Default if no match
    }

    private final int id;
    private final String displayTag;  // New field for the display tag

    DonorRank(int id, String displayTag) {
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
