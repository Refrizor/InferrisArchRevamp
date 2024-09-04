package com.inferris.model;

import com.inferris.model.rank.StaffRank;
import com.inferris.model.rank.SupporterRank;

public class Rank {
    private SupporterRank supporterRank;
    private StaffRank staffRank;

    public Rank(SupporterRank supporterRank, StaffRank staffRank){
        this.supporterRank = supporterRank;
        this.staffRank = staffRank;
    }

    public SupporterRank getSupporterRank() {
        return supporterRank;
    }

    public StaffRank getStaffRank() {
        return staffRank;
    }

    public void setSupporterRank(SupporterRank supporterRank) {
        this.supporterRank = supporterRank;
    }

    public void setStaffRank(StaffRank staffRank) {
        this.staffRank = staffRank;
    }

    Rank(){}
}
