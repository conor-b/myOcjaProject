package com.bettingtracker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AddLosses extends JPanel {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldAmountLost;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public AddLosses(PunterHandler ph , Callable call , OpeningScreen os) {
		setBounds(100, 100, 252, 124);
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel lblAmountPutOn = new JLabel("Amount Put on");
			lblAmountPutOn.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(lblAmountPutOn);
		}
		{
			{
				textFieldAmountLost = new JTextField();
				textFieldAmountLost.setEditable(false);
				contentPanel.add(textFieldAmountLost);
				textFieldAmountLost.setColumns(10);
			}
		}
		{
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			JButton btnConfirm = new JButton("Confirm");
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(textFieldAmountLost.equals(null)){
						JOptionPane.showMessageDialog(AddLosses.this, "Please choose an amount from the slider");
					}else{
						ph.getCurrentPunter().setLoss(ph.getCurrentPunter().getLoss()+Double.parseDouble(textFieldAmountLost.getText()));
						ph.getCurrentPunter().setLosses(ph.getCurrentPunter().getLosses()+1);
						ph.getCurrentPunter().setBalance(ph.getCurrentPunter().getBalance()-Double.parseDouble(textFieldAmountLost.getText()));
						ph.getCurrentPunter().setProfit(ph.getCurrentPunter().getProfit()-Double.parseDouble(textFieldAmountLost.getText()));
						call.call(ph);
						Serializer.serialize(ph.getRealPunters(), "Punters.data");
						Statseditor se = new Statseditor(ph,call,os);
						os.setContentPane(se);
						se.setBounds(os.getBounds().x, os.getBounds().y, se.getBounds().width,se.getBounds().height);
						os.setBounds(se.getBounds());
						
					}
				}
			});
			{
				JSlider slider = new JSlider();
				slider.setValue(0);
				slider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						textFieldAmountLost.setText(""+slider.getValue());
					}
				});
				contentPanel.add(slider);
				slider.setMaximum((int)ph.getCurrentPunter().getBalance());
				
			}
			contentPanel.add(btnConfirm);
			JButton btnCancel = new JButton("Cancel");
			contentPanel.add(btnCancel);
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Statseditor se = new Statseditor(ph,call,os);
					os.setContentPane(se);
					se.setBounds(os.getBounds().x, os.getBounds().y, se.getBounds().width,se.getBounds().height);
					os.setBounds(se.getBounds());
					
				}
			});
		}
	}

}
