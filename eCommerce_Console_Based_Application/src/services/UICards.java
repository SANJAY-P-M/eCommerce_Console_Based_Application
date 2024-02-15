package services;

public class UICards {
	
	public static void printGreeting() {
		System.out.println("        \033[0;37;44m" + " Welcome to E-Commerce Application! " + "\033[0m");
		System.out.println();
	}
	
//	Role Options from user
	public static void askRole() {
		System.out.println("Please choose how you'd like to proceed:");
		System.out.println("[N] New User");
		System.out.println("[E] Existing User");
	}

	public static void printWelcomeMessage(String userName) {
		System.out.println("###################                           ##################");
		System.out.println("################### WELCOME "+userName+"      ##################");
		System.out.println("###################                           ##################");

	}
	
	public static void printWarning(String message) {
            System.out.println("\u001B[33m" + message + "\u001B[0m");
	}
}
