package roles;

import java.util.Map;

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
	
	public void viewAllProducts() {
		ECommerceApplication.viewAllProducts();
	}
	
	public void selectProduct(int productId) {
		ECommerceApplication.selectProduct(productId);
	}
	
	public boolean addToCart(Product product,int quantity) {
		return ECommerceApplication.addToCart(this,product,quantity);
	}
	
	
}
