package com.inferris.exception;

import com.inferris.Inferris;

public class ApiClientException extends RuntimeException {
    public ApiClientException(String message, Throwable cause) {
        super(message, cause);
        Inferris.getInstance().getLogger().severe("API Client Exception: " + message + " - Cause: " + (cause != null ? cause.getMessage() : "No cause"));
    }
}
