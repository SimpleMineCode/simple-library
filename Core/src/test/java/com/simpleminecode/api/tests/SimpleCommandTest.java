package com.simpleminecode.api.tests;

import com.simpleminecode.api.command.SimpleCommand;
import org.bukkit.command.CommandSender;

public class SimpleCommandTest extends SimpleCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("You could be either the console or a player");
    }
}
