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
}
