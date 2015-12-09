package com.bettingtracker;

import java.util.ArrayList;

public class PunterHandler {
	private ArrayList<Punter> realPunters= new ArrayList<Punter>();
	private Punter currentPunter ;
	
	PunterHandler(ArrayList<Punter>realPunters){
		this.realPunters = realPunters;
	}
	
	public ArrayList<String> getPunterNames(){
		
		ArrayList<String> names = new ArrayList<>();
		
		for(Punter p : realPunters){
			names.add(p.getName());
		}
		
		return names;
		
	}

	public ArrayList<Punter> getRealPunters() {
		return realPunters;
	}

	public void setRealPunters(ArrayList<Punter> realPunters) {
		this.realPunters = realPunters;
	}

	public Punter getCurrentPunter() {
		return currentPunter;
	}

	public void setCurrentPunter(Punter currentPunter) {
		this.currentPunter = currentPunter;
	}
	
}
