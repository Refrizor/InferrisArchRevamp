package com.inferris.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class PlayerData implements Serializable {
    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("username")
    private String username;

    @JsonProperty("rank")
    private Rank rank;

    @JsonProperty("profile")
    private Profile profile;

    @JsonProperty("coins")
    private int coins;

    @JsonProperty("channel")
    private Channel channel;

    @JsonProperty("isVanished")
    private boolean isVanished;

    @JsonProperty("currentServer")
    private Server currentServer;

    @JsonProperty("userPreferences")
    private UserPreferences userPreferences;

    public PlayerData(UUID uuid, String username, Rank rank, Profile profile, int coins, Channel channel, boolean isVanished, Server currentServer, UserPreferences userPreferences) {
        this.uuid = uuid;
        this.username = username;
        this.rank = rank;
        this.profile = profile;
        this.coins = coins;
        this.channel = channel;
        this.isVanished = isVanished;
        this.currentServer = currentServer;
        this.userPreferences = userPreferences;
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
        return isVanished;
    }

    public Server getCurrentServer() {
        return currentServer;
    }

    public UserPreferences getUserPreferences() {
        return userPreferences;
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

    public void setVanished(boolean isVanished) {
        this.isVanished = isVanished;
    }

    public void setCurrentServer(Server currentServer) {
        this.currentServer = currentServer;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    PlayerData(){}
}