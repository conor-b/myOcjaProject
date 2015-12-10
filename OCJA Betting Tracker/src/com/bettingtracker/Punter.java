package com.bettingtracker;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Punter implements Serializable {

	private String name;
	private double balance;
	private double profit;
	private double loss;
	private int wins;
	private int losses;
	private double successRate;
	private DecimalFormat df = new DecimalFormat("#.##");


	//	Punter(String name ){
	//		this.name = name;
	//		
	//	}
	public double calculateSuccessRate(){
		double wins = this.wins;
		double losses = this.losses;
		if(wins==0){
			return 0;
		}
		this.successRate = (wins/(wins+losses))*100;
		return Double.parseDouble(df.format(successRate));
	}
	public String getName() {
		return name;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
//	public double getSuccessRate() {
//		return successRate;
//	}
	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}
	public double getBalance() {
		return balance;
	}
	public double getProfit() {
		return profit;
	}
	public double getLoss() {
		return loss;
	}
	public void setName(String temName) {
		name = temName;
	}
	public void setBalance(double temInvestment) {
		balance = temInvestment;
	}
	public void setProfit(double temProfit) {
		profit =  temProfit ;
	}
	public void setLoss(double temloss) {
		loss = temloss;
	}
	
	
}
