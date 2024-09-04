package com.inferris.model;

import com.inferris.utils.DisplayTag;
import net.md_5.bungee.api.ChatColor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PlayerRank {
    NORMAL(1, null),
    HELPER(2, ChatColor.AQUA + "[Helper]"),
    MODERATOR(3, ChatColor.GREEN + "[Mod]"),
    ADMIN(4, DisplayTag.ADMIN.getDisplay()) // No need to call .toString()
    ;

    private static final Collection<PlayerRank> VALUES = Arrays.asList(values());
    private static final Map<Integer, PlayerRank> BY_ID = VALUES.stream().collect(Collectors.toMap(PlayerRank::getId, Function.identity()));

    public static Collection<PlayerRank> getValues() {
        return VALUES;
    }

    public static Optional<PlayerRank> getById(int id) {
        return Optional.ofNullable(BY_ID.get(id));
    }

    private final int id;
    private final String displayTag;  // New field for the display tag

    PlayerRank(int id, String displayTag) {
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
