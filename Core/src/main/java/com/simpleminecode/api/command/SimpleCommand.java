package com.simpleminecode.api.command;

import org.bukkit.command.CommandSender;

public abstract class SimpleCommand implements Command{
    @Override
    public boolean supportForConsole() {
        return true;
    }
}
