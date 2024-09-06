package com.inferris.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import com.inferris.cache.PlayerDataCache;
import com.inferris.model.PlayerData;
import com.inferris.model.rank.StaffRank;
import com.inferris.model.rank.SupporterRank;
import com.inferris.service.PlayerDataService;
import com.inferris.utils.SerializationUtils;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public class CommandAPI implements SimpleCommand {

    private static final UUID DEBUG_UUID = UUID.fromString("7d16b15d-bb22-4a6d-80db-6213b3d75007");
    private final PlayerDataService playerDataService;
    private final PlayerDataCache playerDataCache;

    @Inject
    public CommandAPI(PlayerDataService playerDataService, PlayerDataCache playerDataCache) {
        this.playerDataService = playerDataService;
        this.playerDataCache = playerDataCache;
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();
        int length = args.length;

        Player sender = (Player) invocation.source();
        sender.sendMessage(Component.text(playerDataCache.getCache().asMap().size()));

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
                        sender.sendMessage(Component.text(message));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "post" -> {
                    playerDataService.fetchOrCreatePlayerDataAsync(UUID.fromString("7d16b15d-bb22-4a6d-80db-6213b3d75007"));
                }
                case "update" -> {
                    playerDataService.updatePlayerData(DEBUG_UUID, playerData -> {
                        playerData.getRank().setStaffRank(StaffRank.ADMIN);
                    });
                }
            }
        }

        if (length > 1) {
            UUID uuid = UUID.fromString(args[1]);
            String rankArg = args[2].toUpperCase();
            switch (args[0].toLowerCase()) {
                case "updatestaff" -> {
                    try {
                        playerDataService.updatePlayerData(uuid, playerData2 -> {
                            playerData2.getRank().setStaffRank(StaffRank.valueOf(rankArg));
                        });
                        sender.sendMessage(Component.text(("Updated rank to: " + rankArg)));
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(Component.text(("Invalid staff value: " + rankArg)));
                    }
                }
                case "updatedonor" -> {
                    try {
                        playerDataService.updatePlayerData(uuid, playerData2 -> {
                            playerData2.getRank().setSupporterRank(SupporterRank.valueOf(args[2].toUpperCase()));
                        });
                        sender.sendMessage(Component.text("Updated donor to: " + SupporterRank.valueOf(args[2])));
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(Component.text(("Invalid donor value: " + args[2])));
                    }
                }
                case "get" -> {
                    PlayerData playerData = playerDataService.getPlayerData(uuid);
                    try {
                        sender.sendMessage(Component.text((SerializationUtils.serializePlayerData(playerData))));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }

                case "delete" -> {
                    playerDataService.deletePlayerData(uuid, playerData -> {
                    });
                }
            }
        }
    }
}
