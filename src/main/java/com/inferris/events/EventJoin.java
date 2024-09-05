package com.inferris.events;

import com.inferris.model.PlayerData;
import com.inferris.service.PlayerDataService;
import com.inferris.utils.DisplayTag;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Collection;
import java.util.UUID;

public class EventJoin  {
    private final PlayerDataService playerDataService;
    private final ProxyServer server;

    public EventJoin(PlayerDataService playerDataService, ProxyServer server) {
        this.playerDataService = playerDataService;
        this.server = server;

    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onSwitch(ServerPreConnectEvent event) {

        Player player = event.getPlayer();
        UUID playerUuid = player.getUniqueId();

        PlayerData playerData = playerDataService.fetchOrCreatePlayerData(player.getUniqueId());

        Collection<Player> players = server.getAllPlayers();
        Audience audience = Audience.audience(players);

        server.sendMessage(Component.text("Someone joined!", NamedTextColor.AQUA));

        DisplayTag displayTag = playerDataService.getHighestRankDisplayTag(playerUuid);

        if (playerDataService.hasRank(playerUuid)) {
            server.sendMessage(displayTag.getDisplayComponent());

            //ProxyServer.getInstance().broadcast(TextComponent.fromLegacy(displayTag + " " + ChatColor.RESET + player.getName() + ChatColor.GRAY + " joined!"));
            return;
        }
        server.sendMessage(Component.text(displayTag + " joined"));

        //ProxyServer.getInstance().broadcast(new TextComponent(player.getName() + ChatColor.GRAY + " joined!"));
    }
}
