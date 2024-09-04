package com.inferris.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inferris.model.rank.DonorRank;
import com.inferris.model.PlayerData;
import com.inferris.model.rank.StaffRank;
import com.inferris.utils.SerializationUtils;
import com.inferris.cache.PlayerDataCache;
import com.inferris.service.PlayerDataService;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class CommandAPI extends Command {

    private static final UUID DEBUG_UUID = UUID.fromString("7d16b15d-bb22-4a6d-80db-6213b3d75007");
    private final PlayerDataService playerDataService;
    private final PlayerDataCache playerDataCache;

    public CommandAPI(String name, PlayerDataService playerDataService, PlayerDataCache playerDataCache) {
        super(name);
        this.playerDataService = playerDataService;
        this.playerDataCache = playerDataCache;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        int length = args.length;

        if (length == 1) {
            switch (args[0].toLowerCase()) {
                case "clear" -> {
                    playerDataCache.invalidate(playerDataService.getPlayerData(DEBUG_UUID));
                }
                case "gen" -> {
                    playerDataService.fetchOrCreatePlayerData(DEBUG_UUID);
                }
                case "get" -> {
                    try {
                        String message = SerializationUtils.serializePlayerData(playerDataService.getPlayerData(DEBUG_UUID));
                        sender.sendMessage(message);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "post" -> {
                    playerDataService.fetchOrCreatePlayerDataAsync(UUID.fromString("7d16b15d-bb22-4a6d-80db-6213b3d75007"));
                }
                case "update" -> {
                    playerDataService.updatePlayerData(DEBUG_UUID, playerData -> {
                        playerData.getRank().setStaff(0);
                    });
                }
            }
        }

        if (length > 1) {
            UUID uuid = UUID.fromString(args[1]);
            String rankArg = args[2];
            switch (args[0].toLowerCase()) {
                case "updaterank" -> {
                    try {
                        playerDataService.updatePlayerData(uuid, playerData2 -> {
                            playerData2.getRank().setStaff(Integer.parseInt(args[2]));
                        });
                        sender.sendMessage(new TextComponent("Updated rank to: " + rankArg));
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(new TextComponent("Invalid staff value: " + rankArg));
                    }
                }
                case "updatepackagerank" -> {
                    try {
                        playerDataService.updatePlayerData(uuid, playerData2 -> {
                            playerData2.getRank().setDonor(Integer.parseInt(args[2]));
                        });
                        sender.sendMessage(new TextComponent("Updated rank to: " + DonorRank.valueOf(args[2])));
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(new TextComponent("Invalid staff value: " + args[2]));
                    }
                }
                case "get" -> {
                    PlayerData playerData = playerDataService.getPlayerData(uuid);
                    try {
                        sender.sendMessage(new TextComponent(SerializationUtils.serializePlayerData(playerData)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }

                case "delete" -> {
                    playerDataService.deletePlayerData(uuid, playerData -> {});
                }
            }
        }
    }
}