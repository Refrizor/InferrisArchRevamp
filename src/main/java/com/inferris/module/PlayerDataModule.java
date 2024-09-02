package com.inferris.module;

import com.google.inject.AbstractModule;
import com.inferris.api.PlayerDataApiClient;
import com.inferris.api.PlayerDataApi;
import com.inferris.cache.PlayerDataCache;
import com.inferris.service.PlayerDataService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PlayerDataModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PlayerDataApi.class).toInstance(createRetrofit().create(PlayerDataApi.class));
        bind(PlayerDataApiClient.class).asEagerSingleton();
        bind(PlayerDataCache.class).asEagerSingleton();
        bind(PlayerDataService.class).asEagerSingleton();
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:3000")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}