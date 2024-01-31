package com.simpleminecode.api.tests;

import com.simpleminecode.api.command.PlayerCommand;
import org.bukkit.entity.Player;

public class PlayerCommandTest extends PlayerCommand {
    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage("You are most definitely a player");
    }
}
