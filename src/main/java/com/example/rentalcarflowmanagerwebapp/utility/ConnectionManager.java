package com.example.rentalcarflowmanagerwebapp.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;

    private ConnectionManager() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                final String DB_URL = System.getenv("url");
                final String UID = System.getenv("user");
                final String PWD = System.getenv("pass");
                connection = DriverManager.getConnection(DB_URL, UID, PWD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
