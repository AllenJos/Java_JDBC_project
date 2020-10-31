package com.toyrentalproject.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.toyrentalproject.exceptions.InvalidNameException;
import com.toyrentalproject.model.Customer;
import com.toyrentalproject.model.Toy;
import com.toyrentalproject.util.KeyboardUtil;


public class CustomerServiceImpl implements CustomerService {
	
	static List<Customer> customerArr = new ArrayList<Customer>();
	Scanner sc = new Scanner(System.in);
	String loggedInName;
	public Customer getCustomer(int id) {
		Customer currentCustomer = new Customer();
		try {
			Connection con = DAO.getConnection();
			Statement stmt = con.createStatement();
			String query = "select * from Customer where Customer_Id='"+id+"'";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			currentCustomer.setCustomerId(rs.getInt(1));
			currentCustomer.setCustomerName(rs.getString(2));
			currentCustomer.setPassword(rs.getString(3));
			currentCustomer.setCity(rs.getString(4));
			currentCustomer.setState(rs.getString(5));
			currentCustomer.setZip(rs.getInt(6));
			currentCustomer.setCountry(rs.getString(7));
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver class not loaded");
			return null;
		} catch (SQLException e) {
			System.out.println("Invalid Customer Id");
			return null;
		}
		
		return currentCustomer;
	}
	public Customer getCustomer(String name) {
		Customer currentCustomer = new Customer();
		try {
			Connection con = DAO.getConnection();
			Statement stmt = con.createStatement();
			String query = "select * from Customer where Customer_Name='"+name+"'";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			currentCustomer.setCustomerId(rs.getInt(1));
			currentCustomer.setCustomerName(rs.getString(2));
			currentCustomer.setPassword(rs.getString(3));
			currentCustomer.setCity(rs.getString(4));
			currentCustomer.setState(rs.getString(5));
			currentCustomer.setZip(rs.getInt(6));
			currentCustomer.setCountry(rs.getString(7));
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class not loaded");
			return null;
		} catch (SQLException e) {
			System.out.println("Invalid Customer Name");
			return null;
		}
		
		return currentCustomer;
	}
	
	public void displayAllCustomers() {
		if(customerArr.size()==0)
		{
			System.out.println("No Customers yet.");
			return;
		}
		for(Customer customer: customerArr)
		{
			System.out.println(customer);
		}
	}

	public boolean createCustomer() throws ClassNotFoundException, SQLException {
         // Id is auto Incremented in the database
		String name = KeyboardUtil.getString("Enter your name");
		String password = KeyboardUtil.getString("Enter your password");
		String city = KeyboardUtil.getString("Enter your city");
		String state = KeyboardUtil.getString("Enter your state");
		int zip = KeyboardUtil.getInt("Enter ZIP");
		String country = KeyboardUtil.getString("Enter your country");
		
		Customer newCust = new Customer(password, city, state, zip, country);
		try {
			Connection con = DAO.getConnection();
			newCust.setCustomerName(name);
			String insertQuery = "insert into Customer(Customer_Name, Password, City, State, Zip, Country) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, newCust.getCusomerName());
			stmt.setString(2, newCust.getPassword());
			stmt.setString(3, newCust.getCity());
			stmt.setString(4, newCust.getState());
			stmt.setInt(5, newCust.getZip());
			stmt.setString(6, newCust.getCountry());
			stmt.executeUpdate();
			return true;
		}
		catch(InvalidNameException e) {
			System.out.println("Name should not contain numbers or special characters and should be atleast 6 characters long!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		return false;
	}
	
	public boolean customerLogin() {
		
		String loginName = KeyboardUtil.getString("Enter Customer Name:");
		String loginPassword = KeyboardUtil.getString("Enter account Password:");
		Customer currentCustomer = this.getCustomer(loginName);
		if(currentCustomer.getCusomerName().equals(loginName) && loginPassword.equals(currentCustomer.getPassword()))
		{
			loggedInName = currentCustomer.getCusomerName();
			return true;
		}
		else 
			System.out.println("Wrong username password");
		
		return false;
	}
	
	public void customerOptions(ToyService toyService, ToyRentalService toyRentalService) {
		boolean play = true;
		Toy toy = new Toy();
		while(play)
		{
			System.out.println("************************************************");
			System.out.println("\t\t1. Book a Toy");
			System.out.println("\t\t2. Search a Toy by it's name");
			System.out.println("\t\t3. Search a Toy by it's Id");
			System.out.println("\t\t4. Return a Toy");
			System.out.println("\t\t5. Get your Rental Details:");
			System.out.println("\t\t6. Get you Total Rent Amount(Excluding Returned):");
			System.out.println("\t\t7. Get a Particular Toys Rent Details");
			int choice = KeyboardUtil.getInt("Enter your option:");
			switch(choice)
			{
			   case 1: toyService.displayAllToys();
			   		   String toyName = KeyboardUtil.getString("Enter the toy Name you want to Rent:");
			   		   int weeks = KeyboardUtil.getInt("Enter the number of weeks you want to book for:");
			   		   toyRentalService.bookToy(this, toyName, weeks, toyService, loggedInName);
			   		   break;
			   
			   case 2: String toyName1 = KeyboardUtil.getString("Enter the toy Name you want to search for:");
			   		   toy = toyService.getToy(toyName1);
			   		   System.out.println(toy);
			           break;
			   
			   case 3: int toyId = KeyboardUtil.getInt("Enter the toy Id you want to search for:");
				       toy = toyService.getToy(toyId);
			   		   System.out.println(toy);
			           break;
			           
			   case 4: toyRentalService.returnToy(this, toyService, loggedInName);
			   		   break;
			   
			   case 5: toyRentalService.getRentalDetails(this, toyService, loggedInName);
			   		   break;
			   		   
			   case 6: toyRentalService.getTotalRentAmount(this, toyService, loggedInName);
			   		   break;
			   
			   case 7: toyRentalService.toyRentDetails(toyService);
			}
			char ch = KeyboardUtil.getChar("Press N to go back! or Press any key to continue shopping");
			if(ch=='N'|| ch=='n')
				play=false;
		}
		
	}
	

}
