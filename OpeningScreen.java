import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class OpeningScreen extends JFrame {
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JButton btnSignUp;
	private JCheckBox chckbxRememberMe;
	private JCheckBox chckbxNewToSyndicate;
	private String userName = "" ;
	private String everything = "" ;
	private ArrayList<String> usernames = new ArrayList<String>();



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
		setTitle("Welcome to the syndicate");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 36, 76, 14);
		getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 61, 76, 14);
		getContentPane().add(lblPassword);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(77, 33, 86, 20);
		getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);

		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(77, 58, 86, 20);
		getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);

		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(!(textFieldUsername.getText().equals(""))&&!(textFieldUsername.getText().equals(" "))){

					userName = textFieldUsername.getText();
					everything = loadUserNames();
					int wordStart = 0;
					for (int i = 0; i < everything.length();i++){

						if(everything.charAt(i)=='\n'){
							usernames.add(everything.substring(wordStart, i));
							wordStart = i;
						}
					}System.out.println(usernames);

				}else{
					System.out.println("username must be at least 1 char long and not start with a space");
				}

				PrintWriter out = null ;

				try{
					out = new PrintWriter("Username.txt");
				}catch(FileNotFoundException fne){
					fne.printStackTrace();
				}
				out.println(everything+userName);
				out.close();
			}
		});
		btnSignIn.setBounds(74, 86, 89, 23);
		getContentPane().add(btnSignIn);

		chckbxRememberMe = new JCheckBox("Remember me");
		chckbxRememberMe.setBounds(292, 32, 132, 23);
		getContentPane().add(chckbxRememberMe);

		btnSignUp = new JButton("Sign up");
		btnSignUp.setEnabled(false);
		btnSignUp.setBounds(10, 196, 89, 23);
		getContentPane().add(btnSignUp);

		chckbxNewToSyndicate = new JCheckBox("New to syndicate ?");
		chckbxNewToSyndicate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewToSyndicate.isSelected()){
					btnSignUp.setEnabled(true);
				}else {
					btnSignUp.setEnabled(false);
				}
			}
		});
		chckbxNewToSyndicate.setBounds(6, 151, 157, 23);
		getContentPane().add(chckbxNewToSyndicate);




	}

	private String loadUserNames() {
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader("Username.txt"))) {

			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}


		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
		return sb.toString(); 

	}
}
