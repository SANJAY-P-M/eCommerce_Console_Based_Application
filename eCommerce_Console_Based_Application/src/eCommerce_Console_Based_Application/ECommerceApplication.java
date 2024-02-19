package eCommerce_Console_Based_Application;
import java.util.List;

import doa.ProductTable;
import roles.Product;

public class ECommerceApplication {
	public boolean addProduct(Product product) {
		return ProductTable.insertProduct(product);
	}
	
	public List<Product> viewAllProducts() {
		 return ProductTable.getAllProducts();
	}
	
}
