package com.simpleminecode.api.command;

import org.bukkit.command.CommandSender;

public abstract class SimpleCommand implements Command {
    @Override
    public boolean supportForConsole() {
        return true;
    }

    @Override
    public final boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        this.execute(sender, args);
        return true;
    }
}
