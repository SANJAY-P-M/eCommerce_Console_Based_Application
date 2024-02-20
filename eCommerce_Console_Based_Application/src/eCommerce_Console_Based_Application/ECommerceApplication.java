package eCommerce_Console_Based_Application;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import doa.CartTable;
import doa.OrderTable;
import doa.ProductTable;
import doa.UserTable;
import doaException.NoSuchProductException;
import doaException.StockNotAvailable;
import roles.Cart;
import roles.Customer;
import roles.Order;
import roles.Product;
import roles.User;
import services.UICards;

public class ECommerceApplication {
	
	public static User addUser(User user,String role) {
		return UserTable.insertNewUser(user, role);
	}
	
	public static void viewAllProducts() {
		List<Product> list = ProductTable.getAllProducts();
		for(Product i:list)
			System.out.println(i);
	}
	
	public static boolean makeOrder(User user,Product product,int quantity){
		
//		Check Availability
		if(!ProductTable.isProductAvailable(product, quantity)) return false;
		
//		Update in table 
		Order order = OrderTable.insertOrder(user, product, quantity);
		ProductTable.reduceQuantity(product, quantity);
		
//		Update in local instance
		user.getOrders().add(order);
		return true;
	}
	
	public static boolean checkOutFromCart(User user) {
		
		Cart cart = user.getCart();
		
//		Check Availability
		Map<Product,Integer> cartProduct = cart.getProductsAndQuantity();
		for(Map.Entry<Product, Integer> i:cartProduct.entrySet())
			if(!ProductTable.isProductAvailable(i.getKey(), i.getValue()))
				return false;
		
//		Insert into Order table
		Order order = OrderTable.insertOrder(cart);
		for(Map.Entry<Product, Integer> i:cartProduct.entrySet())
			ProductTable.reduceQuantity(i.getKey(), i.getValue());
		CartTable.makeEmpty(cart.getUserId());
		
//		Finally add new Order to user class
		user.getOrders().add(order);
		user.setCart(null);
		
		return true;
	}

	public static void selectProduct(int productId) {
		try {
			Product product = ProductTable.getProduct(productId);
			System.out.println(product);
		} catch (NoSuchProductException e) {
			UICards.printWarning(e.getMessage());
		}
		System.out.println();
	}

	public static boolean addToCart(User user, Product product,int quantity) {
		
//		Check Availability
		if(!ProductTable.isProductAvailable(product, quantity))
			return false;
		
//		Insert in table
		CartTable.insert(user, product, quantity);
		
//		update in object
		Map<Product,Integer> map = user.getCart().getProductsAndQuantity();
		map.put(product, map.getOrDefault(product, 0)+quantity);
		
		return true;
	}
	
}
