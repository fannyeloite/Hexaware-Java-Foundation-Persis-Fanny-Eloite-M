package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DBConnUtil {
	
	private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                String connStr = DBPropertyUtil.getPropertyString("db.properties");
                conn = DriverManager.getConnection(connStr);
            }
        } catch (SQLException e) {
            System.out.println("DB Connection error: " + e.getMessage());
        }

        return conn;
    }

}
