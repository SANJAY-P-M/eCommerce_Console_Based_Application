package roles;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<Product> products;
	private int netAmount;
	
	public Cart() {
		setProducts(new ArrayList<>());
		setNetAmount(0);
	}
	
	public Cart(List<Product> products) {
		setProducts(products);
		int netAmount = 0;
		for(Product i:products)
			netAmount += i.getPrice() * i.getQuantity();
		setNetAmount(netAmount);
	}
	
	public int getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(int netAmount) {
		this.netAmount = netAmount;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
