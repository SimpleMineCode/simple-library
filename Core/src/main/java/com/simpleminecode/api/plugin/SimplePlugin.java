package com.simpleminecode.api.plugin;

import com.simpleminecode.api.configuration.PluginInformation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class SimplePlugin extends JavaPlugin {
    private boolean validLicense = false;
    private boolean isPremium = false;
    private PluginInformation information;

    @Override
    public final void onLoad() {
        if (this instanceof final Verifiable verifiable) {
            this.validLicense = verifiable.verify(this);
            this.isPremium = true;
        }

        if (isPremium && !validLicense)
            return;

        this.loading();
    }

    @Override
    public final void onEnable() {
        if (isPremium && !validLicense) {
            getLogger().severe("Could not validate license for this plugin. Shutting down...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.information = new PluginInformation(this);
        this.starting();
        this.printBannerToConsole();
    }

    @Override
    public final void onDisable() {
        this.stopping();
    }

    private void printBannerToConsole() {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§7This plugin uses §9SimpleLibrary §7v" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§7Download: §9https://SimpleMineCode.com");
        Bukkit.getConsoleSender().sendMessage("");
    }

    protected void loading() {
    }

    protected void starting() {
    }

    protected void stopping() {
    }

    public PluginInformation getInformation() {
        return information;
    }
}
