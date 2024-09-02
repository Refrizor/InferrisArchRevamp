package com.inferris.api;

import com.inferris.model.PlayerData;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.UUID;

public interface PlayerDataApi {
    @GET("/api/players/{uuid}")
    Call<PlayerData> getPlayerData(@Path("uuid") UUID uuid);

    @POST("/api/players")
    Call<Void> createPlayerData(@Body PlayerData playerData);

    @POST("/api/players/{uuid}")
    Call<Void> updatePlayerData(@Path("uuid") UUID uuid, @Body PlayerData playerData);

    // What about specific things? like /branch/1/ranks/1

    // Other API methods...
}
