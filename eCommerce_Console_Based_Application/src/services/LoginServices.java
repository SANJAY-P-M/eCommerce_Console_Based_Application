package services;

import doa.UserTable;
import eCommerce_Console_Based_Application.Assets;
import eCommerce_Console_Based_Application.Assets.EmailAlreadyExistsException;
import eCommerce_Console_Based_Application.Assets.MobileNumberAlreadyExistsException;
import eCommerce_Console_Based_Application.Assets.UserNameAlreadyExistsException;
import roles.Customer;

public class LoginServices {

	
//	Creating new user
	public static Customer createNewUser() {
		UICards.prompt("E-mail");
		String email = Assets.scan.nextLine();
		
		UICards.prompt("Mobile Number");
		String mobileNumber = Assets.scan.nextLine();
		
		UICards.prompt("Full Name");
		String fullName = Assets.scan.nextLine();
		
		UICards.prompt("User Name");
		String userName = Assets.scan.nextLine();
		
		UICards.prompt("Password");
		String password = Assets.scan.nextLine();
		
		UICards.prompt("Confirm Password");
		String confirmPassword = Assets.scan.nextLine();
		
		Customer customer = new Customer(userName, fullName, confirmPassword, email, mobileNumber);
		try {
			UserTable.insertNewUser(customer);
		} catch (UserNameAlreadyExistsException e) {
			UICards.printWarning(e.getMessage());
		} catch (EmailAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MobileNumberAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}
	
}
