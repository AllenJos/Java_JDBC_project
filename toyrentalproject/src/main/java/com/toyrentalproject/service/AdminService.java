package com.toyrentalproject.service;

public interface AdminService {
	
	public boolean auth(String uName, String password);
	public boolean adminLogin();
	public void adminOptions(ToyService toyService);
}
