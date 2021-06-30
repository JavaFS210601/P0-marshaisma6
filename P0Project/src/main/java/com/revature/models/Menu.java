package com.revature.models;

import java.util.Scanner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.CustomerDao;

public class Menu {
	

	public void display() { 
		
	
		boolean displayMenu = true;
		Scanner scan = new Scanner(System.in);
		CustomerDao daoImpli = new CustomerDao();
		final Logger log = LogManager.getLogger(Menu.class);
		
		
		//greeting
		System.out.println("Welcome to Isma Bank! Which service did you need help with today?");
		System.out.println("===============================================================");
		
		
		while (displayMenu) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("ENTER AN OPTION");
			System.out.println("-----------------------------------------------------------");
			
			
			
		//menu options
			System.out.println("A. Check Balance");
			System.out.println("B. Deposit");
			System.out.println("C. Withdraw");
			System.out.println("D. Register");
			System.out.println("E. Exit Menu");
	
		//parsing user input 
			String input = scan.nextLine();
			
			
			switch(input) {
			
			case "A" : {
				System.out.println("-----------------------------------------------------");
				System.out.println("Please enter your account number to check your current balance: ");
				String account_no_string = scan.nextLine();
				int account_no = Integer.parseInt(account_no_string);
				System.out.println("-----------------------------------------------------");
				double balance= daoImpli.getBalance(account_no);
				System.out.println("Your Checking Account Balance is: " +balance);
				System.out.println("\n");
				break;
				
			}
				
			case "B" : {
				System.out.println("-----------------------------------------------------");
				System.out.println("Please enter your account number: ");
				String account_no_string = scan.nextLine();
				int account_no = Integer.parseInt(account_no_string);
				
				System.out.println("-----------------------------------------------------");
				System.out.println("Thank you! Now please enter the amount you would like to deposit:");
				System.out.println("-----------------------------------------------------");
				String amount =scan.nextLine();
				daoImpli.deposit(account_no,Integer.parseInt(amount));
				log.info("Customer has completed a new transaction");
				System.out.println("\n");
				break;
			}	
			
			case "C" : {
				
				System.out.println("-----------------------------------------------------");
				System.out.println("Please enter your account number: ");
				String account_no_string = scan.nextLine();
				int account_no = Integer.parseInt(account_no_string);
				
				System.out.println("-----------------------------------------------------");
				System.out.println("Thank you! Now please enter the amount you would like to withdraw:");
				System.out.println("-----------------------------------------------------");
				String amount =scan.nextLine();
				daoImpli.withdraw(account_no,Integer.parseInt(amount));
				//withdraw(amount2);
				log.info("Customer has completed a new transaction");
				System.out.println("\n");
				break;
			}
			
			case "D" : {
				System.out.println("-----------------------------------------------------");
				System.out.println("Hi New Customer! Please input the username you would like to add");
				System.out.println("-----------------------------------------------------");
				String username =scan.nextLine();
				System.out.println("Please enter a password for your account");
				System.out.println("-----------------------------------------------------");
				String password =scan.nextLine();
				System.out.println("Please enter your first name");
				System.out.println("-----------------------------------------------------");
				String fName = scan.nextLine();
				System.out.println("Please enter your last name ");
				System.out.println("-----------------------------------------------------");
				String lName = scan.nextLine();
				System.out.println("Please enter your age");
				System.out.println("-----------------------------------------------------");
				String age = scan.nextLine();
				System.out.println("Please enter your email adress");
				System.out.println("-----------------------------------------------------");
				String email = scan.nextLine();
				
				Customer newCust = new Customer(username, password, fName, lName, Integer.parseInt(age), email);
				daoImpli.brandNewCustomer(newCust);
				System.out.println("Congratulations, you have been successfully added to our customer database!");
				
				System.out.println("\n"); 
				log.info("A new customer has been registered");
				break;
			}
			case "E" : {
				System.out.println("Thank you for using our services. You have successfully been logged off");
				displayMenu = false;
				break;
			}
			
			default: {
				System.out.println("The option you selected was not recognized. Please try again");
				break;
			}
			}
			
		
			
			
		}
			
		}

}