package com.simpleminecode.api.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public interface Command extends CommandExecutor {
    void execute(CommandSender sender, String[] args);
}
