package eCommerce_Console_Based_Application;

import doaException.ProductNotSelectedException;
import doaException.StockNotAvailable;
import roles.Admin;
import roles.Customer;
import roles.Employee;
import roles.Product;
import roles.User;
import services.LoginServices;
import services.UICards;
public class Main {
	private static ECommerceApplication app = new ECommerceApplication();
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
		
//		"View Profile",
//		"View products",
//		"View Cart",
//		"View orders",
//		"Log out"
		
		do{
			UICards.printChoiceList(Assets.customerHome);
			choice = Assets.scan.nextInt();
			switch (choice) {
				case 1:
					System.out.println(customer);
					modifyProfile(customer);
					break;
				case 2:
					System.out.println(app.getAvailableProducts());
					selectProduct(customer);
				default:
					break;
			}
		}while(choice != 5);
	}

	private static void selectProduct(Customer customer) {
		int choice;
		
//		{"Select product with product Id "
//			,"Back"}
		
		do {
			UICards.printChoiceList(Assets.selectProduct);
			choice = Assets.scan.nextInt();
			switch (choice) {
			case 1:
				UICards.prompt("product Id");
				int productId = Assets.scan.nextInt();
				Product product = app.getProduct(productId);
				if(product == null) {
					UICards.printWarning("Product does not exists");
					break;
				}
				UICards.printSucessMessage("You selected\n"+product.toString());
				customer.setSelected(product);
				
//				"Add to cart",
//				"Place order",
//				"Back"
				int choice1;
				do {
					UICards.printChoiceList(Assets.doWithProduct);
					choice1 = Assets.scan.nextInt();
					switch (choice1) {
						case 1:
							int quantity = Assets.scan.nextInt();
							try {
								customer.addToCart(quantity);
								UICards.printSucessMessage("Product added to cart sucessfully");
							} catch (StockNotAvailable e) {
								System.out.println(e.getMessage());
							} catch (ProductNotSelectedException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2:
							quantity = Assets.scan.nextInt();
							try {
								customer.makeOrder(quantity);
								UICards.printSucessMessage("Your is placed check in view profile");
							} catch (StockNotAvailable e) {
								System.out.println(e.getMessage());
							} catch (ProductNotSelectedException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 3:
							break;
						default:
							UICards.printWarning("Invalid option");
							break;
					}
				}while(choice != 3);
			default:
				UICards.printWarning("Invalid option");
				break;
			}
		}while(choice != 2);
	}
	
	private static void modifyProfile(User user) {
		int choice;
			
	//		"Change e-mail",
	//		"Change full Name",
	//		"Change Mobile Number",
	//		"Change password",
		
		do{
			UICards.printChoiceList(Assets.modifyProfile);
			choice = Assets.scan.nextInt();
			Assets.scan.nextLine();
			switch (choice) {
			
				case 1:
					String newMail = LoginServices.getNewEmail();
					user.setEmail(newMail);
					UICards.printSucessMessage("Your new E-Mail is "+newMail);
					break;
				case 2:
					UICards.prompt("New Name");
					String newName = Assets.scan.nextLine();
					user.setFullName(newName);
					UICards.printSucessMessage("Your new name is "+newName);
					break;
				case 3:
					String newMobileNumber = LoginServices.getNewMobileNumber();
					user.setMobileNumber(newMobileNumber);
					UICards.printSucessMessage("Your new mobile number is "+newMobileNumber);
					break;
				case 4:
					String newPassword = LoginServices.getNewPassword();
					if(newPassword != null) {
						user.setPassword(newPassword);
						UICards.printSucessMessage("Your new password is "+newPassword);
					}
					break;
				case 5:
					return;
				default:
					UICards.printWarning("Invalid choice");
			}
		}while(choice != 5);
	}

	private static void employeeViwe(Employee user) {
		
	}

	private static void adminView(Admin user) {
		
	}

}
