package com.inferris.model;

import net.md_5.bungee.api.ChatColor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PackageRank {
    NONE(1, null),
    SUPPORTER(2, ChatColor.AQUA + "[Supporter]"),
    SUPPORTER2(3, ChatColor.AQUA + "[Supporter +]"),
    ;

    private static final Collection<PackageRank> VALUES = Arrays.asList(values());
    private static final Map<Integer, PackageRank> BY_ID = VALUES.stream().collect(Collectors.toMap(PackageRank::getId, Function.identity()));

    public static Collection<PackageRank> getValues() {
        return VALUES;
    }

    public static Optional<PackageRank> getById(int id) {
        return Optional.ofNullable(BY_ID.get(id));
    }

    private final int id;
    private final String displayTag;  // New field for the display tag

    PackageRank(int id, String displayTag) {
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
