package businessLogic;

import java.util.ArrayList;
import java.util.List;

import businessLogic.Factory.BookFactory;
import businessLogic.Factory.Recommandation;
import businessLogic.exception.InvalidCredentialException;
import dao.BookDao;
import dao.ClientDao;
import dao.Model.Book;
import dao.Model.BookTableModel;
import dao.Model.Client;

public class BookBll {
	private BookDao bookDao;
	private ClientDao clientDao;
	List<Client> clients = new ArrayList<>();
	List<Book> books = new ArrayList<>();;
	BookFactory bookFactory;
	Recommandation recommandation;
	Book book;
	public BookBll() {
		bookDao = new BookDao();
	}
	
	
	public BookTableModel filterBooksByAuthor(String author) throws Exception {
		List<Book> books = new ArrayList<>();
		if(author == null) {
			throw new InvalidCredentialException();
		}
		books = bookDao.searchBook(author);
		BookTableModel tableModel = new BookTableModel(books);
		return tableModel;

	}
	public String borrowBook(String title, String username) throws Exception {
		books = bookDao.searchBookTitle(title);
		clients = clientDao.searchClient(username);
		
		Book tempBook = null;
		Client tempClient = null;
		
//		for(Book b:books) {
//			tempBook = b;
//		}
//		 
//		for(Client c: clients) {
//			tempClient = c;
//		}
		
//		if(tempBook.getNoOfBooks()==0) {
//			throw new InvalidNoOfBooksException();
//		}else {
//			List<Book> borrowedBooks = new ArrayList<>();
//			borrowedBooks = bookDao.searchBorrowedBookAccount(username);
//			
//			//actualizare nr carti in tabela books
//			int oldNoOfBook = tempBook.getNoOfBooks()-1;
//			tempBook.setNoOfBooks(oldNoOfBook);
//			bookDao.updateNoOfBooks(tempBook);
//			
//			//actualizare bani client
//			int newAmount = tempClient.getWallet()-tempBook.getPrice();
//			tempClient.setWallet(newAmount);
//			clientDao.updateWallet(tempClient);
//			
//			//actualizare carti imprumutate client
//			tempClient.setBooksBorrowed(tempClient.getBooksBorrowed()+1);
//			clientDao.updateBorrowedBooks(tempClient);
//			
//			//inserare carte in bb
//			if(borrowedBooks != null) {
//				bookDao.updateNoOfBorrowedBooks(tempBook, username);
//			}else {
//				bookDao.insertBorrowedBook(tempBook, username);
//			}
			
		//}
		return "Book borrowed";
		
		
	}
	
	
	public String returnBook(String author, String username) throws Exception {
		if(author==null || username ==null) {
			throw new InvalidCredentialException();
		}else {
		bookDao.returnBorrowedBook(author, username);
		}
		return "Book returned";
	}
	
	public Book bookRecommandation(String type) {
		bookFactory = new BookFactory();
		recommandation = bookFactory.createRecommandation(type);
		Book book = recommandation.getRecommandation();
		return book;
	}
}
