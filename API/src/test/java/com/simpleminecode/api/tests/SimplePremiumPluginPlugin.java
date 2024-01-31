package com.simpleminecode.api.tests;

import com.simpleminecode.api.plugin.SimplePremiumPlugin;

public class SimplePremiumPluginPlugin extends SimplePremiumPlugin {
    @Override
    protected void starting() {
        getLogger().info("Starting " + getClass().getName());
    }
}
