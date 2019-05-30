package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Model.Book;
import dao.connection.MyConnection;

public class BookDao {
	private MyConnection myConnection;
	
	public BookDao(){
		myConnection = new MyConnection();
	}
	
	public List<Book> getAllBooks()throws Exception{
		List<Book> list = new ArrayList<>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = myConnection.getMyConnection().createStatement();
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
	public List<Book> getBooksByPrice(int price)throws Exception{
		List<Book> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {

			statement = myConnection.getMyConnection().prepareStatement("select * from books where price < ? ");
			statement.setInt(1, price);
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
	
	public List<Book> searchBorrowedBookAccount(String acc)throws Exception{
		List<Book> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			acc += "%";
			statement = myConnection.getMyConnection().prepareStatement("select * from borrowed_books where account like ? ");
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
			statement = myConnection.getMyConnection().prepareStatement("select * from books where author like ? order by author DESC ");
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
			statement = myConnection.getMyConnection().prepareStatement("select * from books where genre like ? ");
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
			statement = myConnection.getMyConnection().prepareStatement("select * from books where title like ? ");
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
	
	public List<Book> returnBorrowedBook(String title, String account)throws Exception{
		//List<Book> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			title +="%";

			statement = myConnection.getMyConnection().prepareStatement("delete from borrowed_books where title like ? and account like ? ");
			statement.setString(1, title);
			statement.setString(2, account);
			statement.executeUpdate();
			
			return null;
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
			myStmt = myConnection.getMyConnection().prepareStatement("insert into books (releaseDate,author,genre,noOfBooks,title,price)  values(?,?,?,?,?,?)");
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
			myStmt = myConnection.getMyConnection().prepareStatement("update books set noOfBooks=? where title=?");
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
			myStmt = myConnection.getMyConnection().prepareStatement("update borrowed_books set noOfBooks=? where account=?");
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
			myStmt = myConnection.getMyConnection().prepareStatement("insert into borrowed_books (idBook,releaseDate,author,genre,noOfBooks,title,price,account)  values(?,?,?,?,?,?,?,?)");
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
	
}
