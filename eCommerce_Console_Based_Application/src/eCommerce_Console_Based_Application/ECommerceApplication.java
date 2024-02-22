package eCommerce_Console_Based_Application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import doa.CartTable;
import doa.OrderTable;
import doa.ProductTable;
import doaException.NoSuchProductException;
import roles.Cart;
import roles.Order;
import roles.Product;
import roles.User;
import services.UICards;

public class ECommerceApplication {
	
	private List<Product> products;
	
	public List<Product> getProducts() {
		return this.products;
	}
	
	public ECommerceApplication(){
		products = ProductTable.getAllProducts();
	}
	
	public Product getProduct(int productId) {
		for(Product i:products)
			if(i.getId() == productId)
				return i;
		return null;
	}

	public List<Product> getAvailableProducts() {
		return products.stream()
				.filter(element -> element.getAvailableQuantity() > 0)
				.collect(Collectors.toList());
	}
}
