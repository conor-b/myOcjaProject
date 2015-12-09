package com.bettingtracker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class OddsCalculator extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldAmountPutOn;
	private String[] odds = {"1/1","2/1","3/1","4/1","5/1"};
	private JTextField textFieldOdds;
	private DecimalFormat df = new DecimalFormat("#.##");








	/**
	 * Create the dialog.
	 */
	public OddsCalculator(PunterHandler ph ) {
		setBounds(100, 100, 450, 219);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblAmountPutOn = new JLabel("Amount put on");
		lblAmountPutOn.setBounds(0, 25, 97, 14);
		contentPanel.add(lblAmountPutOn);

		JLabel lblOdds = new JLabel("Odds");
		lblOdds.setBounds(0, 50, 48, 14);
		contentPanel.add(lblOdds);

		JLabel lblReturned = new JLabel("Returned");
		lblReturned.setBounds(107, 75, 86, 14);
		contentPanel.add(lblReturned);

		JLabel lblGained = new JLabel("Gained");
		lblGained.setBounds(107, 100, 86, 14);
		contentPanel.add(lblGained);

		JLabel lblMoneyReturned = new JLabel("Money returned");
		lblMoneyReturned.setBounds(2, 75, 95, 14);
		contentPanel.add(lblMoneyReturned);

		JLabel lblAmountGained = new JLabel("Amount gained");
		lblAmountGained.setBounds(0, 100, 97, 14);
		contentPanel.add(lblAmountGained);

		textFieldAmountPutOn = new JTextField();
		textFieldAmountPutOn.setEditable(false);
		textFieldAmountPutOn.setBounds(107, 25, 86, 20);
		contentPanel.add(textFieldAmountPutOn);
		textFieldAmountPutOn.setColumns(10);

		JSlider sliderAmountPutOn = new JSlider();
		sliderAmountPutOn.setValue(0);
		sliderAmountPutOn.setMaximum(500);
		sliderAmountPutOn.setBounds(212, 25, 200, 23);
		contentPanel.add(sliderAmountPutOn);
		sliderAmountPutOn.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				textFieldAmountPutOn.setText(""+sliderAmountPutOn.getValue());
				
			}
		});
		
		


		textFieldOdds = new JTextField();
		textFieldOdds.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!textFieldOdds.equals("")&containsSlash(textFieldOdds.getText())){
					
					double multpliyer = calculateOdds(textFieldOdds.getText());
					double putOn = Double.parseDouble(textFieldAmountPutOn.getText());

					lblReturned.setText("� "+ checkDecimal(Double.parseDouble(df.format((putOn+(putOn*multpliyer))))));
					lblGained.setText("� "+ checkDecimal(Double.parseDouble(df.format((putOn*multpliyer)))));
					
					//ph.getCurrentPunter().setBalance(Double.parseDouble(df.format((putOn*multpliyer))));
					
				}
			}
		});
		textFieldOdds.setBounds(107, 47, 86, 20);
		contentPanel.add(textFieldOdds);
		textFieldOdds.setColumns(10);
		
		JLabel lblOddsCalculator = new JLabel("Odds Calculator");
		lblOddsCalculator.setHorizontalAlignment(SwingConstants.CENTER);
		lblOddsCalculator.setBounds(0, 0, 434, 14);
		contentPanel.add(lblOddsCalculator);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOkay.setBounds(51, 147, 89, 23);
		contentPanel.add(btnOkay);
	

	}
	public double calculateOdds(Object s){
		int marker = 0;
		//		s = comboBoxOdds.getSelectedItem().toString();
		s = textFieldOdds.getText();
		System.out.println(s);
		marker = ((String )s).indexOf('/');
		((String )s).replace('/',' ');
		String s1 = ((String )s).substring(0, marker);
		String s2 = ((String )s).substring(marker+1);
		double leftOdds = Double.parseDouble(s1);
		double rightOdds = Double.parseDouble(s2);
		System.out.println(""+leftOdds/rightOdds);
		return leftOdds/rightOdds ;
	}
	
	public boolean containsSlash(String s){
		for(int i = 1;i<s.length();++i){
			if(s.charAt(s.length()-1)=='/'){
				return false;

			}else if(s.charAt(i)=='/'){
				return true;
			}

		}
		return false;
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
}