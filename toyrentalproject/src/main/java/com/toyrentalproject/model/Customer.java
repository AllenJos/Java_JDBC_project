package com.toyrentalproject.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.toyrentalproject.exceptions.InvalidNameException;

public class Customer {
	private int customerId;
	private String customerName;
	private String password;
	private String city;
	private String state;
	private int zip;
	private String country;
	
	public Customer(String password, String city, String state, int zip,
			String country) {
		super();
//		this.customerId = customerId;
		this.password = password;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCusomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		customerName = customerName.trim();
		Pattern pattern = Pattern.compile("[^a-zA-Z\\s]");
        Matcher matcher = pattern.matcher(customerName);
        boolean isStringContainsSpecialCharacter = matcher.find();
        if(isStringContainsSpecialCharacter || customerName.length()<6)
            throw new InvalidNameException("Name should not contain numbers or special characters and should be atleast 6 characters long!");
        else    
        	this.customerName = customerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toString() {
		return "Customer_Id: "+this.customerId+" Customer_Name: "+this.customerName+" City: "+this.city;
	}
	
}
