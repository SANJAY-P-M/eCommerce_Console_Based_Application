package eCommerce_Console_Based_Application;
import java.util.Scanner;

import doa.DatabaseController;
import roles.User;
import services.Authentication;
import services.LoginServices;
import services.UICards;
public class Main {
	public static void main(String[] args) {
		DatabaseController.createConnection();
		Scanner scan = new Scanner(System.in);
		UICards.printGreeting();
		UICards.askRole();
		
		char input = scan.nextLine().charAt(0);
		
		User user = null;
		
		// After the switch case runs User changes into either customer or Admin
        // This is known as runtime polymorphism or dynamic polymorphism
		
		switch (input) {
		case 'N':
		case 'n':
			Authentication.createNewUser(
				LoginServices.getUniqueUser(scan),
				LoginServices.getUniqueMobileNumber(scan),
				LoginServices.getValidConfirmPassword(scan, LoginServices.getPassword(scan))
			);
			break;
		case 'E':
		case 'e':
			while(user == null) {
				user = Authentication.authenticate(
						LoginServices.getExistingUserName(scan),
						LoginServices.getPassword(scan)
					);				
			}
			break;
		default:
			break;
		}
	}	
}
