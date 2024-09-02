package com.inferris;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inferris.model.PlayerData;
import com.inferris.service.PlayerDataService;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class CommandGet extends Command {
    private final PlayerDataService playerDataService;
    public CommandGet(String name, PlayerDataService playerDataService) {
        super(name);
        this.playerDataService = playerDataService;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        PlayerData playerData = playerDataService.getPlayerData(UUID.fromString("7d16b15d-bb22-4a6d-80db-6213b3d75007"));
        try {
            String message = SerializationUtils.serializePlayerData(playerData);
            commandSender.sendMessage(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
