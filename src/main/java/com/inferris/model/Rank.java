package com.inferris.model;

public class Rank {
    private PackageRank packageRank;
    private PlayerRank playerRank;

    public Rank(PackageRank packageRank, PlayerRank playerRank){
        this.packageRank = packageRank;
        this.playerRank = playerRank;
    }

    public PackageRank getPackageRank() {
        return packageRank;
    }

    public PlayerRank getPlayerRank() {
        return playerRank;
    }

    public void setPackageRank(PackageRank packageRank) {
        this.packageRank = packageRank;
    }

    public void setPlayerRank(PlayerRank playerRank) {
        this.playerRank = playerRank;
    }
}
