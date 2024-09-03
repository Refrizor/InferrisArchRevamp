package com.inferris.api;

public class ApiResponse<T> {
    private boolean success;
    private T player;

    public T getPlayer() {
        return player;
    }

    public boolean isSuccess() {
        return success;
    }

    // Getters and setters...
}