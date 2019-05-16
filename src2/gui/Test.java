package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BookDao;
import model.Book;
import model.BookTableModel;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Test extends JFrame {

	private JPanel contentPane;
	private JTextField authorTextField;
	private JTextField genreTextField;
	private JTextField releaseTextField;
	private JTextField titleTextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFilterBooksByAuthor = new JLabel("Filter by author");
		lblFilterBooksByAuthor.setBounds(31, 11, 86, 14);
		contentPane.add(lblFilterBooksByAuthor);
		
		authorTextField = new JTextField();
		authorTextField.setBounds(136, 8, 110, 20);
		contentPane.add(authorTextField);
		authorTextField.setColumns(10);
		
		JButton btnFilterAuthor = new JButton("filter");
		btnFilterAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BookDao bookDao = new BookDao();
					String author = authorTextField.getText();
					List<Book> books = new ArrayList<>();
					
					if (author != null && author.trim().length() > 0) {
						books = bookDao.searchBook(author);
					} else {
						books = bookDao.getAllBooks();
					}
					 BookTableModel model = new BookTableModel(books);
					  
					  table.setModel(model);
					
				}catch(Exception exc){
					JOptionPane.showMessageDialog(Test.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		btnFilterAuthor.setBounds(256, 7, 89, 23);
		contentPane.add(btnFilterAuthor);
		
		JLabel lblFilterBooksByGenre = new JLabel("Filter by genre");
		lblFilterBooksByGenre.setBounds(31, 36, 95, 14);
		contentPane.add(lblFilterBooksByGenre);
		
		genreTextField = new JTextField();
		genreTextField.setBounds(136, 33, 110, 20);
		contentPane.add(genreTextField);
		genreTextField.setColumns(10);
		
		JButton btnFilterByGenre = new JButton("filter");
		btnFilterByGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BookDao bookDao = new BookDao();
					String genre = genreTextField.getText();
					List<Book> books = new ArrayList<>();
					
					if (genre != null && genre.trim().length() > 0) {
						books = bookDao.searchBookGenre(genre);
					} else {
						books = bookDao.getAllBooks();
					}
					 BookTableModel model = new BookTableModel(books);
					  
					  table.setModel(model);
					
				}catch(Exception exc){
					JOptionPane.showMessageDialog(Test.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnFilterByGenre.setBounds(256, 32, 89, 23);
		contentPane.add(btnFilterByGenre);
		
		JLabel lblFilterBooksByRelease = new JLabel("Filter by release");
		lblFilterBooksByRelease.setBounds(31, 61, 95, 14);
		contentPane.add(lblFilterBooksByRelease);
		
		JLabel lblFilterBooksByTitle = new JLabel("Filter by title");
		lblFilterBooksByTitle.setBounds(31, 85, 95, 14);
		contentPane.add(lblFilterBooksByTitle);
		
		releaseTextField = new JTextField();
		releaseTextField.setBounds(136, 58, 110, 20);
		contentPane.add(releaseTextField);
		releaseTextField.setColumns(10);
		
		titleTextField = new JTextField();
		titleTextField.setBounds(136, 82, 110, 20);
		contentPane.add(titleTextField);
		titleTextField.setColumns(10);
		
		JButton btnFilterReleaseDate = new JButton("filter");
		btnFilterReleaseDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnFilterReleaseDate.setBounds(256, 57, 89, 23);
		contentPane.add(btnFilterReleaseDate);
		
		JButton btnFilterTitle = new JButton("filter");
		btnFilterTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BookDao bookDao = new BookDao();
					String title = titleTextField.getText();
					List<Book> books = new ArrayList<>();
					
					if (title != null && title.trim().length() > 0) {
						books = bookDao.searchBookTitle(title);
					} else {
						books = bookDao.getAllBooks();
					}
					 BookTableModel model = new BookTableModel(books);
					  
					  table.setModel(model);
					
				}catch(Exception exc){
					JOptionPane.showMessageDialog(Test.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnFilterTitle.setBounds(256, 81, 89, 23);
		contentPane.add(btnFilterTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 172, 525, 204);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
