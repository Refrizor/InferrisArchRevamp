package com.inferris;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inferris.api.PlayerDataApiClient;
import com.inferris.model.PlayerData;
import com.inferris.cache.PlayerDataCache;
import com.inferris.service.PlayerDataService;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class CommandAPI extends Command {

    private final PlayerDataService playerDataService;
    private final PlayerDataCache playerDataCache;
    private final PlayerDataApiClient client;
    private final UUID uuid = UUID.fromString("7d16b15d-bb22-4a6d-80db-6213b3d75007");

    public CommandAPI(String name, PlayerDataService playerDataService, PlayerDataCache playerDataCache, PlayerDataApiClient client) {
        super(name);
        this.playerDataService = playerDataService;
        this.playerDataCache = playerDataCache;
        this.client = client;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        int length = args.length;

        if(length == 1){
            switch (args[0].toLowerCase()){
                case "clear" -> {
                    playerDataCache.invalidate(playerDataService.getPlayerData(uuid));
                }
                case "get" -> {
                    try {
                        String message = SerializationUtils.serializePlayerData(playerDataService.getPlayerData(uuid));
                        sender.sendMessage(message);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "post" -> {
                    playerDataService.getPlayerData(UUID.fromString("7d16b15d-bb22-4a6d-80db-6213b3d75007"));
                }
                case "update" -> {
                    playerDataService.updatePlayerData(uuid, playerData -> {
                        playerData.getRank().setStaff(3);
                        playerData.getRank().setStaff(3);
                    });
                }
            }
        }

        if(length > 1){
            switch (args[0].toLowerCase()){
                case "updateclient" -> {
                    try {
                        int staffValue = Integer.parseInt(args[1]);
                        PlayerData playerData = playerDataService.getPlayerData(uuid);
                        client.updatePlayerData(uuid, playerData);
                        sender.sendMessage(new TextComponent("Updated staff to: " + staffValue));
                    } catch (NumberFormatException e) {
                        sender.sendMessage(new TextComponent("Invalid staff value: " + args[1]));
                    }
                }

                case "updateservice" -> {
                    try {
                        int staffValue = Integer.parseInt(args[1]);

                        playerDataService.updatePlayerData(uuid, playerData2 -> {
                            playerData2.getRank().setStaff(staffValue);
                        });
                        sender.sendMessage(new TextComponent("Updated staff to: " + staffValue));
                    } catch (NumberFormatException e) {
                        sender.sendMessage(new TextComponent("Invalid staff value: " + args[1]));
                    }
                }
            }
        }
    }
}