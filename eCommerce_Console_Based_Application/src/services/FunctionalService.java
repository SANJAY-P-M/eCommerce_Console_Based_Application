package services;

import java.util.Scanner;

import doa.DatabaseController;
import roles.User;

public class FunctionalService {
	public static User createUser(Scanner scan) {
	    String username, mobileNo, password, confirmPassword;
	    User user = null;
	    
//	    When you authenticate in frontEnd of your application the you can reduce workLoad on server
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

            user = DatabaseController.createUser(username, mobileNo, password);
            UICards.printWelcomeMessage(username);
	    }

	    System.out.println("Username successfully created!");
	    return user;
	}

	
	private static boolean isValidMobileNumber(String mobileNo) {
		mobileNo.replaceAll("[^0-9]", "");
		return mobileNo.length() == 10;
	}


	public static User authenticate(Scanner scan) {
	    System.out.print("Enter username: ");
	    String username = scan.nextLine();

	    int maxAttempts = 3; // Set a maximum number of password attempts
	    int attempts = 0;
	    while (attempts < maxAttempts) {
	        System.out.print("Enter password: ");
	        String password = scan.nextLine();

	        try {
	            User user = DatabaseController.createUser(username, password);
	            UICards.printWelcomeMessage(username);
	            return user;
	        } catch (Exception e) {
	            attempts++;
	            if (attempts < maxAttempts) {
	                // Handle invalid password exception
	                System.out.println("Invalid password. Please try again.");
	            } else {
	                // Maximum attempts reached
	                System.out.println("Too many invalid password attempts.");
	                return null; // Indicate authentication failure
	            }
	        }
	    }

	    return null; // If all attempts fail, return null
	}


}
