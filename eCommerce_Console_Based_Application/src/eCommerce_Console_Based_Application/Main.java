package eCommerce_Console_Based_Application;

import roles.User;
import services.Authentication;
import services.LoginServices;
import services.UICards;
public class Main {
	public static void main(String[] args) {
		Assets.createConnection();
		UICards.printGreeting();
		UICards.printChoiceList(Assets.signUpPromptList);
		char input = Assets.scan.nextLine().charAt(0);
		
		User user = null;
		
		// After the switch case runs User changes into either customer or Admin
        // This is known as runtime polymorphism or dynamic polymorphism
		
		switch (input) {
		case '1':
			UICards.printSucessMessage("Creating new user....!");
			user = Authentication.createNewUser(
				LoginServices.getUniqueUser(),
				LoginServices.getUniqueMobileNumber(),
				LoginServices.getValidConfirmPassword(LoginServices.getPassword())
			);
			break;
		case '2':
			UICards.printSucessMessage("Enter credencials");
			user = Authentication.authenticate(
					LoginServices.getExistingUserName(),
					LoginServices.getPassword()
				);	
			break;
		case '3':
			UICards.printSucessMessage("Application closed !");
			return;
		}
	}	
}
