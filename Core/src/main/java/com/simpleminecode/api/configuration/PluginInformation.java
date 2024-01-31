package com.simpleminecode.api.configuration;

import com.simpleminecode.api.plugin.SimplePlugin;

import java.io.IOException;
import java.util.Properties;

public class PluginInformation {
    private static final String PROPERTIES_FILE = "plugin.properties";

    private final Properties properties = new Properties();
    private final SimplePlugin plugin;
    private boolean isSafe = true;

    public PluginInformation(SimplePlugin plugin) {
        this.plugin = plugin;

        try {
            this.properties.load(plugin.getResource(PROPERTIES_FILE));
        } catch (IOException e) {
            this.isSafe = false;
            throw new RuntimeException(e);
        }
    }

    public String getMadeFor() {
        return getProperty("made-for");
    }

    public String getProperty(String key) {
        final String value;

        if (!isSafe || (value = this.properties.getProperty(key)) == null) {
            plugin.getLogger().severe("Issues getting property '" + key + "'");
            return null;
        }

        return value;
    }
}
