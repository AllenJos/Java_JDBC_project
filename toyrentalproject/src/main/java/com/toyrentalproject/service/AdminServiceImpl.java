package com.toyrentalproject.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.toyrentalproject.model.Admin;
import com.toyrentalproject.model.Toy;
import com.toyrentalproject.util.KeyboardUtil;

public class AdminServiceImpl implements AdminService {

	Admin admin = new Admin();
	public boolean auth(String uName, String password) {
		try {
			Connection con = DAO.getConnection();
			Statement stmt = con.createStatement();
			String query = "select * from Admin where Username='"+uName+"'";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			admin.setUserName(rs.getString(1));
			admin.setPassword(rs.getString(2));
		}
		catch(Exception e) {
			System.out.println("Error while authenticating");
		}
		return false;
	}

	public boolean adminLogin() {
		String uName = KeyboardUtil.getString("Enter admin user name:(*enter admin)");
		String password = KeyboardUtil.getString("Enter password:(*enter admin)");
		boolean authenticate = auth(uName, password);
		if(uName.equals(admin.getUserName()) && password.equals(admin.getPassword()))
			return true;
		
		return false;
	}

	public void adminOptions(ToyService toyService) {
	   boolean play = true;
	   Toy toy = new Toy();
	   while(play==true)
	   {
		   System.out.println("*****************************************************************");
		   System.out.println("\t\t1. Register a New Toy");
		   System.out.println("\t\t2. Get Toy Details");
		   System.out.println("\t\t3. Delete a Toy");
		   int choice = KeyboardUtil.getInt("Enter your option:");
		   switch(choice)
		   {
		      case 1: try {
							boolean created = toyService.createToy();
						} catch (ClassNotFoundException e) {
							System.out.println("Couldn't access database");
						} catch (SQLException e) {
							System.out.println("Couldn't register Toy");
						}
		      			break;
		      			
		      case 2:   int id = KeyboardUtil.getInt("Enter the toy Id:");
		      			toy = toyService.getToy(id);
		      			System.out.println(toy);
		      			break;
		      			
		      case 3:   toyService.displayAllToys();
		    	        int id2 = KeyboardUtil.getInt("Enter the toy Id:");
		    			toyService.deleteToy(id2);
		    			break;
		      default: System.out.println();
		   }
		   char ch = KeyboardUtil.getChar("Press N to go back! or any other key to continue as Admin");
			if(ch=='N'|| ch=='n')
				play=false;
	   }
	   
	   return;
	}

}
