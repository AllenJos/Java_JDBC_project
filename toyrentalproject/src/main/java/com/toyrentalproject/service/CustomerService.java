package com.toyrentalproject.service;

import java.sql.SQLException;
import java.util.List;

import com.toyrentalproject.model.Customer;

public interface CustomerService {

	public Customer getCustomer(String name);
	public Customer getCustomer(int id);
	public void customerOptions(ToyService toyService, ToyRentalService toyRentalService);
	public boolean createCustomer() throws ClassNotFoundException, SQLException;
	public void displayAllCustomers();
	public boolean customerLogin();
}
