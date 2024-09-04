package com.inferris.service;

import com.google.inject.Inject;
import com.inferris.api.PlayerDataApiClient;
import com.inferris.cache.PlayerDataCache;
import com.inferris.exception.PlayerDataDeleteException;
import com.inferris.exception.PlayerDataNotFoundException;
import com.inferris.exception.PlayerDataUpdateException;
import com.inferris.model.rank.SupporterRank;
import com.inferris.model.PlayerData;
import com.inferris.model.rank.StaffRank;

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
                .orElseGet(() -> {
                    CompletableFuture<Optional<PlayerData>> fetchFuture = apiClient.fetchPlayerDataAsync(uuid);

                    if (fetchFuture == null) {
                        // If fetchPlayerDataAsync returns null, proceed to create new player data
                        return apiClient.createPlayerDataAsync(uuid, "RobbityBob")
                                .thenApply(newPlayerData -> {
                                    playerDataCache.put(newPlayerData);
                                    return newPlayerData;
                                });
                    }

                    return fetchFuture.thenCompose(optionalPlayerData -> optionalPlayerData
                            .map(CompletableFuture::completedFuture)
                            .orElseGet(() -> apiClient.createPlayerDataAsync(uuid, "RobbityBob")
                                    .thenApply(newPlayerData -> {
                                        playerDataCache.put(newPlayerData);
                                        return newPlayerData;
                                    })));
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
                    throw new PlayerDataUpdateException(uuid, ex.getCause());
                });
    }

    public CompletableFuture<Void> deletePlayerDataAsync(UUID uuid, Consumer<PlayerData> updater) {
        return getPlayerDataAsync(uuid)
                .thenCompose(playerData -> {
                    // Apply the lambda function to update the PlayerData object
                    updater.accept(playerData);
                    playerDataCache.put(playerData);
                    return apiClient.deletePlayerDataAsync(uuid);
                }).exceptionally(ex -> {
                    throw new PlayerDataDeleteException(uuid, ex.getCause());
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

    public void deletePlayerData(UUID uuid, Consumer<PlayerData> updater) {
        try {
            // Block and wait for the asynchronous operation to complete
            deletePlayerDataAsync(uuid, updater).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new PlayerDataUpdateException(uuid, e);
        }
    }

    public String getHighestRankDisplayTag(UUID uuid) {
        PlayerData playerData = this.getPlayerData(uuid);
        SupporterRank supporterRank = playerData.getRank().getPackageRank();
        StaffRank staffRank = playerData.getRank().getPlayerRank();
        // If PlayerRank is higher than NONE, return PlayerRank's display tag
        if (staffRank.getId() > StaffRank.NONE.getId() && staffRank.getDisplayTag() != null) {
            return staffRank.getDisplayTag();
        }

        // Otherwise, return PackageRank's display tag (if it exists)
        if (supporterRank.getDisplayTag() != null) {
            return supporterRank.getDisplayTag();
        }

        // Default if no ranks are available
        return "";
    }

    public boolean hasRank(UUID uuid){
        PlayerData playerData = this.getPlayerData(uuid);
        SupporterRank supporterRank = playerData.getRank().getPackageRank();
        StaffRank staffRank = playerData.getRank().getPlayerRank();
        return supporterRank.getId() > 0 || staffRank.getId() > 0;
    }
}