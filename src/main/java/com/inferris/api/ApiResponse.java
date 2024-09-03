package com.inferris.api;

public record ApiResponse<T>(boolean success, T player) {
}