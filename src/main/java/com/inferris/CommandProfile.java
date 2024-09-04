package com.inferris;

import com.google.inject.Inject;
import com.inferris.model.PlayerData;
import com.inferris.service.PlayerDataService;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandProfile extends Command {

    private final PlayerDataService playerDataService;

    @Inject
    public CommandProfile(String name, PlayerDataService playerDataService) {
        super(name);
        this.playerDataService = playerDataService;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer player){
            PlayerData playerData = playerDataService.getPlayerData(player.getUniqueId());

            player.sendMessage(new TextComponent("Highest rank : " + playerDataService.getHighestRankDisplayTag(player.getUniqueId())));
            player.sendMessage(new TextComponent("Staff : " + playerData.getRank().getStaff()));
            player.sendMessage(new TextComponent("Donor : " + playerData.getRank().getDonor()));
        }
    }
}
