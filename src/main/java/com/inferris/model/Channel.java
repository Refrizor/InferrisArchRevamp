package com.inferris.model;

import com.inferris.rank.Branch;
import com.inferris.utils.ColorPalette;

public enum Channel {
    STAFF(Branch.STAFF, 1, ColorPalette.BLUE + "[STAFF]"),
    ADMIN(Branch.STAFF, 3, ColorPalette.RED + "[ADMIN]"),
    SPECIAL(Branch.OTHER, 1, "SPECIAL"),
    NONE(null, 0, "NONE");

    private final Branch branch;
    private final int minimumId;
    private final String tag;
    private final String lowercaseName;
    Channel(Branch branch, int minimumId, String message){
        this.branch = branch;
        this.minimumId = minimumId;
        this.tag = message;
        this.lowercaseName = name().toLowerCase();
    }

    public Branch getBranch() {
        return branch;
    }

    public int getMinimumId() {
        return minimumId;
    }

    public String getTag() {
        return tag;
    }

    public String getTag(boolean hasSpacer) {
        if (hasSpacer) {
            return tag + ColorPalette.RESET + " ";
        }
        return tag;
    }

    public String getLowercaseName() {
        return lowercaseName;
    }
}