package com.revature.models;

public class Customer {
	
	
	private int customer_id;
	private String username;
	private String password;
	private String f_name;
	private String l_name;
	private int  age;
	private String email_address;
	
	
	
	
	//our no-args constructor
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}


	//so we can add new customers who do not have an Id
	public Customer(String username, String password, String f_name, String l_name, int age, String email_address) {
		super();
		this.username = username;
		this.password = password;
		this.f_name = f_name;
		this.l_name = l_name;
		this.age = age;
		this.email_address = email_address;
	}



	public Customer(int customer_id, String username, String password, String f_name, String l_name, int age,
			String email_address) {
		super();
		this.customer_id = customer_id;
		this.username = username;
		this.password = password;
		this.f_name = f_name;
		this.l_name = l_name;
		this.age = age;
		this.email_address = email_address;
	}


	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


	public String getEmail_address() {
		return email_address;
	}


	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}


	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", username=" + username + ", password=" + password
				+ ", f_name=" + f_name + ", l_name=" + l_name + ", age=" + age + ", email_address=" + email_address
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + customer_id;
		result = prime * result + ((email_address == null) ? 0 : email_address.hashCode());
		result = prime * result + ((f_name == null) ? 0 : f_name.hashCode());
		result = prime * result + ((l_name == null) ? 0 : l_name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (age != other.age)
			return false;
		if (customer_id != other.customer_id)
			return false;
		if (email_address == null) {
			if (other.email_address != null)
				return false;
		} else if (!email_address.equals(other.email_address))
			return false;
		if (f_name == null) {
			if (other.f_name != null)
				return false;
		} else if (!f_name.equals(other.f_name))
			return false;
		if (l_name == null) {
			if (other.l_name != null)
				return false;
		} else if (!l_name.equals(other.l_name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
	

}
