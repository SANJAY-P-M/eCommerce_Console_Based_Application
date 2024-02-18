package roles;

import java.util.Map;

public class Cart {
	private int userId;
	private Map<Product,Integer> productAndQuantity;
	private int netAmount;
	
	public Cart(int userId,Map<Product,Integer> productAndQuantity) {
		setProductsAndQuantity(productAndQuantity);
		setUserId(userId);
	}
	
	
	public int getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(int netAmount) {
		this.netAmount = netAmount;
	}

	public Map<Product,Integer> getProductsAndQuantity() {
		return productAndQuantity;
	}

	public void setProductsAndQuantity(Map<Product,Integer> productsAndQuantity) {
		this.productAndQuantity = productsAndQuantity;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
