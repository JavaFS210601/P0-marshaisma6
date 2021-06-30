package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Customer;
import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

import jdk.internal.org.jline.utils.Log;

public class CustomerDao implements CustomerDaoInterface {
	

	@Override
	public void brandNewCustomer(Customer brandNewCustomer) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlInsert = "INSERT INTO isma_bank.customers (username, customer_password, f_name, l_name, customer_age, email_address) values (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sqlInsert);
			ps.setString(1, brandNewCustomer.getUsername());
			ps.setString(2, brandNewCustomer.getPassword());
			ps.setString(3, brandNewCustomer.getF_name());
			ps.setString(4, brandNewCustomer.getL_name());
			ps.setInt(5, brandNewCustomer.getAge());
			ps.setString(6, brandNewCustomer.getEmail_address());

			ps.executeUpdate();
			System.out.println(
					"Customer " + brandNewCustomer.getF_name() + " has been registered to the system. Welcome!");
			
			
			String sqlgetCustId = "SELECT customer_id from isma_bank.customers WHERE username=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlgetCustId);
			preparedStatement.setString(1, brandNewCustomer.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int customer_id = resultSet.getInt("customer_id");
			
			String sqlInsert2 = "INSERT INTO isma_bank.account_info (customer_id, current_balance) values (?,?)";
			PreparedStatement ps2 = conn.prepareStatement(sqlInsert2);
			ps2.setInt(1, customer_id);
			ps2.setInt(2, 0);
			ps2.executeUpdate();

			String sqlgetAccNo = "SELECT account_no from isma_bank.account_info WHERE customer_id=?";
			PreparedStatement preparedStatement2 = conn.prepareStatement(sqlgetAccNo);
			preparedStatement2.setInt(1, customer_id);
			ResultSet resultSet2 = preparedStatement2.executeQuery();
			resultSet2.next();
			int account_no = resultSet2.getInt("account_no");
			System.out.println("Your registration was succesful! Your account number is: " + account_no);
			
			
			
		} catch (SQLException e) {
			System.out.println("Registering the new customer failed!");
			e.printStackTrace();
		}

	}

	@Override
	public double getBalance(int account_no) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlgetBal = "SELECT current_balance from isma_bank.account_info WHERE account_no=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlgetBal);
			preparedStatement.setInt(1, account_no);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getDouble("current_balance");
		} catch (SQLException e) {
			System.out.println("Unfortunately, we were unable to retrieve this information. Please try again");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	//This method interacts with DB 3times 
	//1. Gets current balance from account table, with logic to subtract current balance from withdrawal amount 
	//2. Updates the account_info table with the new balance 
	//3. Inserts into the transaction table the new amount 
	public void withdraw(int account_no, int amount) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlgetBal = "SELECT current_balance from isma_bank.account_info WHERE account_no=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlgetBal);
			preparedStatement.setInt(1, account_no);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			double balance = resultSet.getDouble("current_balance");
			
			if(balance < amount) {
				System.out.println("Sorry, you have insufficient Funds");
			}
			else {
				double newBalance = balance - amount;
				
				String sqlUpdateBalance = "UPDATE isma_bank.account_info SET current_balance =? WHERE account_no=?";
				PreparedStatement preparedStatement2 = conn.prepareStatement(sqlUpdateBalance);
				preparedStatement2.setDouble(1, newBalance);
				preparedStatement2.setInt(2, account_no);
				preparedStatement2.executeUpdate();
				
				String sqlUpdateTransaction = "INSERT INTO isma_bank.transactions (account_no, transaction_type, transaction_amount) VALUES  (?,?,?)";
				PreparedStatement preparedStatement3 = conn.prepareStatement(sqlUpdateTransaction);
				preparedStatement3.setInt(1, account_no);
				preparedStatement3.setString(2, "withdraw");
				preparedStatement3.setInt(3, amount);
				preparedStatement3.executeUpdate();
			}
	
		} catch (SQLException e) {
			System.out.println("Unfortunately, we were unable to retrieve this information. Please try again");
			e.printStackTrace();
		}
		// need method functionality that lets us withdraw

	}

	@Override
	public void deposit(int account_no, int amount) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlgetBal = "SELECT current_balance from isma_bank.account_info WHERE account_no=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlgetBal);
			preparedStatement.setInt(1, account_no);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			double balance = resultSet.getDouble("current_balance");
				double newBalance = balance + amount;
				
				String sqlUpdateBalance = "UPDATE isma_bank.account_info SET current_balance = ? WHERE account_no=?";
				PreparedStatement preparedStatement2 = conn.prepareStatement(sqlUpdateBalance);
				preparedStatement2.setInt(1,(int) newBalance);
				preparedStatement2.setInt(2, account_no);
				preparedStatement2.executeUpdate(); 
				
				String sqlUpdateTransaction = "INSERT INTO isma_bank.transactions (account_no, transaction_type, transaction_amount) VALUES  (?,?,?)";
				PreparedStatement preparedStatement3 = conn.prepareStatement(sqlUpdateTransaction);
				preparedStatement3.setInt(1, account_no);
				preparedStatement3.setString(2, "deposit");
				preparedStatement3.setInt(3, amount);
				preparedStatement3.executeUpdate();
			}
	
		catch (SQLException e) {
			System.out.println("Unfortunately, we were unable to retrieve this information. Please try again");
			e.printStackTrace(); 
		}

	}

}
