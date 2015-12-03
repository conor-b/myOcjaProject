package com.bettingtracker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainScreen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JOptionPane jop = new JOptionPane();
	private JTextField textFieldNewPunterName;
	private JCheckBox chckbxAddNewPunter;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnViewExistingPunters;
	private JList<String> punterList = new JList<String>();
	private ArrayList<String> punters = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MainScreen dialog = new MainScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MainScreen() {
		setBounds(100, 100, 362, 355);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		punterList.setModel(new AbstractListModel<String>(){

			String[] puntArr = new String[punters.size()];
			{
				MainScreen.this.punters.toArray(puntArr);
			}
			public int getSize() {
				return puntArr.length;
			}
			public String getElementAt(int index) {
				return puntArr[index];
			}
		});
		{
			btnViewExistingPunters = new JButton("View punter stats");
			btnViewExistingPunters.setEnabled(false);
			btnViewExistingPunters.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnViewExistingPunters.setBounds(10, 193, 141, 23);
			contentPanel.add(btnViewExistingPunters);
		}

		punterList = new JList<String>();
		punterList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(punterList.isSelectionEmpty()){
					btnViewExistingPunters.setEnabled(false);
					btnDelete.setEnabled(false);
				}else{
					btnViewExistingPunters.setEnabled(true);
					btnDelete.setEnabled(true);
				}
			}
		});


		punterList.setBounds(9, 11, 308, 171);
		contentPanel.add(punterList);

		textFieldNewPunterName = new JTextField();
		textFieldNewPunterName.setVisible(false);
		textFieldNewPunterName.setBounds(10, 274, 208, 20);
		contentPanel.add(textFieldNewPunterName);
		textFieldNewPunterName.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 259, 46, 14);
		contentPanel.add(lblName);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldNewPunterName.getText().equals("")){
					punters.add(textFieldNewPunterName.getText());
					String[] puntArr = new String[punters.size()];
					punters.toArray(puntArr);
					punterList.setListData(puntArr);
					textFieldNewPunterName.setText("");

				}else{

					jop.showMessageDialog(null, "Please enter a valid name");
				}
			}
		});
		btnAdd.setVisible(false);
		btnAdd.setBounds(228, 273, 89, 23);
		contentPanel.add(btnAdd);

		chckbxAddNewPunter = new JCheckBox("Add new punter");
		chckbxAddNewPunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAddNewPunter.isSelected()){
					textFieldNewPunterName.setVisible(true);
					btnAdd.setVisible(true);
				
				}else {
					textFieldNewPunterName.setVisible(false);
					btnAdd.setVisible(false);
				}
			}
		});
		chckbxAddNewPunter.setBounds(10, 223, 158, 23);
		contentPanel.add(chckbxAddNewPunter);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int temp = punterList.getSelectedIndex();
				System.out.println(temp);
				punters.remove(temp);
				String[] puntArr = new String[punters.size()];
				punters.toArray(puntArr);
				punterList.setListData(puntArr);
				if(punterList.isSelectionEmpty()){
					btnDelete.setEnabled(false);
					btnViewExistingPunters.setEnabled(false);
				}else{
					btnDelete.setEnabled(true);
					btnViewExistingPunters.setEnabled(true);
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(228, 193, 89, 23);
		contentPanel.add(btnDelete);
		lblName.setVisible(false);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
		}
	}
}
