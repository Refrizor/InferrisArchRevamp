package com.inferris.model;

public class UserPreferences {

    private boolean sendWelcomeMessage;
    private boolean vanishOnJoin;

    // Constructor
    public UserPreferences(boolean sendWelcomeMessage, boolean vanishOnJoin) {
        this.sendWelcomeMessage = sendWelcomeMessage;
        this.vanishOnJoin = vanishOnJoin;
    }

    // Getters
    public boolean shouldSendWelcomeMessage() {
        return sendWelcomeMessage;
    }

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
}