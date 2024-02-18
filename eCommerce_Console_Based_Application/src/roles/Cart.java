package roles;

import java.util.Map;

public class Cart {
	private String userName;
	private Map<Product,Integer> productAndQuantity;
	private int netAmount;
	
	public Cart(String userName,Map<Product,Integer> productAndQuantity) {
		setUserName(userName);
		setProductsAndQuantity(productAndQuantity);
	}
	
	
	public int getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(int netAmount) {
		this.netAmount = netAmount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<Product,Integer> getProductsAndQuantity() {
		return productAndQuantity;
	}

	public void setProductsAndQuantity(Map<Product,Integer> productsAndQuantity) {
		this.productAndQuantity = productsAndQuantity;
	}
	
}
