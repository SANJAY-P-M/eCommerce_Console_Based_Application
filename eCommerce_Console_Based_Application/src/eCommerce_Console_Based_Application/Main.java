package eCommerce_Console_Based_Application;

import roles.Customer;
import services.LoginServices;
import services.UICards;
public class Main {
	public static void main(String[] args) {
		Assets.createConnection();
		UICards.printGreeting();
		UICards.printChoiceList(Assets.signUpPromptList);
		int choice = Assets.scan.nextInt();
		Assets.scan.nextLine();
		Customer customer;
		
		switch(choice) {
			case 1:
				customer = LoginServices.createNewUser();
		}
	}
}
