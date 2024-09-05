package com.inferris.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPreferences {

    @JsonProperty("sendWelcomeMessage")
    private boolean sendWelcomeMessage;

    @JsonProperty("vanishOnJoin")
    private boolean vanishOnJoin;

    // Constructor
    public UserPreferences(@JsonProperty("sendWelcomeMessage") boolean sendWelcomeMessage,
                           @JsonProperty("vanishOnJoin") boolean vanishOnJoin) {
        this.sendWelcomeMessage = sendWelcomeMessage;
        this.vanishOnJoin = vanishOnJoin;
    }

    // Getters
    @JsonProperty("sendWelcomeMessage")
    public boolean shouldSendWelcomeMessage() {
        return sendWelcomeMessage;
    }

    @JsonProperty("vanishOnJoin")
    public boolean shouldVanishOnJoin() {
        return vanishOnJoin;
    }

    // Setters
    public void setSendWelcomeMessage(boolean sendWelcomeMessage) {
        this.sendWelcomeMessage = sendWelcomeMessage;
    }

    public void setVanishOnJoin(boolean vanishOnJoin) {
        this.vanishOnJoin = vanishOnJoin;
    }

    // Empty constructor for Jackson deserialization
    public UserPreferences() {}
}
