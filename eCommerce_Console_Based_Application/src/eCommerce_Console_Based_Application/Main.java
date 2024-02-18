package eCommerce_Console_Based_Application;

import services.UICards;
public class Main {
	public static void main(String[] args) {
		Assets.createConnection();
		UICards.printGreeting();
		UICards.printChoiceList(Assets.signUpPromptList);
	
	}
}
