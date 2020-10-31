package com.toyrentalproject.service;

import com.toyrentalproject.model.Customer;

public interface ToyRentalService {
	public void bookToy(CustomerService customerService, String toyName, int weeks, ToyService toyService, String cusName);
	public void returnToy(CustomerService customerService, ToyService toyService, String cusName);
	public void getTotalRentAmount(CustomerService customerService, ToyService toyService, String cusName);
	public void getRentalDetails(CustomerService customerService, ToyService toyService, String cusName);
	public void toyRentDetails(ToyService toyService);
}
