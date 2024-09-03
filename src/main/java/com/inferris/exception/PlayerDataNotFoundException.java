package com.inferris.exception;

import java.util.UUID;

public class PlayerDataNotFoundException extends RuntimeException {
    public PlayerDataNotFoundException(UUID uuid) {
        super("Player data not found for UUID: " + uuid);
    }
}