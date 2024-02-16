package eCommerce_Console_Based_Application;
import java.util.Scanner;

import doa.UserTable;
import roles.User;
import services.Authentication;
import services.LoginServices;
import services.UICards;
public class Main {
	public static void main(String[] args) {
		UserTable.createConnection();
		Scanner scan = new Scanner(System.in);
		UICards.printGreeting();
		String[] signUpPromptList = {"Sign up","Sign in","Close Application"};
		UICards.printChoiceList(signUpPromptList);
		char input = scan.nextLine().charAt(0);
		
		User user = null;
		
		// After the switch case runs User changes into either customer or Admin
        // This is known as runtime polymorphism or dynamic polymorphism
		
		switch (input) {
		case '1':
			UICards.printSucessMessage("Creating new user....!");
			user = Authentication.createNewUser(
				LoginServices.getUniqueUser(scan),
				LoginServices.getUniqueMobileNumber(scan),
				LoginServices.getValidConfirmPassword(scan, LoginServices.getPassword(scan))
			);
			break;
		case '2':
			UICards.printSucessMessage("Enter credencials");
			user = Authentication.authenticate(
					LoginServices.getExistingUserName(scan),
					LoginServices.getPassword(scan)
				);	
			break;
		case '3':
			return;
		}
		user.seeProfile();
	}	
}
