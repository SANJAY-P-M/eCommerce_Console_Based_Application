package services;

import java.sql.ResultSet;
import java.util.Scanner;

import doa.DatabaseController;

public class LoginServices {
	
//	get valid user name which does does not exists in db
	public static String getUniqueUser(Scanner scan) {
		ResultSet userInformation = null;
		String username;
        do{
        	System.out.print("->  Enter your desired username: ");
        	username = scan.nextLine();
        	userInformation = DatabaseController.getUserInformationWithUsername(username);
        	if(userInformation != null)
        		System.out.println("Username already exists. Please choose another one."); 
        } while (userInformation != null); 	
		return username;
	}
//  gets a valid mobile number 
	public static String getUniqueMobileNumber(Scanner scan) {
		ResultSet userInformation = null;
		String mobileNo;
		do {
        	System.out.print("->  Enter your mobile number (e.g., 1234567890): ");
        	mobileNo = scan.nextLine();
        	userInformation = DatabaseController.getUserInformationWithMobileNumber(mobileNo);
        	if (!isValidMobileNumber(mobileNo))
        		UICards.printWarning("Invalid mobile number format. Please try again.");
        	else if(userInformation != null) 
        		UICards.printWarning("Mobile number already exists :");
		} while (!isValidMobileNumber(mobileNo) || userInformation != null);
		return mobileNo;
	}
	
//	gets confirm password which matches previous password
	public static String getValidConfirmPassword(Scanner scan,String prevPassword) {
		String confirmPassword;
		do{
			System.out.println(" ->  Enter confirm password : ");
			confirmPassword = scan.nextLine();
            if(!prevPassword.equals(confirmPassword)) UICards.printWarning("Passwords don't match. Please try again.");
        } while(!prevPassword.equals(confirmPassword));
        return confirmPassword;
	}
	
	public static String getExistingUserName(Scanner scan) {
		String userName;
		ResultSet userInformation = null;
		do {
			System.out.print(" ->   Enter user name : ");
			userName =  scan.nextLine();
			userInformation = DatabaseController.getUserInformationWithUsername(userName);
			if(userInformation == null)
				UICards.printWarning("Username does not exists ");
		}while(userInformation == null);
		return userName;
	}
	
//	mobile number validation
	private static boolean isValidMobileNumber(String mobileNo) {
		mobileNo.replaceAll("[^0-9]", "");
		return mobileNo.length() == 10;
	}
	public static String getPassword(Scanner scan) {
		System.out.print(" -> Enter password : ");
		return scan.nextLine();
	}
}
