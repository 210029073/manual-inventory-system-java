package com.manual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManualDatabaseConnection {
    private final String URLConnection;
    private final String username;
    private final String password;
    private static ManualDatabaseConnection instance;
    private ManualDatabaseConnection() {
        URLConnection = "jdbc:mysql://localhost:3306/manual_my";
        username = "root";
        password = "";
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URLConnection, username, password);
        }

        catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }

        return null;
    }

    public static ManualDatabaseConnection getInstance() {
        if(instance == null) {
            instance = new ManualDatabaseConnection();
        }

        return instance;
    }
}
