package roles;

import java.sql.Timestamp;
import java.util.List;

public class Order{
	
	private int orderId;
	private String userName;
	private List<Product> products;
	private Timestamp date;
	private String status;
	private int totalAmount;
	public Order(List<Product> list,Timestamp date) {
		setDate(date);
		setProduct(list);
		setTotalAmount(calculateTotalAmount());
	}
	
	public Order(int orderId, String userName, Timestamp orderDate, int totalAmount , String status, List<Product> products) {
		setDate(orderDate);
		setUserName(userName);
		setOrderId(orderId);
		setProduct(products);
		setStatus(status);
		setTotalAmount(totalAmount);
	}

	private int calculateTotalAmount() {
		int netAmount = 0;
		for(Product i:products)
			netAmount += i.getPrice() * i.getQuantity();
		return netAmount;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<Product> getProduct() {
		return products;
	}
	public void setProduct(List<Product> product) {
		this.products = product;
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

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order ID: ").append(this.orderId).append("\n");
		for (Product product : products) {
			sb.append(product.toString()).append("\n");
		}
		return sb.toString();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
