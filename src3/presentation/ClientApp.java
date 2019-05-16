package presentation;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BookBll;
import businessLogic.Factory.BookFactory;
import businessLogic.Factory.Recommandation;
import dao.BookDao;
import dao.ClientDao;
import dao.Model.Book;
import dao.Model.BookTableModel;
import dao.Model.Client;

public class ClientApp extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBookName;
	private JTextField textFieldAuthor;
	private JTable table;
	private JTextField textFieldUsername;
	private JTextField textFieldPrice;
	private JTextField textFieldRecommandations;
	private BookBll bookBll;
	private JTextField textFieldRecommandation;

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
		setBounds(100, 100, 636, 490);
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
		textFieldBookName.setBounds(420, 44, 189, 20);
		contentPane.add(textFieldBookName);
		textFieldBookName.setColumns(10);
		
		bookBll = new BookBll();
		
		JButton btnBorrow = new JButton("borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					bookBll.borrowBook(textFieldBookName.getText(), textFieldUsername.getText());
 
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
				try {
					bookBll.returnBook(textFieldAuthor.getText(), textFieldUsername.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
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
					 table.setModel(bookBll.filterBooksByAuthor(textFieldAuthor.getText()));		
				}catch(Exception exc){
					JOptionPane.showMessageDialog(ClientApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnFilterByTitle.setBounds(252, 7, 89, 23);
		contentPane.add(btnFilterByTitle);
		
		JLabel lblUsername = new JLabel("Type your username");
		lblUsername.setBounds(485, 205, 124, 14);
		contentPane.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(485, 219, 86, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(438, 263, 171, 52);
		contentPane.add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		JLabel lblRecommandations = new JLabel("Recommandations");
		lblRecommandations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRecommandations.setBounds(10, 275, 124, 40);
		contentPane.add(lblRecommandations);
		
		textFieldRecommandations = new JTextField();
		textFieldRecommandations.setBounds(10, 326, 331, 84);
		contentPane.add(textFieldRecommandations);
		textFieldRecommandations.setColumns(10);
		
		JButton btnGetRecommandations = new JButton("Get recommandations");
		btnGetRecommandations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textFieldRecommandations.setText(bookBll.bookRecommandation(textFieldRecommandation.getText()).getTitle().toString()+" "+bookBll.bookRecommandation("price").getPrice()+"RON");
			}
		});
		btnGetRecommandations.setBounds(351, 357, 155, 23);
		contentPane.add(btnGetRecommandations);
		
		JLabel lblGet = new JLabel("Get recommandation by ");
		lblGet.setBounds(351, 332, 157, 14);
		contentPane.add(lblGet);
		
		textFieldRecommandation = new JTextField();
		textFieldRecommandation.setBounds(523, 329, 86, 20);
		contentPane.add(textFieldRecommandation);
		textFieldRecommandation.setColumns(10);
		
		JLabel lblTypeBookTitle = new JLabel("Type book title");
		lblTypeBookTitle.setBounds(420, 19, 154, 14);
		contentPane.add(lblTypeBookTitle);
	}
}
