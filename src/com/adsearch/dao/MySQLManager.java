package com.adsearch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLManager {

    private static final String DB_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String CONNECTION_CONFIG = "?autoReconnect=true&useSSL=false";

    public static Connection getConnection(String dbURL, String dbName, String user, String password) {

        Connection connection = null;

        try {
            Class.forName(DB_DRIVER_CLASS_NAME);
            connection = DriverManager
                    .getConnection(dbURL + "/" + dbName + CONNECTION_CONFIG, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR: Cannot find the MySQL JDBC Driver.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR: Connection Failed! Check output console.");
        }

        return connection;
    }
}
