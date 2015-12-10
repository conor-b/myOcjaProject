package com.bettingtracker;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Statseditor extends JPanel implements Callable {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNewName;
	private JTextField textFieldDepositMoney;
	private Integer[] values = {0,1,2,3,4,5};
	
	

	/**
	 * Create the dialog.
	 */
	public Statseditor(final PunterHandler ph, Callable call,OpeningScreen os) {
		setBounds(100, 100, 272, 300);
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boolean isValid = true;
					if (textFieldNewName.getText().equals("")|textFieldNewName.getText().equals(" ")){
						JOptionPane.showMessageDialog(Statseditor.this, "Name must be at least one character long");
						isValid = false;
					}else{
						ph.getCurrentPunter().setName(textFieldNewName.getText());
						Serializer.serialize(ph.getRealPunters(), "Punters.data");
						isValid = true;
					}
					if (textFieldDepositMoney.getText().equals("")|textFieldDepositMoney.getText().equals(" ")){
						JOptionPane.showMessageDialog(Statseditor.this, "Deposit must be at least one number long");
						isValid = false;
					}else{
						ph.getCurrentPunter().setBalance(ph.getCurrentPunter().getBalance()+Double.parseDouble(textFieldDepositMoney.getText()));
						Serializer.serialize(ph.getRealPunters(), "Punters.data");
						isValid = true;
					}
					
					if(isValid){
//					ph.getCurrentPunter().setWins((Integer)comboBoxWins.getSelectedItem()+ph.getCurrentPunter().getWins());
//					ph.getCurrentPunter().setLosses((Integer)comboBoxLosses.getSelectedItem()+ph.getCurrentPunter().getLosses());
					call.call(ph);
					PunterStats ps = new PunterStats(ph,os);
					os.setContentPane(ps);
					ps.setBounds(os.getBounds().x, os.getBounds().y, ps.getBounds().width,ps.getBounds().height);
					os.setBounds(ps.getBounds());
					
					}
				}
			});
			btnSave.setBounds(142, 228, 89, 23);
			contentPanel.add(btnSave);
		}
		{
			JLabel lblEditName = new JLabel("Edit name");
			lblEditName.setBounds(10, 11, 89, 14);
			contentPanel.add(lblEditName);
		}
		
		textFieldNewName = new JTextField();
		textFieldNewName.setBounds(10, 24, 221, 20);
		contentPanel.add(textFieldNewName);
		textFieldNewName.setColumns(10);
		textFieldNewName.setText(ph.getCurrentPunter().getName());
		
		JLabel lblDepositMoney = new JLabel("Deposit money");
		lblDepositMoney.setBounds(10, 51, 89, 14);
		contentPanel.add(lblDepositMoney);
		
		textFieldDepositMoney = new JTextField();
		textFieldDepositMoney.setEditable(false);
		textFieldDepositMoney.setBounds(10, 64, 221, 20);
		contentPanel.add(textFieldDepositMoney);
		textFieldDepositMoney.setColumns(10);
		textFieldDepositMoney.setText(""+Double.parseDouble("0.00"));
	
		
		JSlider sliderDeposit = new JSlider();
		sliderDeposit.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println(""+sliderDeposit.getValue());
				textFieldDepositMoney.setText(""+sliderDeposit.getValue());
			}

			
		});
		sliderDeposit.setBounds(10, 95, 221, 23);
		contentPanel.add(sliderDeposit);
		sliderDeposit.setMaximum(500);
		sliderDeposit.setMinimum(0);
		sliderDeposit.setValue(0);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PunterStats ps = new PunterStats(ph,os);
				os.setContentPane(ps);
				ps.setBounds(os.getBounds().x, os.getBounds().y, ps.getBounds().width,ps.getBounds().height);
				os.setBounds(ps.getBounds());
			}
		});
		btnCancel.setBounds(10, 228, 89, 23);
		contentPanel.add(btnCancel);
//		for(int i = 0; i<values.length;++i){
//		comboBoxWins.addItem(values[i]);
//		}
//		for(int i = 0; i<values.length;++i){
//			comboBoxLosses.addItem(values[i]);
//			}
		
		JButton btnAddWin = new JButton("Add win");
		btnAddWin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddWins aw = new AddWins(ph,Statseditor.this,os);
				os.setContentPane(aw);
				aw.setBounds(os.getBounds().x, os.getBounds().y, aw.getBounds().width,aw.getBounds().height);
				os.setBounds(aw.getBounds());
				
			}
		});
		btnAddWin.setBounds(10, 153, 89, 23);
		contentPanel.add(btnAddWin);
		
		JButton btnAddLoss = new JButton("Add loss");
		btnAddLoss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddLosses al = new AddLosses(ph,Statseditor.this,os);
				os.setContentPane(al);
				al.setBounds(os.getBounds().x, os.getBounds().y, al.getBounds().width,al.getBounds().height);
				os.setBounds(al.getBounds());
			}
		});
		btnAddLoss.setBounds(142, 153, 89, 23);
		contentPanel.add(btnAddLoss);
		
	}



	@Override
	public void call(PunterHandler ph) {
		// TODO Auto-generated method stub
		
	}
}
