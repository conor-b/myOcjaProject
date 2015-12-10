package com.bettingtracker;

public class Admin {

	private String username = "con90" ;
	private char[] password ={'1','2','3','a','b','c'};
	
	
	public Admin(String username,char[] password){
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	
	public char[] getPassword() {
		return password;
	}
	
	
	
}
