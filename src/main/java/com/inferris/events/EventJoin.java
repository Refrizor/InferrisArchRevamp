package com.inferris.events;

import com.inferris.model.PlayerData;
import com.inferris.service.PlayerDataService;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class EventJoin implements Listener {
    private final PlayerDataService playerDataService;

    public EventJoin(PlayerDataService playerDataService) {
        this.playerDataService = playerDataService;
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onSwitch(ServerSwitchEvent event) {
        ProxiedPlayer player = event.getPlayer();
        UUID playerUuid = player.getUniqueId();

        PlayerData playerData = playerDataService.fetchOrCreatePlayerData(player.getUniqueId());

        if (playerData.getRank().getStaffRank().getId() > 0) {
            ProxyServer.getInstance().broadcast(new TextComponent(ChatColor.AQUA + "A staff joined!"));
        }

        String displayTag = playerDataService.getHighestRankDisplayTag(playerUuid);
        if (playerDataService.hasRank(playerUuid)) {
            ProxyServer.getInstance().broadcast(TextComponent.fromLegacy(displayTag + " " + ChatColor.RESET + player.getName() + ChatColor.GRAY + " joined!"));
            return;
        }
        ProxyServer.getInstance().broadcast(new TextComponent(player.getName() + ChatColor.GRAY + " joined!"));
    }
}
