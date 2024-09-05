package com.inferris.model.rank;

import com.inferris.utils.DisplayTag;

public enum StaffRank {
    NONE(1, null),
    HELPER(2, null),
    MODERATOR(3, null),
    ADMIN(4, DisplayTag.ADMIN) // No need to call .toString()
    ;

    private final int id;
    private final DisplayTag displayTag;  // New field for the display tag

    StaffRank(int id, DisplayTag displayTag) {
        this.id = id;
        this.displayTag = displayTag;
    }

    public int getId() {
        return id;
    }

    public DisplayTag getDisplayTag() {
        return displayTag;
    }
}
