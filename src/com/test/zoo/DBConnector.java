package com.test.zoo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	static Connection con;
	public DBConnector(){
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			
			con = DriverManager.getConnection("jdbc:derby://localhost:1527/ZooDB");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public static Connection getConnection(){
		return con;
	}

}
