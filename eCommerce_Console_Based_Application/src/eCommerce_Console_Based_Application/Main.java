package eCommerce_Console_Based_Application;

import roles.Customer;
import roles.User;
import services.LoginServices;
import services.UICards;
public class Main {
	public static void main(String[] args) {
		UICards.printGreeting();
		UICards.printChoiceList(Assets.signUpPromptList);
		int choice = Assets.scan.nextInt();
		Assets.scan.nextLine();
		User user = null;
		
		switch(choice) {
			case 1:
				user = LoginServices.createNewUser();
				break;
			case 2:
				user = LoginServices.authenticateUser();
		}
		System.out.println(user);
	}
}
