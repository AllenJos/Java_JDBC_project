package com.toyrentalproject.service;

import java.sql.SQLException;

import com.toyrentalproject.model.Customer;
import com.toyrentalproject.model.Toy;

public interface ToyService {
	
	public Toy getToy(int id);
	public Toy getToy(String name);
	public boolean createToy() throws ClassNotFoundException, SQLException;
	public void displayAllToys();
	public void deleteToy(int id);
}
