package com.toyrentalproject.model;

public class Musical extends Toy {

	private int noOfBatteries;
	private String noOfSpeakers;
	
	public Musical(int noOfBatteries, String noOfSpeakers) {
		super();
		this.noOfBatteries = noOfBatteries;
		this.noOfSpeakers = noOfSpeakers;
	}
	
	public int getNoOfBatteries() {
		return noOfBatteries;
	}
	public void setNoOfBatteries(int noOfBatteries) {
		this.noOfBatteries = noOfBatteries;
	}
	public String getNoOfSpeakers() {
		return noOfSpeakers;
	}
	public void setNoOfSpeakers(String noOfSpeakers) {
		this.noOfSpeakers = noOfSpeakers;
	}
	
	
}
