package roles;

import java.sql.Timestamp;
import java.util.Map;

public class Order{
	
	private int orderId;
	private int userId;
	private Map<Product,Integer> productAndQuantity;
	private Timestamp date;
	private String status;
	private double totalAmount;
	private Employee delivery;
	
	public Order(int orderId, int userId, Timestamp orderDate , String status, Map<Product,Integer> productAndQuantity) {
		setDate(orderDate);
		setUserId(userId);
		setOrderId(orderId);
		setStatus(status);
		setTotalAmount(calculateTotalAmount());
		setProductAndQuantity(productAndQuantity);
	}

	public Order(int orderId, int userId, Timestamp orderDate, double totalAmount, String status,
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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
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
	
	@Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order ID: ").append(orderId).append("\n");
        stringBuilder.append("User ID: ").append(userId).append("\n");
        stringBuilder.append("Date: ").append(date).append("\n");
        stringBuilder.append("Status: ").append(status).append("\n");
        stringBuilder.append("Products and Quantity: \n");
        for (Map.Entry<Product, Integer> entry : productAndQuantity.entrySet()) {
        	Product product = entry.getKey();
        	int quantity = entry.getValue();
        	stringBuilder.append(" - ").append(product.getName()).append(": ").append(quantity).append("\n");
        }
        stringBuilder.append("Total Amount: ").append(totalAmount).append("\n")
        	.append("Delivery Employee Number : ").append(this.delivery.getMobileNumber());
        return stringBuilder.toString();
    }

	public Employee getDelivery() {
		return delivery;
	}

	public void setDelivery(Employee delivery) {
		this.delivery = delivery;
	}
}
