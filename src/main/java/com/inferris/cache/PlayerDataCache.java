package com.inferris.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.inject.Inject;
import com.inferris.model.PlayerData;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PlayerDataCache {
    private final Cache<UUID, PlayerData> cache;

    @Inject
    public PlayerDataCache() {
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();
    }

    public Optional<PlayerData> get(UUID uuid) {
        return Optional.ofNullable(cache.getIfPresent(uuid));
    }

    public void put(PlayerData playerData) {
        cache.put(playerData.getUuid(), playerData);
    }

    public void invalidate(PlayerData playerData){
        cache.invalidate(playerData.getUuid());
    }

    // Other cache-related methods...
}