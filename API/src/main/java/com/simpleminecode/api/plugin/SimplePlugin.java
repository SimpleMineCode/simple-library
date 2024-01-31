package com.simpleminecode.api.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class SimplePlugin extends JavaPlugin {
    private boolean validLicense = false;
    private boolean isPremium = false;

    @Override
    public final void onLoad() {
        this.loading();

        if (this instanceof final Verifiable verifiable) {
            this.validLicense = verifiable.verify(this);
            this.isPremium = true;
        }
    }

    @Override
    public final void onEnable() {
        if (isPremium && !validLicense) {
            getLogger().severe("Could not validate license for this plugin. Shutting down...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.starting();
    }

    @Override
    public final void onDisable() {
        this.stopping();
    }

    protected void loading() {}
    protected void starting() {}
    protected void stopping() {}
}
