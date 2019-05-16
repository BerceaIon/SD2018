package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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

import dao.AdminDao;
import model.Admin;

public class AdminLoginApp extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordPasswordField;

	//private AdminDao adminDao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLoginApp frame = new AdminLoginApp();
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
	public AdminLoginApp() {
		setTitle("Admin Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblUsername = new JLabel("username");
		panel.add(lblUsername);
		
		userNameTextField = new JTextField();
		panel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		panel.add(lblPassword);
		
		passwordPasswordField = new JPasswordField();
		panel.add(passwordPasswordField);
		passwordPasswordField.setColumns(10);
		
		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					AdminDao adminDao = new AdminDao();
					String lastName = userNameTextField.getText();
					String password = passwordPasswordField.getText();
					List<Admin> admins = new ArrayList<>();
					if(lastName!=null && lastName.trim().length()>0) {
						admins = adminDao.searchAdmin(lastName);
						
					}else {
						new JOptionPane().showMessageDialog(null, "Numele introdus nu satisface conditiile");
					}
					System.out.println(admins.size());
					  for(int i=0; i<admins.size(); i++) {  
					  if((admins.get(i)).getPassword().equals(password)) {
						 // AdminApp adminApp = new AdminApp();
						//	adminApp.setVisible(true);
						  Test adminApp = new Test();
						  adminApp.setVisible(true);
						    setVisible(false);
						  System.out.println("Login cu succes!!"); }
					  else {
						  new JOptionPane().showMessageDialog(null, "Parola incorecta");
						  System.out.println("Parola incorecta!");
					  }
					  }
				}catch(Exception exc) {
					JOptionPane.showMessageDialog(AdminLoginApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel.add(btnLogin);
	}

}
