package com.inferris;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.inferris.api.PlayerDataApiClient;
import com.inferris.commands.CommandAPI;
import com.inferris.cache.PlayerDataCache;
import com.inferris.events.EventJoin;
import com.inferris.module.PlayerDataModule;
import com.inferris.service.PlayerDataService;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Inferris extends Plugin {
    private static Inferris instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Inferris plugin is enabled!");

        Injector injector = Guice.createInjector(new PlayerDataModule());
        PlayerDataService playerDataService = injector.getInstance(PlayerDataService.class);
        PlayerDataApiClient playerDataApiClient = injector.getInstance(PlayerDataApiClient.class);
        PlayerDataCache playerDataCache = injector.getInstance(PlayerDataCache.class);

        PluginManager pluginManager = this.getProxy().getPluginManager();
        pluginManager.registerCommand(this, new CommandAPI("api", playerDataService, playerDataCache));
        pluginManager.registerListener(this, new EventJoin(playerDataService));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Inferris getInstance() {
        return instance;
    }
}
