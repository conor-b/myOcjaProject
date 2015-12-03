package com.bettingtracker;

import java.io.Serializable;

public class Punter implements Serializable {

	private String name;
	private int balance;
	private int profit;
	private int loss;
	private int wins;
	private int losses;
	private double successRate;

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
		this.successRate = wins/(wins+losses)*100;
		return successRate;
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
	public double getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}
	public int getBalance() {
		return balance;
	}
	public int getProfit() {
		return profit;
	}
	public int getLoss() {
		return loss;
	}
	public void setName(String temName) {
		name = temName;
	}
	public void setBalance(int temInvestment) {
		balance = temInvestment;
	}
	public void setProfit(int temProfit) {
		profit = temProfit;
	}
	public void setLoss(int temloss) {
		loss = temloss;
	}

}
