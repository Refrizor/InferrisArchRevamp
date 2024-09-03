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
        // Try to get the player data from the cache
        return CompletableFuture.supplyAsync(() -> playerDataCache.get(uuid))
                .thenCompose(optionalPlayerData -> {
                    if (optionalPlayerData.isPresent()) {
                        return CompletableFuture.completedFuture(optionalPlayerData.get());
                    } else {
                        // If not found in the cache, fetch from the API asynchronously
                        return apiClient.fetchPlayerDataAsync(uuid)
                                .thenApply(fetchedData -> {
                                    if (fetchedData.isPresent()) {
                                        playerDataCache.put(fetchedData.get());
                                        return fetchedData.get();
                                    } else {
                                        throw new RuntimeException("Player data not found for UUID: " + uuid);
                                    }
                                });
                    }
                });
    }

    public CompletableFuture<PlayerData> fetchOrCreatePlayerDataAsync(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> playerDataCache.get(uuid))
                .thenCompose(optionalPlayerData -> {
                    if (optionalPlayerData.isPresent()) {
                        return CompletableFuture.completedFuture(optionalPlayerData.get());
                    } else {
                        // Try to fetch from API, and if not found, create new data
                        return apiClient.fetchPlayerDataAsync(uuid)
                                .thenCompose(fetchedData -> {
                                    if (fetchedData.isPresent()) {
                                        playerDataCache.put(fetchedData.get());
                                        return CompletableFuture.completedFuture(fetchedData.get());
                                    } else {
                                        // Create new player data if it doesn't exist
                                        return apiClient.createPlayerDataAsync(uuid, "RobbityBob") // todo: Replace "RobbityBob" with actual data
                                                .thenApply(newPlayerData -> {
                                                    playerDataCache.put(newPlayerData);
                                                    return newPlayerData;
                                                });
                                    }
                                });
                    }
                });
    }

    public CompletableFuture<Void> updatePlayerDataAsync(UUID uuid, Consumer<PlayerData> updater) {
        return getPlayerDataAsync(uuid)
                .thenCompose(playerData -> {
                    // Apply the lambda function to update the PlayerData object
                    updater.accept(playerData);
                    playerDataCache.put(playerData);
                    return apiClient.updatePlayerDataAsync(uuid, playerData);
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

    public void updatePlayerData(UUID uuid, Consumer<PlayerData> updater) {
        try {
            // Block and wait for the asynchronous operation to complete
            updatePlayerDataAsync(uuid, updater).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to update player data", e);
        }
    }
}