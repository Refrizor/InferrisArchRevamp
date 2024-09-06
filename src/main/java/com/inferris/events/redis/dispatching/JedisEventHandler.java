package com.inferris.events.redis.dispatching;

public interface JedisEventHandler {
    void handle(com.inferris.service.PlayerDataService playerDataService, String message, String senderId);
}