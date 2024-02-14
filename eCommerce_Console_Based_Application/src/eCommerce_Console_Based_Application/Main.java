package eCommerce_Console_Based_Application;
import java.util.Scanner;

import roles.User;
import services.FunctionalService;
import services.UICards;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		UICards.printGreeting();
		UICards.askRole();
		
		char input = scan.nextLine().charAt(0);
		
		User user;
		
		// After the switch case runs User changes into either customer or Admin
        // This is known as runtime polymorphism or dynamic polymorphism
		
		switch (input) {
		case 'N':
		case 'n':
			user = FunctionalService.createUser(scan);
			break;
		case 'E':
		case 'e':
			user = FunctionalService.authenticate();
			break;
		default:
			break;
		}
	}	
}
