package com.bettingtracker;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OperningScreen extends JFrame {

	
	
	private JPanel contentPane;
	private JTextField textFieldUserName;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OperningScreen frame = new OperningScreen();
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
	public OperningScreen() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
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
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(10, 134, 184, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MainScreen().setVisible(true);
			}
		});
		btnSignIn.setBounds(10, 177, 89, 23);
		contentPane.add(btnSignIn);
	}
}
