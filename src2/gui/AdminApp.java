package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

import dao.BookDao;
import model.Book;
import model.BookTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;

public class AdminApp extends JFrame {

	private JPanel contentPane;
	private JTextField authorTextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminApp frame = new AdminApp();
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
	public AdminApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		
		JLabel lblFilterBooks = new JLabel("Filter books by author");
		
		authorTextField = new JTextField();
		authorTextField.setColumns(10);
		
		JButton btnFilter = new JButton("filter");
		btnFilter.addActionListener(new ActionListener() {
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
					JOptionPane.showMessageDialog(AdminApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(lblFilterBooks);
		panel.add(authorTextField);
		panel.add(btnFilter);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.EAST);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
