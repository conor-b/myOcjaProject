package com.bettingtracker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class PunterStats extends JDialog {

	private final JPanel contentPanel = new JPanel();

	

	/**
	 * Create the dialog.
	 */
	public PunterStats(Punter p) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(10, 11, 82, 14);
			contentPanel.add(lblName);
		}
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(10, 36, 82, 14);
		contentPanel.add(lblBalance);
		
		JLabel lblSuccessRate = new JLabel("Success rate:");
		lblSuccessRate.setBounds(10, 61, 82, 14);
		contentPanel.add(lblSuccessRate);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(290, 0, 14, 262);
		contentPanel.add(separator_1);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(323, 7, 89, 23);
		contentPanel.add(btnEdit);
		
		JLabel lblTotalWins = new JLabel("Total wins:");
		lblTotalWins.setBounds(10, 86, 82, 14);
		contentPanel.add(lblTotalWins);
		
		JLabel lblTotalLosses = new JLabel("Total losses:");
		lblTotalLosses.setBounds(10, 111, 82, 14);
		contentPanel.add(lblTotalLosses);
		
		JLabel lblTotalProfit = new JLabel("Total profit:");
		lblTotalProfit.setBounds(10, 136, 82, 14);
		contentPanel.add(lblTotalProfit);
		
		JLabel lblTotalLoss = new JLabel("Total loss:");
		lblTotalLoss.setBounds(10, 161, 82, 14);
		contentPanel.add(lblTotalLoss);
		
		JLabel lblAcName = new JLabel(p.getName());
		lblAcName.setBounds(116, 11, 46, 14);
		contentPanel.add(lblAcName);
		
		
		JLabel lblAcBalnce = new JLabel("€"+p.getBalance());
		lblAcBalnce.setBounds(116, 36, 46, 14);
		contentPanel.add(lblAcBalnce);
		
		JLabel lblAcSuccessRate = new JLabel(p.calculateSuccessRate()+"%");
		lblAcSuccessRate.setBounds(116, 61, 46, 14);
		contentPanel.add(lblAcSuccessRate);
		
		JLabel lblAcWins = new JLabel(p.getWins()+"");
		lblAcWins.setBounds(116, 86, 46, 14);
		contentPanel.add(lblAcWins);
		
		JLabel lblAcLosses = new JLabel(p.getLosses()+"");
		lblAcLosses.setBounds(116, 111, 46, 14);
		contentPanel.add(lblAcLosses);
		
		JLabel lblAcProfit = new JLabel("€"+p.getProfit());
		lblAcProfit.setBounds(116, 136, 46, 14);
		contentPanel.add(lblAcProfit);
		
		JLabel lblAcLoss = new JLabel("€"+p.getLoss());
		lblAcLoss.setBounds(116, 161, 46, 14);
		contentPanel.add(lblAcLoss);
	}
}
