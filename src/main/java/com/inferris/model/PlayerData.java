package com.inferris.model;

import java.io.Serializable;
import java.util.UUID;

public class PlayerData implements Serializable {
    private UUID uuid;
    private String username;
    private Rank rank;
    private Profile profile;
    private int coins;
    private Channel channel;
    private boolean vanished;
    private Server currentServer;

    public PlayerData(UUID uuid, String username, Rank rank, Profile profile, int coins, Channel channel, boolean vanished, Server currentServer) {
        this.uuid = uuid;
        this.username = username;
        this.rank = rank;
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

    public Rank getRank() {
        return rank;
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