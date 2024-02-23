package roles;

import java.util.Map;
import java.util.Map.Entry;

public class Cart {
	private int userId;
	private Map<Product,Integer> productAndQuantity;
	private double netAmount;
	
	public Cart(int userId,Map<Product,Integer> productAndQuantity) {
		setProductsAndQuantity(productAndQuantity);
		setUserId(userId);
		this.netAmount = calculateNetAmount();
	}
	
	private double calculateNetAmount() {
		int total = 0;
		for(Entry<Product,Integer> i:productAndQuantity.entrySet()) {
			total += i.getKey().getPrice() * i.getValue();
		}
		return total;
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
	
	public double getNetAmount() {
		return netAmount;
	}
	
	@Override
	public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\tUser ID: ").append(userId).append("\n");
        stringBuilder.append("\tProducts in Cart:\n");
        for (Map.Entry<Product, Integer> entry : productAndQuantity.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            stringBuilder.append("  - ").append(product.getName()).append(": ")
                    .append(quantity).append(" (each $").append(product.getPrice())
                    .append(")  -  : ")
                    .append(product.getPrice()* entry.getValue())
                    .append("\n");
        }
        stringBuilder.append("Net Amount: $").append(netAmount).append("\n");
        return stringBuilder.toString();
    }
	
}
