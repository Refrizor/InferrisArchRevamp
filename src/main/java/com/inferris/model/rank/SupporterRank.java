package com.inferris.model.rank;

import com.inferris.utils.DisplayTag;

public enum SupporterRank {
    NONE(1, null),
    SUPPORTER(2, null),
    SUPPORTER2(3, null),
    ;

    private final int id;
    private final DisplayTag displayTag;  // New field for the display tag

    SupporterRank(int id, DisplayTag displayTag) {
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
