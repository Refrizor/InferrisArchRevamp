package com.inferris.service;

import com.google.inject.Inject;
import com.inferris.api.PlayerDataApiClient;
import com.inferris.cache.PlayerDataCache;
import com.inferris.model.PlayerData;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class PlayerDataService {
    private final PlayerDataCache playerDataCache;
    private final PlayerDataApiClient apiClient;

    @Inject
    public PlayerDataService(PlayerDataCache playerDataCache, PlayerDataApiClient apiClient) {
        this.playerDataCache = playerDataCache;
        this.apiClient = apiClient;
    }

    public CompletableFuture<PlayerData> getPlayerDataAsync(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            PlayerData playerData = playerDataCache.get(uuid).orElse(null);

            if (playerData == null) {
                playerData = apiClient.fetchPlayerData(uuid).orElseGet(() -> {
                    PlayerData newPlayerData = apiClient.createPlayerData(uuid, "Test");
                    playerDataCache.put(newPlayerData);
                    return newPlayerData;
                });
            }
            return playerData;
        });
    }

    public void updatePlayerData(UUID uuid, Consumer<PlayerData> updater) {
        CompletableFuture.runAsync(() -> {
            PlayerData playerData = playerDataCache.get(uuid).orElse(getPlayerData(uuid));

            // Apply the lambda function to update the PlayerData object
            updater.accept(playerData);
            playerDataCache.put(playerData);
            apiClient.updatePlayerData(uuid, playerData);
        }).exceptionally(ex -> {
            throw new RuntimeException("Failed to update player data", ex);
        });
    }

    // Synchronous wrapper method
    public PlayerData getPlayerData(UUID uuid) {
        try {
            // Block and wait for the asynchronous operation to complete
            return getPlayerDataAsync(uuid).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to fetch player data", e);
        }
    }
}