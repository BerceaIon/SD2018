package gui;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ClientDao;
import model.Client;

public class CreateNewAccountUser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JTextField textFieldWallet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNewAccountUser frame = new CreateNewAccountUser();
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
	public CreateNewAccountUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(132, 36, 86, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(132, 61, 86, 14);
		contentPane.add(lblLastName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(132, 86, 86, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(132, 111, 86, 14);
		contentPane.add(lblPassword);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(250, 33, 86, 20);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(250, 58, 86, 20);
		contentPane.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(250, 83, 86, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JButton btnInsertNewUser = new JButton("create");
		btnInsertNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClientDao clientDao = new ClientDao();
					String firstName = textFieldFirstName.getText();
					String lastName = textFieldLastName.getText();
					String username = textFieldUsername.getText();
					String password = passwordField.getText();
					int wallet =Integer.parseInt(textFieldWallet.getText()) ;
					if(firstName!=null && firstName.trim().length()>0 &&
					   lastName!=null && lastName.trim().length()>0 &&
					   username!=null && username.trim().length()>0 &&
					   password!=null && password.trim().length()>0 
					    && wallet >=0) {
					    clientDao.insertClient(new Client(firstName,lastName,0,username,password,wallet));
					    new JOptionPane().showMessageDialog(null, "Account created!");
					    setVisible(false);
					}else {
						new JOptionPane().showMessageDialog(null, "Wrong fields inserted");
						  System.out.println("Wrong fields inserted!");
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnInsertNewUser.setBounds(172, 181, 89, 23);
		contentPane.add(btnInsertNewUser);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(250, 108, 86, 20);
		contentPane.add(passwordField);
		
		JLabel lblWallet = new JLabel("Wallet");
		lblWallet.setBounds(132, 136, 46, 14);
		contentPane.add(lblWallet);
		
		textFieldWallet = new JTextField();
		textFieldWallet.setBounds(250, 133, 86, 20);
		contentPane.add(textFieldWallet);
		textFieldWallet.setColumns(10);
	}
}
