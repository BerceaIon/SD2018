package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class AdminApp extends JFrame {

	private JPanel contentPane;
	private JTextField authorTextField;
	private JTable table;
	private BookBll bookBll;
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
		setBounds(100, 100, 831, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		
		JLabel lblFilterBooks = new JLabel("Filter books by author");
		
		authorTextField = new JTextField();
		authorTextField.setColumns(10);
		bookBll = new BookBll();
		JButton btnFilter = new JButton("filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {		  
					 table.setModel(bookBll.filterBooksByAuthor(authorTextField.getText()));		
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
