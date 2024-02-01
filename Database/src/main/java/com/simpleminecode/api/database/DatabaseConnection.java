package com.simpleminecode.api.database;


import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

public class DatabaseConnection {
    private final String dsn;
    private String username;
    private String password;

    private boolean sqlite = false;

    public DatabaseConnection(JavaPlugin plugin, String username, String password, String host, String databaseName) {
        this(plugin, username, password, host, databaseName, false);
    }

    public DatabaseConnection(JavaPlugin plugin, String filePath) {
        this.dsn = "jdbc:sqlite:" + filePath;
        this.sqlite = true;

        plugin.getLogger().info("Created SQLite instance with link to '%s'".formatted(filePath));
    }

    public DatabaseConnection(JavaPlugin plugin, String username, String password, String host, String databaseName, boolean createDatabase) {
        if (createDatabase) {
            final DatabaseConnection tempDatabaseConnection = new DatabaseConnection(plugin, username, password, host, "");
            final String sql = "CREATE DATABASE IF NOT EXISTS ?;";

            try (Connection connection = tempDatabaseConnection.getConnection();
                 PreparedStatement statement = tempDatabaseConnection.prepareStatement(connection, sql)) {
                statement.setString(1, databaseName);
                statement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        this.dsn = "jdbc:DatabaseConnection://" + host + ":3306/" + databaseName;
        this.username = username;
        this.password = password;

        plugin.getLogger().info("Created DatabaseConnection instance with username '%s'".formatted(username));
    }

    public Statement createStatement(final Connection connection) throws SQLException {
        if (!sqlite)
            return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        return connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public PreparedStatement prepareStatement(final Connection connection, final String sql) throws SQLException {
        if (!sqlite)
            return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        return connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dsn, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}