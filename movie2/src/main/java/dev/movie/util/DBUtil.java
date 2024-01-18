package dev.movie.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {
	public static Connection getConnection(String path, String DB_URL, String DATABASE_NAME, String USER, String PASSWORD) {
		
		Properties prop = new Properties();
	    try {
			prop.load(new FileInputStream(path));
			
			String DB_URL1 = prop.getProperty(DB_URL);
			String DATABASE_NAME1 = prop.getProperty(DATABASE_NAME);
			String USER1 = prop.getProperty(USER);
			String PASSWORD1 = prop.getProperty(PASSWORD);
			
			return DriverManager.getConnection(DB_URL1+DATABASE_NAME1, USER1, PASSWORD1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		return null;
	}
}