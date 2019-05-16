package dao;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import model.Admin;

public class AdminDao {
	private Connection myConnection;
	
	public AdminDao()throws Exception{
		
		  Properties properties = new Properties(); properties.load(new
		  FileInputStream("sql/library.properties"));
		  
		  String user = properties.getProperty("user"); String password =
		  properties.getProperty("password"); String dburl =
		  properties.getProperty("dburl");
		  
		  myConnection = DriverManager.getConnection(dburl, user, password);
		  System.out.println("Connection succesfull  to "+dburl);
		 
		//Connect connection = new Connect();
	}
	
	public List<Admin> searchAdmin(String lastName) throws Exception {
		List<Admin> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConnection.prepareStatement("select * from admin where lastName like ?");
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Admin tempAdmin = convertRowToAdmin(myRs);
				list.add(tempAdmin);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	
	  private static void close(Connection myConn, Statement myStmt, ResultSet
	  myRs) throws SQLException {
	  
	  if (myRs != null) { myRs.close(); }
	  
	  if (myStmt != null) {
	  
	  }
	  
	  if (myConn != null) { myConn.close(); } }
	  
	  private void close(Statement myStmt, ResultSet myRs) throws SQLException {
	  close(null, myStmt, myRs); }
	 
	public Admin convertRowToAdmin(ResultSet myResultSet)throws SQLException{
		int id = myResultSet.getInt("id");
		String lastName = myResultSet.getString("lastName");
		String firstName = myResultSet.getString("firstName");
		String password = myResultSet.getString("password");
		
		Admin temp = new Admin(id, lastName, firstName, password);
		return temp;
		
	}
	
	public static void main(String[] args) throws Exception {
		
		AdminDao admin = new AdminDao();
		
		List<Admin> admins = new ArrayList<>();
		//admins = admin.searchAdmin("Doe");
		//admins = admin.filterBy("genre", "drama");
		for (Admin a:admins) {
			System.out.println(a.toString());
		}
		//System.out.println(admin.searchAdmin("Doe").get(0).getPassword());
		//System.out.println(admin.);
	}
}
