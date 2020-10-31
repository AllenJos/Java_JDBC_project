package com.toyrentalproject;

import java.sql.SQLException;

import com.toyrentalproject.service.AdminService;
import com.toyrentalproject.service.AdminServiceImpl;
import com.toyrentalproject.service.CustomerService;
import com.toyrentalproject.service.CustomerServiceImpl;
import com.toyrentalproject.service.ToyRentalService;
import com.toyrentalproject.service.ToyRentalServiceImpl;
import com.toyrentalproject.service.ToyService;
import com.toyrentalproject.service.ToyServiceImpl;
import com.toyrentalproject.util.KeyboardUtil;

public class TestApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		CustomerService customerService = new CustomerServiceImpl();
		ToyService toyService = new ToyServiceImpl();
		ToyRentalService toyRentalService = new ToyRentalServiceImpl();
		AdminService adminService = new AdminServiceImpl();
		boolean play = true;
		while(play)
		{
			System.out.println("\t\t1. Customer Login");
			System.out.println("\t\t2. New Customer");
			System.out.println("\t\t3. Admin");
			int option = KeyboardUtil.getInt("\tChoose one option {Enter option number}");
			switch(option)
			{
				case 1: boolean loggedIn = customerService.customerLogin();
						if(loggedIn)
						{
							System.out.println("Logged In Successfully");
							customerService.customerOptions(toyService, toyRentalService);
						}
						break;
				
				case 2: if(customerService.createCustomer())
							System.out.println("Customer Account created successfully!");
				        else
				        	System.out.println("Problem with creating account");
						break;
			
				case 3: boolean adminLogIn = adminService.adminLogin();
				        if(adminLogIn)
				        {
				        	System.out.println("Admin Logged In Successfully");
				        	adminService.adminOptions(toyService);
				        }
				        break;
				        
				default: System.out.println("Default");
			}
			char ch = KeyboardUtil.getChar("Do you want to continue Exploring?(Y/N)");
			if(ch=='N'|| ch=='n')
				play=false;
		}
	}

}
