package dao.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
	private Connection myConnection;
	
	public MyConnection(){
		  Properties properties = new Properties(); try {
			properties.load(new
			  FileInputStream("sql/library.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		  String user = properties.getProperty("user"); String password =
		  properties.getProperty("password"); String dburl =
		  properties.getProperty("dburl");
		  
		  try {
			myConnection = DriverManager.getConnection(dburl, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  System.out.println("Connection succesfull  to "+dburl);

	}

	public Connection getMyConnection() {
		return myConnection;
	}

}
