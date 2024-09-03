package com.inferris.service;

import com.google.inject.Inject;
import com.inferris.api.PlayerDataApiClient;
import com.inferris.cache.PlayerDataCache;
import com.inferris.exception.PlayerDataNotFoundException;
import com.inferris.exception.PlayerDataUpdateException;
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
        return playerDataCache.get(uuid)
                .map(CompletableFuture::completedFuture)
                .orElseGet(() -> apiClient.fetchPlayerDataAsync(uuid)
                        .thenApply(optionalPlayerData -> optionalPlayerData
                                .map(fetchedData -> {
                                    playerDataCache.put(fetchedData); // Cache the fetched data
                                    return fetchedData;
                                })
                                .orElseThrow(() -> new PlayerDataNotFoundException(uuid))));
    }

    public CompletableFuture<PlayerData> fetchOrCreatePlayerDataAsync(UUID uuid) {
        return playerDataCache.get(uuid)
                .map(CompletableFuture::completedFuture)
                .orElseGet(() -> apiClient.fetchPlayerDataAsync(uuid)
                        .thenCompose(optionalPlayerData -> optionalPlayerData
                                .map(CompletableFuture::completedFuture)
                                .orElseGet(() -> apiClient.createPlayerDataAsync(uuid, "RobbityBob") // todo: Replace "RobbityBob" with actual data
                                        .thenApply(newPlayerData -> {
                                            playerDataCache.put(newPlayerData);
                                            return newPlayerData;
                                        }))));
    }

    public CompletableFuture<Void> updatePlayerDataAsync(UUID uuid, Consumer<PlayerData> updater) {
        return getPlayerDataAsync(uuid)
                .thenCompose(playerData -> {
                    // Apply the lambda function to update the PlayerData object
                    updater.accept(playerData);
                    playerDataCache.put(playerData);
                    return apiClient.updatePlayerDataAsync(uuid, playerData);
                }).exceptionally(ex -> {
                    throw new PlayerDataUpdateException(uuid, ex.getCause());
                });
    }

    // Synchronous wrapper method
    public PlayerData getPlayerData(UUID uuid) {
        try {
            // Block and wait for the asynchronous operation to complete
            return getPlayerDataAsync(uuid).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new PlayerDataNotFoundException(uuid);
        }
    }

    public PlayerData fetchOrCreatePlayerData(UUID uuid) {
        try {
            // Block and wait for the asynchronous operation to complete
            return fetchOrCreatePlayerDataAsync(uuid).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new PlayerDataNotFoundException(uuid);
        }
    }

    public void updatePlayerData(UUID uuid, Consumer<PlayerData> updater) {
        try {
            // Block and wait for the asynchronous operation to complete
            updatePlayerDataAsync(uuid, updater).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new PlayerDataUpdateException(uuid, e);
        }
    }
}