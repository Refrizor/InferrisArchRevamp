package com.inferris;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.inferris.api.PlayerDataApiClient;
import com.inferris.commands.CommandAPI;
import com.inferris.cache.PlayerDataCache;
import com.inferris.events.EventJoin;
import com.inferris.module.PlayerDataModule;
import com.inferris.service.PlayerDataService;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;

import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(id = "inferris", name = "Inferris", version = "1.0.0-alpha",
        url = "https://example.org", description = "I did it!", authors = {"Me"})
public class Inferris {
    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDirectory;
    private static Inferris instance;

    @Inject
    public Inferris(ProxyServer server, Logger logger,  @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
        instance = this;

        logger.info("Inferris plugin is enabled!");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        // Do some operation demanding access to the Velocity API here.
        // For instance, we could register an event:

        Injector injector = Guice.createInjector(new PlayerDataModule());
        PlayerDataService playerDataService = injector.getInstance(PlayerDataService.class);
        PlayerDataApiClient playerDataApiClient = injector.getInstance(PlayerDataApiClient.class);
        PlayerDataCache playerDataCache = injector.getInstance(PlayerDataCache.class);

        CommandManager commandManager = server.getCommandManager();
        CommandMeta commandMeta = commandManager.metaBuilder("api").plugin(this).build();
        commandManager.register(commandMeta, new CommandAPI(playerDataService, playerDataCache));

        server.getEventManager().register(this, new EventJoin(playerDataService, server));
    }

    public Logger getLogger() {
        return logger;
    }

    public ProxyServer getServer() {
        return server;
    }

    public static Inferris getInstance() {
        return instance;
    }
}
