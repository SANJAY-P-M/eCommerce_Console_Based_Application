package eCommerce_Console_Based_Application;
import java.util.List;
import java.util.Map;

import doa.OrderTable;
import doa.ProductTable;
import doa.UserTable;
import doaException.StockNotAvailable;
import roles.Cart;
import roles.Order;
import roles.Product;
import roles.User;
import services.UICards;

public class ECommerceApplication {
	
	public static User addUser(User user,String role) {
		return UserTable.insertNewUser(user, role);
	}
	
	public void viewAllProducts() {
		List<Product> list = ProductTable.getAllProducts();
		for(Product i:list)
			System.out.println(i);
	}
	
	public static boolean makeOrder(User user,Product product,int quantity){
		
//		Check Availability
		if(!ProductTable.isProductAvailable(product, quantity)) return false;
		
		OrderTable.insertOrder(user, product, quantity);
		return true;
	}
	
	public static boolean checkOutFromCart(User user,Cart cart) {
		
//		Check Availability
		Map<Product,Integer> cartProduct = cart.getProductsAndQuantity();
		for(Map.Entry<Product, Integer> i:cartProduct.entrySet())
			if(!ProductTable.isProductAvailable(i.getKey(), i.getValue()))
				return false;
		Order order = OrderTable.insertOrder(cart);
		
//		Finally add new Order to user class
		user.getOrders().add(order);
		return true;
	}
	
}
