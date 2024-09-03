package com.inferris.exception;

import java.util.UUID;

public class PlayerDataUpdateException extends RuntimeException {
    public PlayerDataUpdateException(UUID uuid, Throwable cause) {
        super("Failed to update player data for UUID: " + uuid, cause);
    }
}