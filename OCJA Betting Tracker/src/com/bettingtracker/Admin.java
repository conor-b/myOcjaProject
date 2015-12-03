package com.bettingtracker;

public class Admin {

	private String username ;
	private String password ;
	
	public Admin(){
		this("unkown","unkown");
	}
	public Admin(String username,String password){
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	
}
