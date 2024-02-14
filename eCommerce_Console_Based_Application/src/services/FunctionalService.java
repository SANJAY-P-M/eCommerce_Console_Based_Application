package services;

import java.util.Scanner;

import doa.DatabaseController;
import roles.User;

public class FunctionalService {
	public static User createUser(Scanner scan) {
	    String username, mobileNo, password, confirmPassword;
	    User user = null;

	    // Input validation loop
	    while (user == null) {
	        System.out.print("Enter your desired username: ");
	        username = scan.nextLine();

	        if (DatabaseController.isUsernameTaken(username)) {
	            System.out.println("Username already exists. Please choose another one.");
	            continue;
	        }

	        System.out.print("Enter your mobile number (e.g., +1234567890): ");
	        mobileNo = scan.nextLine();

	        if (!isValidMobileNumber(mobileNo)) {
	            System.out.println("Invalid mobile number format. Please try again.");
	            continue;
	        }

	        System.out.print("Enter your password : ");
	        password = scan.nextLine();

	        System.out.print("Confirm your password: ");
	        confirmPassword = scan.nextLine();

	        if (!password.equals(confirmPassword)) {
	            System.out.println("Passwords don't match. Please try again.");
	            continue;
	        }

	        // Attempt to create user only after all validations pass
	        try {
	            user = DatabaseController.createUser(username, mobileNo, password);
	        } catch (Exception e) {
	            System.out.println("Error creating user: " + e.getMessage());
	            continue; // Restart the loop to allow user to retry
	        }
	    }

	    System.out.println("Username successfully created!");
	    return user;
	}

	
	private static boolean isValidMobileNumber(String mobileNo) {
		mobileNo.replaceAll("[^0-9]", "");
		return mobileNo.length() == 10;
	}

}
