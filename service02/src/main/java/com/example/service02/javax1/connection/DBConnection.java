package com.example.service02.javax1.connection;

import java.sql.*;

public class DBConnection {
    private final String serverName = "UTOP\\UTOP";
    private final String dbName = "UTOP";
    private final String portNumber = "8080";
    private final String instance = "";
    private final String userID = "quan";
    private final String password = "1234567890";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
        if (instance == null || instance.trim().isEmpty())
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }


    public static void main(String[] args) {
        try {
            System.out.println(new DBConnection().getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
