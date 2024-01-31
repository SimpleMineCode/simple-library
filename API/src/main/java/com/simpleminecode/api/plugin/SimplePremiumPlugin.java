package com.simpleminecode.api.plugin;

public class SimplePremiumPlugin extends PremiumPlugin {
    @Override
    public boolean verify(SimplePlugin plugin) {
        plugin.getLogger().severe("This kind of premium plugin licensing check is not supported yet.");
        return false;
    }
}
