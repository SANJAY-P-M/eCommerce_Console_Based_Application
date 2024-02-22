package roles;

import java.util.List;

import doa.ProductTable;
import doa.UserTable;
import eCommerce_Console_Based_Application.ECommerceApplication;

public class User{
	private int userId;
	private String fullName;
	private String email;
	private String password;
	private String mobileNumber;
	
	public User(String fullName,String email,String mobileNumber, String password) {
		setFullName(fullName);
		setEmail(email);
		setMobileNumber(mobileNumber);
		setPassword(password);
	}
	
	public User(int userId,String fullName,String email,String mobileNumber,String password,List<Order> orders,Cart cart) {
		setUserId(userId);
		setEmail(email);
		setFullName(fullName);
		setMobileNumber(mobileNumber);
		setPassword(password);
	}
	
//	Copy constructor
	public User(User user) {
		setUserId(user.userId);
		setFullName(user.fullName);
		setEmail(user.email);
		setMobileNumber(user.mobileNumber);
		setPassword(user.password);
	}
	
	public void setUserId(int userName) {
		this.userId = userName;
	}
	
	public int getUserId() {
		return this.userId;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
		UserTable.updateMail(this.getUserId(),email);
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
	    return "User Details:\n" +
	           "\tUser ID: " + userId + "\n" +
	           "\tFull Name: " + fullName + "\n" +
	           "\tEmail: " + email + "\n" +
	           "\tPassword: " + password + "\n" +
	           "\tMobile Number: " + mobileNumber;
	}



}
