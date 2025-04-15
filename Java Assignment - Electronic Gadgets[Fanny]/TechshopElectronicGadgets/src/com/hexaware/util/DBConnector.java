package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class DBConnector {
	    private static final String URL = "jdbc:mysql://localhost:3306/techshop";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Fanny@1623"; // replace with your actual password

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	}



