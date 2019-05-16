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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.AdminBll;

public class AdminLoginApp extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordPasswordField;

	private AdminBll adminBll;
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
		
		adminBll = new AdminBll();
		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new JOptionPane().showMessageDialog(null, adminBll.login(userNameTextField.getText(), passwordPasswordField.getText()));
					AdminApp adminApp = new AdminApp();
					adminApp.setVisible(true);
				}catch(Exception exc) {
					new JOptionPane().showMessageDialog(null, exc.getMessage());				}			
			}
		});
		panel.add(btnLogin);
	}

}
