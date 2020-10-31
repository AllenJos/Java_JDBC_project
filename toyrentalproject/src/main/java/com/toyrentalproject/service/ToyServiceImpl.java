package com.toyrentalproject.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.toyrentalproject.exceptions.InvalidAgeException;
import com.toyrentalproject.model.Customer;
import com.toyrentalproject.model.Toy;
import com.toyrentalproject.util.KeyboardUtil;

public class ToyServiceImpl implements ToyService {
	
	public List<Toy> toyArr = new ArrayList<Toy>();
	
	public boolean createToy() throws ClassNotFoundException, SQLException {	
		String name = KeyboardUtil.getString("Enter New Toy Name");
		String type = KeyboardUtil.getString("Enter the toy type:(Electronic/Musical) ");
		int minAge = KeyboardUtil.getInt("Enter the minimum age:");
		int maxAge = KeyboardUtil.getInt("Enter the maximum age:");
		double price = KeyboardUtil.getDouble("Enter the price: ");
		int quantity = KeyboardUtil.getInt("Enter the available quantity:");
		double rentalAmount = KeyboardUtil.getDouble("Enter the rental amount:");
		Toy newToy = new Toy(name, type, price, quantity, rentalAmount);
		try {
			Connection con = DAO.getConnection();
			newToy.setMinAge(minAge);
			newToy.setMaxAge(maxAge);
			String insertQuery = "insert into Toy(Toy_Name, Toy_Type, Min_Age, Max_Age, Price, Quantity, Rental_Amount) values"
					              + "(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(insertQuery);
			stmt.setString(1, newToy.getToyName());
			stmt.setString(2, newToy.getToyType());
			stmt.setInt(3, newToy.getMinAge());
			stmt.setInt(4, newToy.getMaxAge());
			stmt.setDouble(5, newToy.getPrice());
			stmt.setInt(6, newToy.getQuantity());
			stmt.setDouble(7, newToy.getRentalAmount());
			stmt.executeUpdate();
			return true;
		}
		catch(InvalidAgeException e) {
			System.out.println("Age should be between 0 and 12");
		}
		catch(Exception e) {
			System.out.println("Could not enter the toy in the database");
		}
		return false;
	}

	public void displayAllToys() {
		Connection con;
		try {
			con = DAO.getConnection();
			Statement stmt = con.createStatement();
			String query = "select * from Toy";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				Toy currentToy = new Toy();
				currentToy.setToyId(rs.getInt(1));
				currentToy.setToyName(rs.getString(2));
				currentToy.setToyType(rs.getString(3));
				currentToy.setMinAge(rs.getInt(4));
				currentToy.setMaxAge(rs.getInt(5));
				currentToy.setPrice(rs.getDouble(6));
				currentToy.setQuantity(rs.getInt(7));
				currentToy.setRentalAmount(rs.getDouble(8));
				toyArr.add(currentToy);
			}
			for(Toy toy: toyArr)
				System.out.println(toy);
		}
		catch(Exception e)
		{
			System.out.println("Couldn't fetch Toys!");
		}

	}

	public Toy getToy(int id) {
		Connection con;
		Toy currentToy = new Toy();
		try {
			con = DAO.getConnection();
			Statement stmt = con.createStatement();
			String query = "select * from Toy where Toy_Id='"+id+"'";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			currentToy.setToyId(rs.getInt(1));
			currentToy.setToyName(rs.getString(2));
			currentToy.setToyType(rs.getString(3));
			currentToy.setMinAge(rs.getInt(4));
			currentToy.setMaxAge(rs.getInt(5));
			currentToy.setPrice(rs.getDouble(6));
			currentToy.setQuantity(rs.getInt(7));
			currentToy.setRentalAmount(rs.getDouble(8));
		} catch (ClassNotFoundException e) {
			System.out.println("Can't connect to DataBase");
			return null;
		} catch (SQLException e) {
			System.out.println("Can't find the Toy please search again!");
			return null;
		}
		
		return currentToy;
	}

	public Toy getToy(String name) {
		Connection con;
		Toy currentToy = new Toy();
		try {
			con = DAO.getConnection();
			Statement stmt = con.createStatement();
			String query = "select * from Toy where Toy_Name='"+name+"'";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			currentToy.setToyId(rs.getInt(1));
			currentToy.setToyName(rs.getString(2));
			currentToy.setToyType(rs.getString(3));
			currentToy.setMinAge(rs.getInt(4));
			currentToy.setMaxAge(rs.getInt(5));
			currentToy.setPrice(rs.getDouble(6));
			currentToy.setQuantity(rs.getInt(7));
			currentToy.setRentalAmount(rs.getDouble(8));
		} catch (ClassNotFoundException e) {
			System.out.println("Can't connect to DataBase");
			return null;
		} catch (SQLException e) {
			System.out.println("Can't find the Toy please search again!");
			return null;
		}
		
		return currentToy;
	}
	
	public void deleteToy(int id)
	{
		Connection con;
		try {
			con = DAO.getConnection();
			Statement stmt = con.createStatement();
			String query = "delete from toy where Toy_Id="+id+"";
			stmt.executeUpdate(query);
			System.out.println("Selected Toy deleted successfully");
		}
		catch(Exception e) {
			System.out.println("Sorry could not delete the selected toy!");
		}
	}

}
