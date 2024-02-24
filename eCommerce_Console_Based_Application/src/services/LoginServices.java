package services;

import doa.UserTable;
import eCommerce_Console_Based_Application.Assets;
import roles.Customer;
import roles.User;

public class LoginServices {

	
//	Creating new user
	public static Customer createNewCustomer() {
		
		String email = getNewEmail();
		
		String mobileNumber = getNewMobileNumber();
		
		
//		get Full Name
		UICards.prompt("Full Name");
		String fullName = Assets.scan.nextLine().toUpperCase();
		
//		Password
		String password = getNewPassword();
		if(password == null) return null;
		
		Customer customer = null;
		customer = new Customer(fullName, email, mobileNumber, password);
		
//		Inserting customer into user table
		customer  = (Customer) UserTable.insert(customer,"customer");
		
		UICards.printWelcomeMessage(customer.getFullName());
		return customer;
		
	}
	


	public static String getNewMobileNumber() {

//		Get Valid Mobile Number
		UICards.prompt("Mobile Number");
		String mobileNumber = Assets.scan.nextLine();
		
//		Validating mobile number
		boolean mobileNumberExists = UserTable.isMobileNumberExists(mobileNumber);
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
		return mobileNumber;
}



	public static String getNewEmail() {

//		Get valid E-mail
		UICards.prompt("E-mail");
		String email = Assets.scan.nextLine();
		
//		Validating E-mail
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
		return email;
}



	public static User authenticateCustomer() {
		User user = null;
		
//		Get valid E-mail
		String email = getExistingEmail();
		
//		Get password
		String password;
		int attempts = 3;
		do {
				UICards.prompt("Password");
	            password = Assets.scan.nextLine();
                if (password.equals(UserTable.getPassword(email))) {
                    user = UserTable.getUserWithEMail(email);
                } else {
                	UICards.printWarning("Incorrect password "+attempts+" left");                	
                }
	            attempts--;
        } while (attempts > 0 && user == null);
		
		return user;
	}

	
	
	public static String getExistingEmail() {
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
		return email;
	}



	private static boolean isValidMobileNumber(String mobileNumber) {
		return mobileNumber.matches("^\\d{10}$");
	}
	
	private static boolean isValidEMail(String email) {
		return email.matches("^[\\w.+\\-]+@[a-zA-Z\\d\\-]+(\\.[a-zA-Z]+)*$");
	}
	
	public static String getNewPassword() {
//		password Logic
		String password;
		String confirmPassword;
		
		int attemmpts = 3;
		do {
			UICards.prompt("Password");
			password = Assets.scan.nextLine();
			
			UICards.prompt("Confirm Password");
			confirmPassword = Assets.scan.nextLine();
			
			if(!password.equals(confirmPassword)) {
				UICards.printWarning("password and confirm password mismatch\n\t\t"+attemmpts--+" left");
			}
			
		}while(!password.equals(confirmPassword) && attemmpts > 0);
		
		if(attemmpts == 0) {
			UICards.printWarning("No more attempts");
			return null;
		}
		return password;
	}
}

