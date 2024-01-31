package com.simpleminecode.api.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public interface Command extends CommandExecutor {
    boolean supportForConsole();

    void execute(CommandSender sender, String[] args);

    @Override
    default boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        execute(sender, args);
        return true;
    }
}
