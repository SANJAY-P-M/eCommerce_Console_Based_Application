package services;

import doa.UserTable;
import doaException.EmailAlreadyExistsException;
import doaException.MobileNumberAlreadyExistsException;
import doaException.NoSuchEmailException;
import eCommerce_Console_Based_Application.Assets;
import eCommerce_Console_Based_Application.ECommerceApplication;
import roles.Customer;
import roles.User;

public class LoginServices {

	
//	Creating new user
	public static Customer createNewCustomer() {
		
//		Get valid E-mail
		UICards.prompt("E-mail");
		String email = Assets.scan.nextLine();
		boolean mailExists = UserTable.isMailExists(email);
		boolean validMail = isValidEMail(email);
		while(mailExists || !validMail) {
			if(!validMail)
				UICards.printWarning("E-mail must be in pattern (mail@mail.com)");
			else if(mailExists)
				UICards.printWarning("E-Mail already exists ");
			UICards.prompt("E-mail");
			email = Assets.scan.nextLine();
			mailExists = UserTable.isMailExists(email);
			validMail = isValidEMail(email);
		}
		
		
//		Get Valid Mobile Number
		UICards.prompt("Mobile Number");
		String mobileNumber = Assets.scan.nextLine();
		boolean mobileNumberExists = UserTable.isMailExists(mobileNumber);
		boolean validMobileNumber = isValidMobileNumber(mobileNumber);
		while(mobileNumberExists || !validMobileNumber) {
			if(!validMobileNumber)
				UICards.printWarning("Mobile Number must be 10 digits");
			else if(mobileNumberExists)
				UICards.printWarning("Mobile Number already exists ");
			UICards.prompt("Mobile Number");
			mobileNumber = Assets.scan.nextLine();
			validMobileNumber = isValidMobileNumber(mobileNumber);
			mobileNumberExists = UserTable.isMobileNumberExists(mobileNumber);
		}
		
//		get Full Name
		UICards.prompt("Full Name");
		String fullName = Assets.scan.nextLine().toUpperCase();
		
		
//		password Logic
		String password;
		String confirmPassword;
		do {
			UICards.prompt("Password");
			password = Assets.scan.nextLine();
			UICards.prompt("Confirm Password");
			confirmPassword = Assets.scan.nextLine();
			if(!password.equals(confirmPassword))
				UICards.printWarning("password and confirm password mismatch");
		}while(!password.equals(confirmPassword));
		
		Customer customer = null;
		 customer = new Customer(fullName, email, mobileNumber, password);
		ECommerceApplication.addUser(customer,"customer");
		UICards.printWelcomeMessage(customer.getFullName());
		return customer;
	}
	
	private static boolean isValidMobileNumber(String mobileNumber) {
		return mobileNumber.matches("^\\d{10}$");
	}
	
	private static boolean isValidEMail(String email) {
		return email.matches("^[\\w.+\\-]+@[a-zA-Z\\d\\-]+(\\.[a-zA-Z]+)*$");
	}

	public static User authenticateCustomer() {
		User user = null;
		
//		Get valid E-mail
		UICards.prompt("E-Mail");
		String email = Assets.scan.nextLine();
		boolean mailExists = UserTable.isMailExists(email);
		boolean validMail = isValidEMail(email);
		while(!mailExists || !validMail) {
			if(!validMail)
				UICards.printWarning("E-mail must be in pattern (mail@mail.com)");
			else if(!mailExists)
				UICards.printWarning("E-Mail does not exists ");
			UICards.prompt("E-mail");
			email = Assets.scan.nextLine();
			mailExists = UserTable.isMailExists(email);
			validMail = isValidEMail(email);
		}
		
//		Get password
		String password;
		int attempts = 3;
		do {
				UICards.prompt("Password");
	            password = Assets.scan.nextLine();
	            try {
	                if (password.equals(UserTable.getPassword(email))) {
	                    user = UserTable.getUserWithEMail(email);
	                }
	            } catch (NoSuchEmailException e) {
	                e.printStackTrace();
	            }
	            UICards.printWarning("Incorrect password "+attempts+" left");
	            attempts--;
        } while (attempts > 0 && user == null);
		
		return user;
	}
	
}
