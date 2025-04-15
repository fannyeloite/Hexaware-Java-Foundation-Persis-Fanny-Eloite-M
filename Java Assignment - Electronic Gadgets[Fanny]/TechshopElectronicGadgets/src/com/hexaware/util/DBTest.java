package com.hexaware.util;

import java.sql.Connection;

public class DBTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = DBConnUtil.getConnection("resources/db.properties");
        if (conn != null) {
            System.out.println("✅ Database connected successfully!");
        } else {
            System.out.println("❌ Failed to connect to the database.");
        }
    }

}
