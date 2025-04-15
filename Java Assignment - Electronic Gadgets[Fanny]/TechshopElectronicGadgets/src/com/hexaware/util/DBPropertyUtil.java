package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
	 public static String getConnectionString(String propertyFileName) {
	        Properties props = new Properties();
	        try {
	            FileInputStream fis = new FileInputStream(propertyFileName);
	            props.load(fis);
	            fis.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return props.getProperty("connectionString");
	    }

}
