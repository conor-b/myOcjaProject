package com.bettingtracker;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PunterStats extends JPanel implements Callable{

	private final JPanel contentPanel = new JPanel();
	private ArrayList<JLabel> labelArr = new ArrayList<>();

	private JLabel lblAcName;
	private JLabel lblAcTotalWins;
	private JLabel lblAcTotalLosses;
	private JLabel lblAcBalance;
	private JLabel lblAcSuccessRate;
	private JLabel lblAcTotalProfit;
	
	/**
	 * Create the dialog.
	 */
	public PunterStats(final PunterHandler ph , OpeningScreen os) {
		setBounds(100, 100, 254, 247);
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(10, 25, 82, 14);
			contentPanel.add(lblName);
		}

		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(10, 50, 82, 14);
		contentPanel.add(lblBalance);

		JLabel lblSuccessRate = new JLabel("Success rate:");
		lblSuccessRate.setBounds(10, 75, 82, 14);
		contentPanel.add(lblSuccessRate);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Statseditor se = new Statseditor(ph,PunterStats.this,os);
				os.setContentPane(se);
				se.setBounds(os.getBounds().x, os.getBounds().y, se.getBounds().width,se.getBounds().height);
				os.setBounds(se.getBounds());
			}
		});
		btnEdit.setBounds(10, 175, 103, 23);
		contentPanel.add(btnEdit);

		JLabel lblTotalWins = new JLabel("Total wins:");
		lblTotalWins.setBounds(10, 100, 82, 14);
		contentPanel.add(lblTotalWins);

		JLabel lblTotalLosses = new JLabel("Total losses:");
		lblTotalLosses.setBounds(10, 125, 82, 14);
		contentPanel.add(lblTotalLosses);

		JLabel lblTotalProfit = new JLabel("Total profit:");
		lblTotalProfit.setBounds(10, 150, 82, 14);
		contentPanel.add(lblTotalProfit);

		lblAcName = new JLabel(ph.getCurrentPunter().getName());
		lblAcName.setBounds(116, 25, 96, 14);
		contentPanel.add(lblAcName);

//		new Thread(new Runnable(){
//
//			@Override
//			public void run() {
//				while(true){
//					try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					lblAcName.setText(ph.getCurrentPunter().getName());
//					System.out.println(ph.getCurrentPunter().getName());
//					lblTotalWins.setText(""+ph.getCurrentPunter().getWins());
//					lblTotalLosses.setText(""+ph.getCurrentPunter().getLosses());
//					lblBalance.setText(""+ph.getCurrentPunter().getBalance());
//					lblSuccessRate.setText(""+ph.getCurrentPunter().calculateSuccessRate());
//					lblTotalProfit.setText(""+ph.getCurrentPunter().getProfit());
//					lblTotalLoss.setText(""+ph.getCurrentPunter().getLoss());
//				}
//			}
//
//		}).start();


		lblAcBalance = new JLabel("€ "+ph.getCurrentPunter().getBalance());
		lblAcBalance.setBounds(116, 50, 96, 14);
		contentPanel.add(lblAcBalance);

		lblAcSuccessRate = new JLabel(ph.getCurrentPunter().calculateSuccessRate()+"%");
		lblAcSuccessRate.setBounds(116, 75, 96, 14);
		contentPanel.add(lblAcSuccessRate);

		lblAcTotalWins = new JLabel(ph.getCurrentPunter().getWins()+"");
		lblAcTotalWins.setBounds(116, 100, 96, 14);
		contentPanel.add(lblAcTotalWins);

		lblAcTotalLosses = new JLabel(ph.getCurrentPunter().getLosses()+"");
		lblAcTotalLosses.setBounds(116, 125, 96, 14);
		contentPanel.add(lblAcTotalLosses);

		lblAcTotalProfit = new JLabel("€ "+ph.getCurrentPunter().getProfit());
		lblAcTotalProfit.setBounds(116, 150, 96, 14);
		contentPanel.add(lblAcTotalProfit);
		
		JLabel lblPunterStats = new JLabel(ph.getCurrentPunter().getName()+"'s stats");
		lblPunterStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblPunterStats.setBounds(0, 0, 254, 14);
		contentPanel.add(lblPunterStats);
		
		JButton btnPunterList = new JButton("Punter list");
		btnPunterList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainScreen ms = new MainScreen(os);
				os.setContentPane(ms);
				ms.setBounds(os.getBounds().x, os.getBounds().y, ms.getBounds().width,ms.getBounds().height);
				os.setBounds(ms.getBounds());
			}
		});
		btnPunterList.setBounds(126, 175, 103, 23);
		contentPanel.add(btnPunterList);
		
		
	}

	public String checkDecimal(double d){
		String temp = String.valueOf(d);
		int indexOfDecimal = temp.indexOf('.');
		if(indexOfDecimal==temp.length()-2){
			temp=temp+"0";
			return temp;
		} 

		return "" + d;
	}


	@Override
	public void call(PunterHandler ph) {
		lblAcName.setText(ph.getCurrentPunter().getName());
		//System.out.println(ph.getCurrentPunter().getName());
		lblAcTotalWins.setText(""+ph.getCurrentPunter().getWins());
		lblAcTotalLosses.setText(""+ph.getCurrentPunter().getLosses());
		lblAcBalance.setText("€ "+ph.getCurrentPunter().getBalance());
		lblAcSuccessRate.setText(checkDecimal(ph.getCurrentPunter().calculateSuccessRate())+"%");
		lblAcTotalProfit.setText("€ "+ph.getCurrentPunter().getProfit());
		
	}
}
