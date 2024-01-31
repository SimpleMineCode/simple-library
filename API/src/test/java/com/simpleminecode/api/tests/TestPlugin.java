package com.simpleminecode.api.tests;

import com.simpleminecode.api.plugin.SimplePlugin;

public class TestPlugin extends SimplePlugin {
    @Override
    protected void starting() {
        getLogger().info("Starting " + getClass().getName());
    }
}
