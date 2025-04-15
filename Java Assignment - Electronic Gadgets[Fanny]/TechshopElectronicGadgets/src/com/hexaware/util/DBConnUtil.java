package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
	public static Connection getConnection(String propertyFileName) {
        String connStr = DBPropertyUtil.getConnectionString(propertyFileName);  // Fetch from .properties file
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(connStr);  // Establish DB connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
        


