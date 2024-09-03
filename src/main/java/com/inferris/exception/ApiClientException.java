package com.inferris.exception;

public class ApiClientException extends RuntimeException {
    public ApiClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
