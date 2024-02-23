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
	
	public User(String fullName, String email, String mobileNumber, String password) {
	    this.fullName = fullName;
	    this.email = email;
	    this.mobileNumber = mobileNumber;
	    this.password = password;
	}

	
	public User(int userId, String fullName, String email, String mobileNumber, String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }
	
//	Copy constructor
	public User(User user) {
	    this.userId = user.userId;
	    this.fullName = user.fullName;
	    this.email = user.email;
	    this.mobileNumber = user.mobileNumber;
	    this.password = user.password;
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
//		Update in database
		UserTable.updateMobileNumber(this.userId , mobileNumber);
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
//		Update in database
		UserTable.updateFullName(this.userId , fullName);
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
//		Update in database
		UserTable.updatePassword(this.userId,password);
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
