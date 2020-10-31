package com.toyrentalproject.model;

public class ToyRental {
	
	private int rentalId;
	private int customerId;
	private int toyId;
	private String startDate;
	private String endDate;
	private double rentalAmountPerDay;
	private double totalAmount;
	private double fine;
	private String status;
	
	public ToyRental(int rentalId, String startDate, String endDate, double rentalAmountPerDay, double totalAmount,
			double fine, String status) {
		super();
		this.rentalId = rentalId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rentalAmountPerDay = rentalAmountPerDay;
		this.totalAmount = totalAmount;
		this.fine = fine;
		this.status = status;
	}
	
	public ToyRental() {}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getToyId() {
		return toyId;
	}

	public void setToyId(int toyId) {
		this.toyId = toyId;
	}

	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public double getRentalAmountPerDay() {
		return rentalAmountPerDay;
	}
	public void setRentalAmountPerDay(double rentalAmountPerDay) {
		this.rentalAmountPerDay = rentalAmountPerDay;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return "Rental_id: "+this.rentalId+" CustomerId: "+this.customerId+" ToyId: "+this.toyId+" StartDate: "+this.startDate+" EndDate: "
				+this.endDate+" RentalAmount: "+this.rentalAmountPerDay+" Fine: "+this.fine+" Status: "+this.status;
	}
	
}
