package com.simpleminecode.api.command;

import org.bukkit.command.CommandSender;

public interface Command {
    boolean supportForConsole();

    void execute(CommandSender sender, String[] args);
}
