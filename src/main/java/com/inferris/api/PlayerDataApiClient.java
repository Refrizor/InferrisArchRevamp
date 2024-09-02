package com.inferris.api;

import com.google.inject.Inject;
import com.inferris.model.Channel;
import com.inferris.model.PlayerData;
import com.inferris.model.Profile;
import com.inferris.model.Rank;
import com.inferris.model.Server;
import retrofit2.Response;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class PlayerDataApiClient {
    private final PlayerDataApi playerDataApi;

    @Inject
    public PlayerDataApiClient(PlayerDataApi playerDataApi) {
        this.playerDataApi = playerDataApi;
    }

    public Optional<PlayerData> fetchPlayerData(UUID uuid) {
        try {
            // Perform the GET request to fetch player data from the API
            Response<PlayerData> response = playerDataApi.getPlayerData(uuid).execute();

            if (response.isSuccessful()) {
                // Return the fetched player data wrapped in an Optional
                return Optional.ofNullable(response.body());
            } else if (response.code() == 404) {
                // If the player data is not found, return an empty Optional
                return Optional.empty();
            } else {
                throw new RuntimeException("Failed to fetch player data: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch player data", e);
        }
    }

    public PlayerData createPlayerData(UUID uuid, String username) {
        long joinDate = Instant.now().getEpochSecond();

        PlayerData newPlayerData = new PlayerData(uuid, username, new Rank(0, 0, 0, 0),
                new Profile(joinDate, null, null, 0, false, false), 0, Channel.NONE, false, Server.LOBBY);

        try {
            Response<Void> response = playerDataApi.createPlayerData(newPlayerData).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to create player data: " + response.code());
            }
            return newPlayerData;
        } catch (IOException e) {
            throw new RuntimeException("Failed to create player data", e);
        }
    }

    public void updatePlayerData(UUID uuid, PlayerData playerData) {
        try {
            // Perform the PUT request to update existing player data in the API
            Response<Void> response = playerDataApi.updatePlayerData(uuid, playerData).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to update player data: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to update player data", e);
        }
    }

    // Other methods for specific API interactions could be added here, e.g., /branch/1/ranks/1
}