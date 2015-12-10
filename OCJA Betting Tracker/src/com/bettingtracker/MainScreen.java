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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import java.io.File;
import java.io.IOException;
import javax.swing.SwingConstants;

public class MainScreen extends JPanel {

	private final JPanel contentPanel = new JPanel();

	private JTextField textFieldNewPunterName;
	private JCheckBox chckbxAddNewPunter;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnViewExistingPunters;
	private JList<String> punterList = new JList<String>();
	//private ArrayList<String> punters = new ArrayList<String>();
	//static ArrayList<Punter> realPunters = new ArrayList<Punter>();
	private PunterHandler ph = new PunterHandler(new ArrayList<Punter>());
	private JLabel lblMainMenu;




	/**
	 * Create the dialog.
	 */
	public MainScreen(OpeningScreen os) {

		setBounds(0, 0, 353, 353);
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		punterList.setModel(new AbstractListModel<String>(){

			String[] puntArr = new String[ph.getPunterNames().size()];
			{


				MainScreen.this.ph.getPunterNames().toArray(puntArr);
				punterList.setListData(puntArr);
			}
			public int getSize() {
				return puntArr.length;
			}
			public String getElementAt(int index) {
				return puntArr[index];
			}
		});



//		new Thread(new Runnable(){
//
//			@Override
//			public void run() {
//				while(true){
//
//					try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					String[] puntArr = new String[ph.getPunterNames().size()];
//
//					ph.getPunterNames().toArray(puntArr);
//					boolean isSame = true;
//					for(int i = 0; i < ph.getPunterNames().size(); i++){
//						System.out.println("Size"+ph.getPunterNames().size());
//						if(!punterList.getModel().getElementAt(i).equals(ph.getPunterNames().get(i))){
//							isSame = false;
//							break;
//						}
//					}
//					
//					if(!isSame){
//						punterList.setListData(puntArr);
//					}
//					
//					
//
//				}
//			}
//
//		}).start();;

		{
			btnViewExistingPunters = new JButton("View punter stats");
			btnViewExistingPunters.setEnabled(false);
			btnViewExistingPunters.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(punterList.getSelectedIndex() >= 0){
						ph.setCurrentPunter(ph.getRealPunters().get(punterList.getSelectedIndex()));
						PunterStats ps = new PunterStats(ph,os);
						os.setContentPane(ps);
						ps.setBounds(os.getBounds().x, os.getBounds().y, ps.getBounds().width,ps.getBounds().height);
						os.setBounds(ps.getBounds());
						
						
					}else{
						JOptionPane.showMessageDialog(MainScreen.this, "No punter selected");
					}
				}
			});
			btnViewExistingPunters.setBounds(9, 209, 141, 23);
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


		punterList.setBounds(9, 27, 308, 171);
		contentPanel.add(punterList);

		textFieldNewPunterName = new JTextField();
		textFieldNewPunterName.setVisible(false);
		textFieldNewPunterName.setBounds(9, 285, 209, 20);
		contentPanel.add(textFieldNewPunterName);
		textFieldNewPunterName.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(9, 269, 33, 14);
		contentPanel.add(lblName);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldNewPunterName.getText().equals("")){
					ph.getPunterNames().add(textFieldNewPunterName.getText());
					String[] puntArr = new String[ph.getPunterNames().size()];
					ph.getPunterNames().toArray(puntArr);

					Punter p = new Punter();
					p.setName(textFieldNewPunterName.getText());
					ph.getRealPunters().add(p);
					
					//System.out.println(p.getName());
					textFieldNewPunterName.setText("");
					Serializer.serialize(ph.getRealPunters(), "Punters.data");
					punterList.setListData(puntArr);
					
					updateList();
				}else{

					JOptionPane.showMessageDialog(MainScreen.this, "Please enter a valid name");
				}
			}
		});
		btnAdd.setVisible(false);
		btnAdd.setBounds(228, 284, 89, 23);
		contentPanel.add(btnAdd);

		chckbxAddNewPunter = new JCheckBox("Add new punter");
		chckbxAddNewPunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAddNewPunter.isSelected()){
					textFieldNewPunterName.setVisible(true);
					lblName.setVisible(true);
					btnAdd.setVisible(true);

				}else {
					lblName.setVisible(false);
					textFieldNewPunterName.setVisible(false);
					btnAdd.setVisible(false);
				}
			}
		});
		chckbxAddNewPunter.setBounds(10, 239, 158, 23);
		contentPanel.add(chckbxAddNewPunter);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int temp = punterList.getSelectedIndex();
				System.out.println(temp);
				ph.getPunterNames().remove(temp);
				ph.getRealPunters().remove(temp);
				Serializer.serialize(ph.getRealPunters(), "Punters.data");
				String[] puntArr = new String[ph.getPunterNames().size()];
				ph.getPunterNames().toArray(puntArr);
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
		btnDelete.setBounds(228, 209, 89, 23);
		contentPanel.add(btnDelete);
		
		lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setBounds(0, 0, 337, 14);
		contentPanel.add(lblMainMenu);
		lblName.setVisible(false);
		{
			JPanel buttonPane = new JPanel();
			add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
		}

		if(new File("Punters.data").exists()){
			System.out.println("working");
			try {
				ArrayList<Punter> realPunters = (ArrayList<Punter>) Serializer.unserialize("Punters.data");
				ph = new PunterHandler(realPunters);
				for(Punter p : realPunters){
					ph.getPunterNames().add(p.getName());
					System.out.println(p.getName());
				}
				System.out.println(ph.getPunterNames().size());

				String[] puntArr = new String[ph.getPunterNames().size()];
				this.ph.getPunterNames().toArray(puntArr);
				punterList.setListData(puntArr);


			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void updateList(){
		String[] puntArr = new String[ph.getPunterNames().size()];

		ph.getPunterNames().toArray(puntArr);
		
		punterList.setListData(puntArr);
		
//		boolean isSame = true;
//		for(int i = 0; i < ph.getPunterNames().size(); i++){
//			System.out.println("Size"+ph.getPunterNames().size());
//			//if(!punterList.getModel().getElementAt(i).equals(ph.getPunterNames().get(i))){
//				isSame = false;
//				break;
//			//}
//		}
		
		//if(!isSame){
			
		//}
	}
}
