package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import dao.ClientDao;
import model.Client;

public class ClientLoginApp extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLoginApp frame = new ClientLoginApp();
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
	public ClientLoginApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(114, 28, 68, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(114, 53, 68, 14);
		contentPane.add(lblPassword);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(192, 25, 86, 20);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClientDao clientDao = new ClientDao();
					String username = usernameTextField.getText();
					String password = passwordField.getText();
					List<Client> clients = new ArrayList<>();
					if(username!=null && username.trim().length()>0) {
						clients = clientDao.searchClient(username);		
					}else {
						new JOptionPane().showMessageDialog(null, "Numele introdus nu satisface conditiile");
					}
					for(int i=0; i<clients.size(); i++) {  
						  if((clients.get(i)).getPassword().equals(password)) {
							  AdminApp adminApp = new AdminApp();
								adminApp.setVisible(true);
							    setVisible(false);
							  System.out.println("Login cu succes!!"); }
						  else {
							  new JOptionPane().showMessageDialog(null, "Parola incorecta");
							  System.out.println("Parola incorecta!");
						  }
						  }
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(145, 119, 110, 23);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(192, 50, 86, 20);
		contentPane.add(passwordField);
		
		JButton btnNewAccount = new JButton("new account");
		btnNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateNewAccountUser newAccount = new CreateNewAccountUser();
				newAccount.setVisible(true);
			}
		});
		btnNewAccount.setBounds(145, 153, 110, 23);
		contentPane.add(btnNewAccount);
	}
}
