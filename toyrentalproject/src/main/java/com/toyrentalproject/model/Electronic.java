package com.toyrentalproject.model;

public class Electronic extends Toy {
	private int noOfBatteries;
	private String operatingMode;
	
	public Electronic(String toyName, String toyType, double price, int quantity, double rentalAmount,
			int noOfBatteries, String operatingMode) {
		super(toyName, toyType, price, quantity, rentalAmount);
		this.noOfBatteries = noOfBatteries;
		this.operatingMode = operatingMode;
	}
	
	public int getNoOfBatteries() {
		return noOfBatteries;
	}
	public void setNoOfBatteries(int noOfBatteries) {
		this.noOfBatteries = noOfBatteries;
	}
	public String getOperatingMode() {
		return operatingMode;
	}
	public void setOperatingMode(String operatingMode) {
		this.operatingMode = operatingMode;
	}
	
	
}
