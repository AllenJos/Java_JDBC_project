package com.toyrentalproject.model;

import com.toyrentalproject.exceptions.InvalidAgeException;

public class Toy {
	
	private int toyId;
	private String toyName;
	private String toyType;
	private int minAge;
	private int maxAge;
	private double price;
	private int quantity;
	private double rentalAmount;
	
	public Toy(String toyName, String toyType, double price, int quantity,
			double rentalAmount) {
		super();
		this.toyName = toyName;
		this.toyType = toyType;
		this.price = price;
		this.quantity = quantity;
		this.rentalAmount = rentalAmount;
	}
	public Toy() {
		// TODO Auto-generated constructor stub
	}
	public int getToyId() {
		return toyId;
	}
	public void setToyId(int toyId) {
		this.toyId = toyId;
	}
	public String getToyName() {
		return toyName;
	}
	public void setToyName(String toyName) {
		this.toyName = toyName;
	}
	public String getToyType() {
		return toyType;
	}
	public void setToyType(String toyType) {
		this.toyType = toyType;
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		if(minAge>=0 && minAge<=12)
			this.minAge = minAge;
		else
			throw new InvalidAgeException("Sorry! Can't Register - Age should be between 0 and 12");
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		if(maxAge>=0 && maxAge<=12)
			this.maxAge = maxAge;
		else
			throw new InvalidAgeException("Sorry! Can't Register - Age should be between 0 and 12");
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getRentalAmount() {
		return rentalAmount;
	}
	public void setRentalAmount(double rentalAmount) {
		this.rentalAmount = rentalAmount;
	}
	
	public String toString() {
		return "Toy_Id: "+this.toyId+" Toy_Name: "+this.toyName+" Toy_Type: "+this.toyType+" Rental Amount: "+this.rentalAmount
				 +" Quantity: "+this.quantity+"\n";
	}
	
}
