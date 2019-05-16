package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BookDao;
import dao.ClientDao;
import model.Book;
import model.BookTableModel;
import model.Client;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ClientApp extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBookName;
	private JTextField textFieldAuthor;
	private JTable table;
	private JTextField textFieldUsername;
	private JTextField textFieldPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientApp frame = new ClientApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 405, 218);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textFieldBookName = new JTextField();
		textFieldBookName.setBounds(438, 44, 189, 20);
		contentPane.add(textFieldBookName);
		textFieldBookName.setColumns(10);
		
		JButton btnBorrow = new JButton("borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String bookTitle = textFieldBookName.getText();
					String username = textFieldUsername.getText();
					
					BookDao bookDao = new BookDao();					
					ClientDao clientDao = new ClientDao();
					List<Book> books = new ArrayList<>();
					List<Client> clients = new ArrayList<>();
					books = bookDao.searchBookTitle(bookTitle);
					clients = clientDao.searchClient(username);
					Book tempBook = null;
					Client tempClient = null;
					int totalCost;
					
					for(Book b:books) {
						tempBook=b;
					}
					for(Client c:clients) {
						tempClient = c;
					}
					if(tempBook.getNoOfBooks()==0) {
						new JOptionPane().showMessageDialog(null, "Nu sunt carti disponibile");
						  System.out.println("Nu sunt carti disponibile!"+tempBook.getNoOfBooks());
					}else {
						totalCost = tempBook.getPrice();
						if(totalCost>0) {
							int option = JOptionPane.showConfirmDialog(null, "Costul total este: "+totalCost+ " ron"+"\ndoresti sa continui tranzactia?","The payment", JOptionPane.YES_NO_OPTION);
							if(option==0) {
								List<Book> borrowedBooks = new ArrayList<>();
								borrowedBooks = bookDao.searchBorrowedBookAccount(username);
								
								
								
								int oldNoOfBooks = tempBook.getNoOfBooks()-1;
								tempBook.setNoOfBooks(oldNoOfBooks);
								bookDao.updateNoOfBooks(tempBook);
								
								int newAmount = tempClient.getWallet()-tempBook.getPrice();
								tempClient.setWallet(newAmount);
								clientDao.updateWallet(tempClient);
								
								int newNoOfBooks = tempClient.getBooksBorrowed()+1;
								tempClient.setBooksBorrowed(newNoOfBooks);
								clientDao.updateBorrowedBooks(tempClient);
								if(borrowedBooks != null) {
									bookDao.updateNoOfBorrowedBooks(tempBook, username);
								}else {
									//insert
									bookDao.insertBorrowedBook(tempBook, username);
								}
							}
							
						/*	
						bookDao.insertBorrowedBook(tempBook, username);
						
						int oldNoOfBooks = tempBook.getNoOfBooks()-1;
						tempBook.setNoOfBooks(oldNoOfBooks);
						bookDao.updateNoOfBooks(tempBook);
						
						int newAmount = tempClient.getWallet()-tempBook.getPrice();
						tempClient.setWallet(newAmount);
						clientDao.updateWallet(tempClient);
						
						int newNoOfBooks = tempClient.getBooksBorrowed()+1;
						tempClient.setBooksBorrowed(newNoOfBooks);
						clientDao.updateBorrowedBooks(tempClient);*/
						}
						
					}
					/*
					 * for(Book b:books){ if(b.getNoOfBooks()>0) {
					 * bookDao.insertBorrowedBook(b,textFieldUsername.getText());
					 * textFieldPrice.setText("The price of books is "+b.getPrice()); } }
					 */
					/*
					 * for(Client c:clients) { System.out.println(c.toString()); }
					 */
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnBorrow.setBounds(485, 111, 89, 23);
		contentPane.add(btnBorrow);
		
		JButton btnReturnBook = new JButton("return");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReturnBook.setBounds(485, 145, 89, 23);
		contentPane.add(btnReturnBook);
		
		JLabel lblTitle = new JLabel("Search by title");
		lblTitle.setBounds(10, 11, 94, 14);
		contentPane.add(lblTitle);
		
		textFieldAuthor = new JTextField();
		textFieldAuthor.setBounds(101, 8, 124, 20);
		contentPane.add(textFieldAuthor);
		textFieldAuthor.setColumns(10);
		
		JButton btnFilterByTitle = new JButton("search");
		btnFilterByTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BookDao bookDao = new BookDao();
					String title = textFieldAuthor.getText();
					List<Book> books = new ArrayList<>();
					
					if (title != null && title.trim().length() > 0) {
						books = bookDao.searchBookTitle(title);
					} else {
						books = bookDao.getAllBooks();
					}
					 BookTableModel model = new BookTableModel(books);
					  
					  table.setModel(model);
					
				}catch(Exception exc){
					JOptionPane.showMessageDialog(ClientApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnFilterByTitle.setBounds(252, 7, 89, 23);
		contentPane.add(btnFilterByTitle);
		
		JLabel lblUsername = new JLabel("Type your username");
		lblUsername.setBounds(485, 207, 124, 14);
		contentPane.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(485, 232, 86, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(438, 263, 171, 52);
		contentPane.add(textFieldPrice);
		textFieldPrice.setColumns(10);
	}
}
