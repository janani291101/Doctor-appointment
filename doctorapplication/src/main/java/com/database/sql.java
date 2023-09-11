package com.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sql {
	private final static String DRIVER_CLASS="";
	private final static String URL="jdbc:mysql://localhost:3306/onlineappointment";
	private final static String USERNAME="root";
	private final static String PASSWORD="Janani@2";
	
	private static Connection connection=null;
	
	public static Connection getConnection() {
		try {
			connection=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connection Success....");
		} catch (SQLException e) {
			System.err.println("Connection Not Established...");
			System.err.print(e);
		}
		return connection;
	}

}
