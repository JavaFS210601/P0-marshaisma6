package com.revature.daos;

import com.revature.models.Customer;

public interface CustomerDaoInterface {
	
	public void brandNewCustomer (Customer brandNewCustomer);
	public double getBalance(int account_no);
	public void withdraw(int account_no, int amount);
	public void deposit(int account_no, int amount);

	
	
	
	
	
}