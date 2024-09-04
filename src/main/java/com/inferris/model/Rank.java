package com.inferris.model;

public class Rank {
    private SupporterRank supporterRank;
    private StaffRank staffRank;

    public Rank(SupporterRank supporterRank, StaffRank staffRank){
        this.supporterRank = supporterRank;
        this.staffRank = staffRank;
    }

    public SupporterRank getPackageRank() {
        return supporterRank;
    }

    public StaffRank getPlayerRank() {
        return staffRank;
    }

    public void setPackageRank(SupporterRank supporterRank) {
        this.supporterRank = supporterRank;
    }

    public void setPlayerRank(StaffRank staffRank) {
        this.staffRank = staffRank;
    }

    Rank(){}
}
