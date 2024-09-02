package com.inferris;

import com.inferris.service.PlayerDataService;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class CommandPost extends Command {
    private final PlayerDataService playerDataService;

    public CommandPost(String name, PlayerDataService playerDataService) {
        super(name);
        this.playerDataService = playerDataService;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ProxyServer.getInstance().getLogger().info("Attempting to POST");
        playerDataService.getPlayerData(UUID.fromString("7d16b15d-bb22-4a6d-80db-6213b3d75007"));
    }
}
