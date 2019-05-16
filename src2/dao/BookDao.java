package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;
import java.util.Properties;


import model.Book;
import model.Client;

public class BookDao {
	private Connection myConnection;
	
	public BookDao()throws Exception{
		//get db properties
		Properties properties = new Properties();
		properties.load(new FileInputStream("sql/library.properties"));
		
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String dburl = properties.getProperty("dburl");
	
		//connect to database
		myConnection = (Connection) DriverManager.getConnection(dburl, user, password);		
		System.out.println("Database connection succesfull!" + dburl);
	}
	
	public List<Book> getAllBooks()throws Exception{
		List<Book> list = new ArrayList<>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = myConnection.createStatement();
			resultSet = statement.executeQuery("select * from books");
			
			while(resultSet.next()) {
				Book temporaryBook = convertRowToBook(resultSet);
				list.add(temporaryBook);
			}
			/*
			 * for(int i=0; i<list.size(); i++) {
			 * System.out.println(list.get(i).toString()); }
			 */
		}
		finally {
			close(statement, resultSet);
		}
		return list;
	}
	public List<Book> searchBorrowedBookAccount(String acc)throws Exception{
		List<Book> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			acc += "%";
			statement = myConnection.prepareStatement("select * from borrowed_books where account like ? ");
			statement.setString(1, acc);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book temporaryBook = convertRowToBook(resultSet);
				list.add(temporaryBook);
			}
			return list;
		}
		finally {
			close(statement, resultSet);
		}
	}
	
	public List<Book> searchBook(String author)throws Exception{
		List<Book> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			author +="%";
			statement = myConnection.prepareStatement("select * from books where author like ? order by author DESC ");
			statement.setString(1, author);
			//statement.setString(2, author);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book temporaryBook = convertRowToBook(resultSet);
				list.add(temporaryBook);
			}
			return list;
		}
		finally {
			close(statement, resultSet);
		}
	}
	
	public List<Book> searchBookGenre(String genre)throws Exception{
		List<Book> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			genre +="%";
			statement = myConnection.prepareStatement("select * from books where genre like ? ");
			statement.setString(1, genre);
			//statement.setString(2, author);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book temporaryBook = convertRowToBook(resultSet);
				list.add(temporaryBook);
			}
			return list;
		}
		finally {
			close(statement, resultSet);
		}
	}
	public List<Book> searchBookTitle(String title)throws Exception{
		List<Book> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			title +="%";
			statement = myConnection.prepareStatement("select * from books where title like ? ");
			statement.setString(1, title);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book temporaryBook = convertRowToBook(resultSet);
				list.add(temporaryBook);
			}
			return list;
		}
		finally {
			close(statement, resultSet);
		}
	}
	
	
	public Book convertRowToBook(ResultSet resultSet)throws SQLException{
		//int id = resultSet.getInt("bookId");
		String releaseDate = resultSet.getString("releaseDate");
		String author = resultSet.getString("author");
		String genre = resultSet.getString("genre");
		int noOfBooks = resultSet.getInt("noOfBooks");
		String title = resultSet.getString("title");
		int price = resultSet.getInt("price");
		
		Book temp = new Book(releaseDate,author,genre,noOfBooks,title,price);
		return temp;
	}
	
	public void  insertBook(Book book) throws Exception {

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConnection.prepareStatement("insert into books (releaseDate,author,genre,noOfBooks,title,price)  values(?,?,?,?,?,?)");
			//myStmt.setInt(1, client.getIdClient());
			myStmt.setString(1, book.getReleaseDate());
			myStmt.setString(2, book.getAuthor());
			myStmt.setString(3, book.getGenre());
			myStmt.setInt(4, book.getNoOfBooks());
			myStmt.setString(5, book.getTitle());
			myStmt.setInt(6, book.getPrice());
			
			myStmt.executeUpdate();
			
			 

		}
		finally {
			close(myStmt, myRs);
		}
	}
	public void updateNoOfBooks(Book book) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConnection.prepareStatement("update books set noOfBooks=? where title=?");
			myStmt.setInt(1, book.getNoOfBooks());
			myStmt.setString(2, book.getTitle());			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt, myRs);
		}
	}
	public void updateNoOfBorrowedBooks(Book book, String account) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConnection.prepareStatement("update borrowed_books set noOfBooks=? where account=?");
			myStmt.setInt(1, book.getNoOfBooks());
			myStmt.setString(2, account);			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt, myRs);
		}
	}
	public void  insertBorrowedBook(Book book, String username) throws Exception {

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConnection.prepareStatement("insert into borrowed_books (idBook,releaseDate,author,genre,noOfBooks,title,price,account)  values(?,?,?,?,?,?,?,?)");
			myStmt.setInt(1, book.getBookId());
			myStmt.setString(2, book.getReleaseDate());
			myStmt.setString(3, book.getAuthor());
			myStmt.setString(4, book.getGenre());
			myStmt.setInt(5, book.getNoOfBooks());
			myStmt.setString(6, book.getTitle());
			myStmt.setInt(7, book.getPrice());
			myStmt.setString(8, username);
			myStmt.executeUpdate();
			
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}
	
public static void main(String[] args) throws Exception {
		
		BookDao dao = new BookDao();
		Client newClient = new Client("Ionel", "Craciun", 0, "ionel", "craciunel", 100);
		newClient.setAccount("ionut");
		List<Book> books = new ArrayList<>();
		//Book newBook = new Book("12-Martie-1996","Rubben Micheles","drama",5,"1000 de lei",10);
		//System.out.println(dao.searchBookTitle("D"));
		//dao.insertBook(new Book("12-Martie-1996","Rubben Micheles","drama",5,"1000 de lei",10));
		//dao.insertBorrowedBook(newBook, newClient);
		
		/*
		 * books = dao.searchBook("Dan B"); Book c = null; for(Book b:books) { c=b; }
		 * System.out.println(c.toString()); c.setNoOfBooks(3); dao.updateNoOfBooks(c);
		 */
		books = dao.searchBorrowedBookAccount("ionut");
		for(Book b:books) {
			System.out.println(b.toString());
		}
		 
		
	}
}
