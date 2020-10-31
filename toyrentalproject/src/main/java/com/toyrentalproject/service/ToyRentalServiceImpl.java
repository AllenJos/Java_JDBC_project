package com.toyrentalproject.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.toyrentalproject.model.Customer;
import com.toyrentalproject.model.Toy;
import com.toyrentalproject.model.ToyRental;
import com.toyrentalproject.util.DateUtil;
import com.toyrentalproject.util.KeyboardUtil;

public class ToyRentalServiceImpl implements ToyRentalService {
	ToyRental rental = new ToyRental();
	
	Toy currentToy = new Toy();
	public void bookToy(CustomerService customerService,String toyName, int weeks, ToyService toyService, String cusName) {
		Connection con;
		Toy toy = new Toy();
		Customer customer = new Customer();
		try {
			con = DAO.getConnection();
			String insertQuery = "insert into Toy_Rental(Customer_Id, Toy_Id, Rental_Start_Date, Rental_End_Date, Rental_Amount, "
				                     	+ "Total_Amount, Fine, Status) values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			toy = toyService.getToy(toyName);
			customer = customerService.getCustomer(cusName);
			stmt.setInt(1, customer.getCustomerId());
			stmt.setInt(2, toy.getToyId());
			stmt.setDate(3, new Date(System.currentTimeMillis()));
			stmt.setDate(4, DateUtil.calculateDate(weeks));	
			stmt.setDouble(5, toy.getRentalAmount());
			stmt.setDouble(6, 7*weeks*toy.getRentalAmount());
			stmt.setDouble(7, toy.getRentalAmount()*0);
			stmt.setString(8, "Rented");
			stmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Sorry could not book your Toy! Try Again");
		}
		
	}
	public void returnToy(CustomerService customerService, ToyService toyService, String cusName) {
		Connection con;
		Toy toy = new Toy();
		List<ToyRental> rentalArr = new ArrayList<ToyRental>();
		Customer customer = new Customer();
		try {
			con = DAO.getConnection();
			customer = customerService.getCustomer(cusName);
			Statement stmt = con.createStatement();
			String query = "select * from Toy_Rental where Customer_Id='"+customer.getCustomerId()+"'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				rental = new ToyRental();
				rental.setRentalId(rs.getInt(1));
				rental.setCustomerId(rs.getInt(2));
				rental.setToyId(3);
				rental.setStartDate(rs.getDate(4).toString());
				rental.setEndDate(rs.getDate(5).toString());
				rental.setRentalAmountPerDay(rs.getDouble(6));
				rental.setTotalAmount(rs.getDouble(7));
				rental.setFine(rs.getDouble(8));
				rental.setStatus(rs.getString(9));
				rentalArr.add(rental);
			}
			
			for(ToyRental rental: rentalArr)
				System.out.println(rental);
			
		}
		catch(Exception e)
		{
			System.out.println("Sorry could not book your Toy! Try Again");
		}
		
		try {
			con = DAO.getConnection();
			customer = customerService.getCustomer(cusName);
			Statement newstmt = con.createStatement();
			int id = KeyboardUtil.getInt("Enter the Rental Id to return the toy!");
			String query = "update Toy_Rental set Status='Returned' where Rental_Id='"+id+"'";
			newstmt.executeUpdate(query);
		}
		catch(Exception e) {
			System.out.println("Sorry was not able to return your toy!");
		}
	}
	public void getTotalRentAmount(CustomerService customerService, ToyService toyService, String cusName) {
		Customer customer = customerService.getCustomer(cusName);
		Connection con;
		try {
			con = DAO.getConnection();
			Statement stmt = con.createStatement();
			int id = customer.getCustomerId();
			String query = "select sum(Total_Amount) from Toy_Rental where Customer_Id="+id+" and Status='Rented'";
			ResultSet rs = stmt.executeQuery(query);
			int result=0;
			while(rs.next())
			{
				result = rs.getInt(1);
			}
			System.out.println(customer.getCusomerName()+"'s Total Rent Amount: "+result);
			
		}
		catch(Exception e) {
			System.out.println("Sorry was not able to fetch the results. Try Again!");
		}
		
	}
	public void getRentalDetails(CustomerService customerService, ToyService toyService, String cusName) {
		Customer customer = customerService.getCustomer(cusName);
		Connection con;
		List<ToyRental> rentalArr = new ArrayList<ToyRental>();
		try {
			con = DAO.getConnection();
			Statement stmt = con.createStatement();
			int id = customer.getCustomerId();
			String query = "select * from Toy_Rental where Customer_Id="+id+"";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				rental = new ToyRental();
				rental.setRentalId(rs.getInt(1));
				rental.setCustomerId(rs.getInt(2));
				rental.setToyId(3);
				rental.setStartDate(rs.getDate(4).toString());
				rental.setEndDate(rs.getDate(5).toString());
				rental.setRentalAmountPerDay(rs.getDouble(6));
				rental.setTotalAmount(rs.getDouble(7));
				rental.setFine(rs.getDouble(8));
				rental.setStatus(rs.getString(9));
				rentalArr.add(rental);
			}
			for(ToyRental rental: rentalArr)
				System.out.println(rental);
			
		}
		catch(Exception e) {
			System.out.println("Sorry was not able to fetch the results. Try Again!");
		}
		
	}
	public void toyRentDetails(ToyService toyService) {
		toyService.displayAllToys();
		int id = KeyboardUtil.getInt("Enter toys Id");
		Toy toy = toyService.getToy(id);
		System.out.println(toy);
		
	}
}
