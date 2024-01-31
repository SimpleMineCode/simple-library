package com.simpleminecode.api.command;

public abstract class SimpleCommand implements Command {
    @Override
    public boolean supportForConsole() {
        return true;
    }
}
