package com.inferris.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPreferences {

    @JsonProperty("isWelcomeMessageEnabled")
    private boolean isWelcomeMessageEnabled;

    @JsonProperty("isAutoVanishEnabled")
    private boolean isAutoVanishEnabled;

    @JsonProperty("isSurpriseModeEnabled")
    private boolean isSurpriseModeEnabled;

    // Constructor
    public UserPreferences(@JsonProperty("isWelcomeMessageEnabled") boolean isWelcomeMessageEnabled,
                           @JsonProperty("isAutoVanishEnabled") boolean isAutoVanishEnabled,
                           @JsonProperty("isSurpriseModeEnabled") boolean isSurpriseModeEnabled) {
        this.isWelcomeMessageEnabled = isWelcomeMessageEnabled;
        this.isAutoVanishEnabled = isAutoVanishEnabled;
        this.isSurpriseModeEnabled = isSurpriseModeEnabled;
    }

    // Getters
    @JsonProperty("isWelcomeMessageEnabled")
    public boolean isWelcomeMessageEnabled() {
        return isWelcomeMessageEnabled;
    }

    @JsonProperty("isAutoVanishEnabled")
    public boolean isAutoVanishEnabled() {
        return isAutoVanishEnabled;
    }

    @JsonProperty("isSurpriseModeEnabled")
    public boolean isSurpriseModeEnabled() {
        return isSurpriseModeEnabled;
    }

    // Setters
    public void setWelcomeMessageEnabled(boolean isWelcomeMessageEnabled) {
        this.isWelcomeMessageEnabled = isWelcomeMessageEnabled;
    }

    public void setAutoVanishEnabled(boolean isAutoVanishEnabled) {
        this.isAutoVanishEnabled = isAutoVanishEnabled;
    }

    public void setSurpriseModeEnabled(boolean isSurpriseModeEnabled) {
        this.isSurpriseModeEnabled = isSurpriseModeEnabled;
    }

    // Empty constructor for Jackson deserialization
    public UserPreferences() {
    }
}

