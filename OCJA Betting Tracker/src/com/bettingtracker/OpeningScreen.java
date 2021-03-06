package com.bettingtracker;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class OpeningScreen extends JFrame {

	
	
	private JPanel contentPane;
	private JTextField textFieldUserName;
	private JPasswordField passwordField;
	private ImageIcon img = new ImageIcon("syndicate.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpeningScreen frame = new OpeningScreen();
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
	public OpeningScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(img.toString()));
		//System.out.println(img.getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(640-184, 512-147, 369, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Syndicate");
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 36, 400, 2);
		contentPane.add(separator);
		
		JLabel lblWelcome = new JLabel("Welcome to the syndicate");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(0, 11, 363, 14);
		contentPane.add(lblWelcome);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 77, 78, 14);
		contentPane.add(lblUsername);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(10, 91, 184, 20);
		contentPane.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 122, 78, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 135, 184, 20);
		contentPane.add(passwordField);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Admin ad = new Admin("con90", new char[]{'1','2','3'});
					
				if(textFieldUserName.getText().equals(ad.getUsername())&Arrays.equals(passwordField.getPassword(), ad.getPassword())){
					
					MainScreen ms = new MainScreen(OpeningScreen.this);
					setContentPane(ms);
					ms.setBounds(getBounds().x,getBounds().y , ms.getBounds().width,ms.getBounds().height);
					setBounds(ms.getBounds());
				}else{
					JOptionPane.showMessageDialog(OpeningScreen.this, "Username or password are incorrect");
					ad = null;
				}
				
				
			}
		});
		btnSignIn.setBounds(10, 177, 89, 23);
		contentPane.add(btnSignIn);
		
		
		
		
		
		
		
		
	}
}
