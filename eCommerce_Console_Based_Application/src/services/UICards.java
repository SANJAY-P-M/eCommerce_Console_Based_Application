package services;

public class UICards {
	
	public static void printGreeting() {
		System.out.println("        \033[0;37;44m" + " Welcome to E-Commerce Application! " + "\033[0m");
		System.out.println();
	}

	public static void printWelcomeMessage(String userName) {
		System.out.println("#################################################                           ##################");
		System.out.println("#################################################      WELCOME "+userName.toUpperCase()+"      ##################");
		System.out.println("#################################################                           ##################");
	}
	
	public static void printWarning(String message) {
			System.out.println("#######################################################");
            System.out.println("           \u001B[33m" + message + "\u001B[0m");
			System.out.println("#######################################################");
	}
	
	public static void prompt(String toGet) {
		System.out.print("   ->   Enter "+toGet+"  : ");
	}
	
	public static void printSucessMessage(String message) {
	    System.out.println("#######################################################");
	    System.out.println("           \u001B[32m" + message + "\u001B[0m");
	    System.out.println("#######################################################");
	}
	
	public static void printChoiceList(String[] array) {
		System.out.println("=>> Please choose how you'd like to proceed");
		for(int i = 0;i < array.length;i++)
			System.out.println("        ["+(i+1)+"]  "+array[i]);
		prompt("your choice");
	}
}
