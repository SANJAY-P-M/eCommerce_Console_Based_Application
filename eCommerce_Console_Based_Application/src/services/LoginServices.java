package services;

import java.sql.ResultSet;
import java.util.Scanner;

import doa.UserTable;

public class LoginServices {
	
//	get valid user name which does does not exists in db
	public static String getUniqueUser(Scanner scan) {
		ResultSet userInformation = null;
		String username;
        do{
        	UICards.prompt("username: ");
        	username = scan.nextLine();
        	userInformation = UserTable.getUserInformationWithUsername(username);
        	if(userInformation != null)
        		UICards.printWarning("Username already exists. Please choose another one."); 
        } while (userInformation != null); 	
		return username;
	}
//  gets a valid mobile number 
	public static String getUniqueMobileNumber(Scanner scan) {
		ResultSet userInformation = null;
		String mobileNo;
		do {
        	UICards.prompt("mobile number (e.g., 1234567890)");
        	mobileNo = scan.nextLine();
        	userInformation = UserTable.getUserInformationWithMobileNumber(mobileNo);
        	if (!isValidMobileNumber(mobileNo))
        		UICards.printWarning("Invalid mobile number format. Please try again.");
        	else if(userInformation != null) 
        		UICards.printWarning("Mobile number already exists :");
		} while (!isValidMobileNumber(mobileNo) || userInformation != null);
		return mobileNo;
	}
	
	public static String getExistingUserName(Scanner scan) {
		String userName;
		ResultSet userInformation = null;
		do {
			UICards.prompt("username");
			userName =  scan.nextLine();
			userInformation = UserTable.getUserInformationWithUsername(userName);
			if(userInformation == null)
				UICards.printWarning("Username does not exists ");
		}while(userInformation == null);
		return userName;
	}

	//	gets confirm password which matches previous password
	public static String getValidConfirmPassword(Scanner scan,String prevPassword) {
		String confirmPassword;
		do{
			UICards.prompt("confirm  password");
			confirmPassword = scan.nextLine();
            if(!prevPassword.equals(confirmPassword)) UICards.printWarning("Passwords don't match. Please try again.");
        } while(!prevPassword.equals(confirmPassword));
        return confirmPassword;
	}
	
	
//	mobile number validation
	private static boolean isValidMobileNumber(String mobileNo) {
		mobileNo.replaceAll("[^0-9]", "");
		return mobileNo.length() == 10;
	}
	public static String getPassword(Scanner scan) {
		UICards.prompt("password");
		return scan.nextLine();
	}
}
