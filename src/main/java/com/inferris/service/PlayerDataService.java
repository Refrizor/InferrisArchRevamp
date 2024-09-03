package com.inferris.service;

import com.google.inject.Inject;
import com.inferris.api.PlayerDataApiClient;
import com.inferris.cache.PlayerDataCache;
import com.inferris.model.PlayerData;

import java.util.Optional;
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
            // Try to get the player data from the cache
            PlayerData playerData = playerDataCache.get(uuid).orElse(null);

            if (playerData == null) {
                // If not found in the cache, fetch from the API (synchronously within the async context)
                Optional<PlayerData> fetchedData = apiClient.fetchPlayerData(uuid);

                if (fetchedData.isPresent()) {
                    playerData = fetchedData.get();
                    // Cache the fetched player data for future requests
                    playerDataCache.put(playerData);
                } else {
                    // Handle the case where the player data is not found in the API
                    throw new RuntimeException("Player data not found for UUID: " + uuid);
                }
            }
            return playerData;
        });
    }

    public CompletableFuture<PlayerData> fetchOrCreatePlayerDataAsync(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> playerDataCache.get(uuid)
                .orElseGet(() -> {
                    // todo: Replace "Test" with actual data
                    return apiClient.fetchPlayerData(uuid).orElseGet(() -> {
                        PlayerData newPlayerData = apiClient.createPlayerData(uuid, "RobbityBob"); // todo: Replace "Test" with actual data
                        playerDataCache.put(newPlayerData);
                        return newPlayerData;
                    });
                }));
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

    public PlayerData fetchOrCreatePlayerData(UUID uuid) {
        try {
            // Block and wait for the asynchronous operation to complete
            return fetchOrCreatePlayerDataAsync(uuid).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to fetch player data", e);
        }
    }
}