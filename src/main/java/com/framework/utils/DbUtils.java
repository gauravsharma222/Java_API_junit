package com.framework.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    public static Connection getDB2Connection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
