package roles;

import java.util.List;

public class Customer{
	private String fullName;
	private String password;
	private String email;
	private String mobileNumber;
	private List<Order> orders;
	
	public Customer(String fullName,String password,String email,String mobileNumber) {
		setFullName(fullName);
		setPassword(password);
		setEmail(email);
		setMobileNumber(mobileNumber);
	}
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	

}
