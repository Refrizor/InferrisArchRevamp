package com.inferris.model;

import java.io.Serializable;
import java.util.UUID;

public class PlayerData implements Serializable {
    private UUID uuid;
    private String username;
    private PackageRank packageRank;
    private PlayerRank playerRank;
    private Profile profile;
    private int coins;
    private Channel channel;
    private boolean vanished;
    private Server currentServer;

    public PlayerData(UUID uuid, String username, PackageRank packageRank, PlayerRank playerRank, Profile profile, int coins, Channel channel, boolean vanished, Server currentServer) {
        this.uuid = uuid;
        this.username = username;
        this.packageRank = packageRank;
        this.playerRank = playerRank;
        this.profile = profile;
        this.coins = coins;
        this.channel = channel;
        this.vanished = vanished;
        this.currentServer = currentServer;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public PackageRank getPackageRank() {
        return packageRank;
    }

    public PlayerRank getPlayerRank() {
        return playerRank;
    }

    public Profile getProfile() {
        return profile;
    }

    public int getCoins() {
        return coins;
    }

    public Channel getChannel() {
        return channel;
    }

    public boolean isVanished() {
        return vanished;
    }

    public Server getCurrentServer() {
        return currentServer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPackageRank(PackageRank packageRank) {
        this.packageRank = packageRank;
    }

    public void setPlayerRank(PlayerRank playerRank) {
        this.playerRank = playerRank;
    }

    public void setCoins(int amount) {
        this.coins = amount;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setVanished(boolean vanished) {
        this.vanished = vanished;
    }

    public void setCurrentServer(Server currentServer) {
        this.currentServer = currentServer;
    }

    PlayerData(){}
}