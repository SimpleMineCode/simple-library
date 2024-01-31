package com.simpleminecode.api.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand extends SimpleCommand {
    @Override
    public boolean supportForConsole() {
        return false;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof final Player player)) {
            sender.sendMessage("§cOnly players can execute this command.");
            return;
        }

        this.execute(player, args);
    }

    public abstract void execute(Player player, String[] args);
}
