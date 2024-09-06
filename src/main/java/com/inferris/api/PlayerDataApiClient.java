package com.inferris.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import com.inferris.Inferris;
import com.inferris.exception.ApiClientException;
import com.inferris.exception.PlayerDataDeleteException;
import com.inferris.exception.PlayerDataUpdateException;
import com.inferris.model.*;
import com.inferris.utils.SerializationUtils;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerDataApiClient {
    private final PlayerDataApi playerDataApi;

    @Inject
    public PlayerDataApiClient(PlayerDataApi playerDataApi) {
        this.playerDataApi = playerDataApi;
    }

    public CompletableFuture<Optional<PlayerData>> fetchPlayerDataAsync(UUID uuid) {
        CompletableFuture<Optional<PlayerData>> future = new CompletableFuture<>();

        playerDataApi.getPlayerData(uuid).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<ApiResponse<PlayerData>> call, @NotNull Response<ApiResponse<PlayerData>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<PlayerData> apiResponse = response.body();
                    if (apiResponse != null && apiResponse.success()) {
                        future.complete(Optional.ofNullable(apiResponse.player()));
                    } else {
                        future.complete(Optional.empty());
                    }
                } else if (response.code() == 404) {
                    Inferris.getInstance().getLogger().info("Player data 404 not found for UUID: " + uuid);
                    future.complete(Optional.empty());
                } else {
                    future.completeExceptionally(new ApiClientException("Failed to fetch player data: " + response.code(), null));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ApiResponse<PlayerData>> call, @NotNull Throwable t) {
                future.completeExceptionally(new ApiClientException("Failed to fetch player data for UUID: " + uuid, t));
            }
        });

        return future;
    }

    public CompletableFuture<PlayerData> createPlayerDataAsync(UUID uuid, String username) {
        PlayerData newPlayerData = new PlayerData(uuid, username, PlayerDefault.DEFAULT_RANK,
                PlayerDefault.DEFAULT_PROFILE,
                PlayerDefault.DEFAULT_COINS, Channel.NONE, false, PlayerDefault.DEFAULT_CURRENT_SERVER,
                PlayerDefault.DEFAULT_USER_PREFERENCES);

        try {
            Inferris.getInstance().getLogger().info(SerializationUtils.serializePlayerData(newPlayerData));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<PlayerData> future = new CompletableFuture<>();

        playerDataApi.createPlayerData(newPlayerData).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.isSuccessful()) {
                    future.complete(newPlayerData);
                } else {
                    future.completeExceptionally(new ApiClientException("Failed to create player data: " + response.code() + response.message(), null));
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                future.completeExceptionally(new ApiClientException("Failed to create player data", t));
            }
        });

        return future;
    }

    public CompletableFuture<Void> updatePlayerDataAsync(UUID uuid, PlayerData playerData) {
        CompletableFuture<Void> future = new CompletableFuture<>();

        playerDataApi.updatePlayerData(uuid, playerData).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.isSuccessful()) {
                    future.complete(null);  // Complete with null as there's no meaningful result to return
                } else {
                    future.completeExceptionally(new PlayerDataUpdateException(uuid, null));
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                future.completeExceptionally(new PlayerDataUpdateException(uuid, t));
            }
        });

        return future;
    }

    public CompletableFuture<Void> deletePlayerDataAsync(UUID uuid) {
        CompletableFuture<Void> future = new CompletableFuture<>();

        playerDataApi.deletePlayerData(uuid).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.isSuccessful()) {
                    future.complete(null);  // Complete with null as there's no meaningful result to return
                } else {
                    future.completeExceptionally(new PlayerDataDeleteException(uuid, null));
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                future.completeExceptionally(new PlayerDataDeleteException(uuid, t));
            }
        });

        return future;
    }

    // Other methods for specific API interactions could be added here, e.g., /branch/1/ranks/1
}