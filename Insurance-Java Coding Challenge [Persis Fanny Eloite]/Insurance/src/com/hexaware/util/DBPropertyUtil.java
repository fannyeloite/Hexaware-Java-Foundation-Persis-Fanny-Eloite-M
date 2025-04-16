package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getPropertyString(String filename) {
        Properties props = new Properties();
        String connStr = "";

        try {
            FileInputStream fis = new FileInputStream("src/" + filename);
            props.load(fis);

            String host = props.getProperty("hostname");
            String port = props.getProperty("port");
            String db = props.getProperty("dbname");
            String user = props.getProperty("username");
            String pass = props.getProperty("password");

            connStr = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + pass;

        } catch (IOException e) {
            System.out.println("Error reading property file: " + e.getMessage());
        }

        return connStr;
    }
}
