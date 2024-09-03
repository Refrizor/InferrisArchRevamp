package com.inferris.exception;

import java.util.UUID;

public class PlayerDataDeleteException extends RuntimeException {
    public PlayerDataDeleteException(UUID uuid, Throwable cause) {
        super("Player data of " + uuid + " failed to delete: " + cause);
    }
}