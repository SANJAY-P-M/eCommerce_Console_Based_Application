package roles;

import eCommerce_Console_Based_Application.ECommerceApplication;

public class Customer extends User {
	
	public Customer(User user) {
		super(user);
	}
	
	public Customer(String fullName , String email , String mobileNumber,String password) {
		super(fullName, email, mobileNumber, password);
	}
	
	public boolean placeOrder(Product product,int quantity) {
		return ECommerceApplication.makeOrder(this, product, quantity);
	}
	
	
	
}
