package com.inferris.events.redis.dispatching;

import com.google.inject.Inject;
import com.inferris.service.PlayerDataService;

import java.util.HashMap;
import java.util.Map;

public class JedisEventDispatcher {
    private final Map<String, JedisEventHandler> eventHandlers = new HashMap<>();
    private final PlayerDataService playerDataService;

    @Inject
    public JedisEventDispatcher(PlayerDataService playerDataService) {
        this.playerDataService = playerDataService;
    }


    public void registerHandler(String channel, JedisEventHandler handler) {
        eventHandlers.put(channel, handler);
    }

    public void dispatch(String channel, String message, String senderId) {
        JedisEventHandler handler = eventHandlers.get(channel);
        if (handler != null) {
            handler.handle(playerDataService, message, senderId);
        } else {
            System.err.println("No handler registered for channel: " + channel);
        }
    }
}