package roles;

import java.sql.Timestamp;
import java.util.Map;

public class Order{
	
	private int orderId;
	private int userId;
	private Map<Product,Integer> productAndQuantity;
	private Timestamp date;
	private String status;
	private int totalAmount;
	
	public Order(int orderId, int userId, Timestamp orderDate , String status, Map<Product,Integer> productAndQuantity) {
		setDate(orderDate);
		setUserId(userId);
		setOrderId(orderId);
		setStatus(status);
		setTotalAmount(calculateTotalAmount());
		setProductAndQuantity(productAndQuantity);
	}

	public Order(int orderId, int userId, Timestamp orderDate, int totalAmount, String status,
			Map<Product, Integer> allProducts) {
		setDate(orderDate);
		setOrderId(orderId);
		setProductAndQuantity(allProducts);
		setStatus(status);
		setTotalAmount(totalAmount);
		setUserId(userId);
	}

	private int calculateTotalAmount() {
		int totalAmount = 0;
		for(Map.Entry<Product,Integer> i:productAndQuantity.entrySet()) {
			totalAmount += i.getKey().getPrice() * i.getValue();
		}
		return totalAmount;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Map<Product,Integer> getProductAndQuantity() {
		return productAndQuantity;
	}

	public void setProductAndQuantity(Map<Product,Integer> productAndQuantity) {
		this.productAndQuantity = productAndQuantity;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
