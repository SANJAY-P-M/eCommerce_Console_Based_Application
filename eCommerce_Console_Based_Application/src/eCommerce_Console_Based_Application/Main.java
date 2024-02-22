package eCommerce_Console_Based_Application;

import roles.Admin;
import roles.Customer;
import roles.Employee;
import roles.User;
import services.LoginServices;
import services.UICards;
public class Main {
	public static void main(String[] args) {
		User user = null;
		
			while(user == null) {
				UICards.printGreeting();
				UICards.printChoiceList(Assets.signUpPromptList);
				int choice = Assets.scan.nextInt();
				Assets.scan.nextLine();
				switch(choice) {
				case 1:
					user = LoginServices.createNewCustomer();
					break;
				case 2:
					user = LoginServices.authenticateCustomer();
					break;			
			}
		}
		if(user instanceof Customer) {
			customerView((Customer) user);
		} else if( user instanceof Admin) {
			adminView((Admin)user);
		} else if(user instanceof Employee) {
			employeeViwe((Employee)user);
		}
		System.out.println(user);
	}

	private static void customerView(Customer customer) {
		int choice;
		
		do{
			UICards.printChoiceList(Assets.customerHome);
			choice = Assets.scan.nextInt();
			switch (choice) {
				case 1:
					System.out.println(customer);
					
					break;
					
				default:
					break;
			}
		}while(choice != 5);
	}

	private static void employeeViwe(Employee user) {
		
	}

	private static void adminView(Admin user) {
		
	}

}
