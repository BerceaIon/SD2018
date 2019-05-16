package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Client;

public class ClientDao {
	private Connection myConnection;
	
	public ClientDao()throws Exception{
		
		  Properties properties = new Properties(); properties.load(new
		  FileInputStream("sql/library.properties"));
		  
		  String user = properties.getProperty("user"); String password =
		  properties.getProperty("password"); String dburl =
		  properties.getProperty("dburl");
		  
		  myConnection = DriverManager.getConnection(dburl, user, password);
		  System.out.println("Connection succesfull  to "+dburl);

	}
	
	public List<Client> getAllClients() throws Exception {
		List<Client> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConnection.prepareStatement("select * from client");
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Client tempClient = convertRowToClient(myRs);
				list.add(tempClient);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Client> searchClient(String account) throws Exception {
		List<Client> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConnection.prepareStatement("select * from client where account like ?");
			myStmt.setString(1, account);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Client tempClient = convertRowToClient(myRs);
				list.add(tempClient);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public void  insertClient(Client client) throws Exception {

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConnection.prepareStatement("insert into client (firstName,lastName,booksBorrowed,account,password,wallet)  values(?,?,?,?,?,?)");
			//myStmt.setInt(1, client.getIdClient());
			myStmt.setString(1, client.getFirstName());
			myStmt.setString(2, client.getLastName());
			myStmt.setInt(3, 0);
			myStmt.setString(4, client.getAccount());
			myStmt.setString(5, client.getPassword());
			myStmt.setInt(6, client.getWallet());
			
			myStmt.executeUpdate();
			
			 

		}
		finally {
			close(myStmt, myRs);
		}
	}
	public void  updateWallet(Client client) throws Exception {

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConnection.prepareStatement("update client set wallet=? where account=?");

			myStmt.setInt(1, client.getWallet());
			myStmt.setString(2, client.getAccount());

			myStmt.executeUpdate();

		}
		finally {
			close(myStmt, myRs);
		}
	}
	public void  updateBorrowedBooks(Client client) throws Exception {

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConnection.prepareStatement("update client set booksBorrowed=? where account=?");

			myStmt.setInt(1, client.getBooksBorrowed());
			myStmt.setString(2, client.getAccount());

			myStmt.executeUpdate();

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
	 
	public Client convertRowToClient(ResultSet myResultSet)throws SQLException{
		int id = myResultSet.getInt("idClient");
		String lastName = myResultSet.getString("firstName");
		String firstName = myResultSet.getString("lastName");
		int booksBorrowed = myResultSet.getInt("booksBorrowed");
		String account = myResultSet.getString("account");
		String password = myResultSet.getString("password");
		int wallet = myResultSet.getInt("wallet");
		
		Client temp = new Client(id, firstName, lastName, booksBorrowed, account, password,wallet);
		return temp;
		
	}
	@SuppressWarnings("null")
	public static void main(String args[]) throws Exception {
		ClientDao clientDao = new ClientDao();
		List<Client> lista = new ArrayList<>();
		lista = clientDao.searchClient("ionut");
		Client client = null;
		for(Client c:lista) {
			System.out.println(c.toString());
			client=c;
		}
		client.setWallet(1000);
		clientDao.updateWallet(client);
		//client.insertClient(new Client("Ionita","Vasile",0,"ionita","vasile",100));
	}
}
