package roles;

import java.sql.Date;
import java.util.List;

public class Order{
	
	private String orderId;
	private List<Product> products;
	private Date date;
	private String status;
	private int totalAmount;
	public Order(List<Product> list,Date date) {
		setDate(date);
		setProduct(list);
		setTotalAmount(calculateTotalAmount());
	}
	
	private int calculateTotalAmount() {
		int netAmount = 0;
		for(Product i:products)
			netAmount += i.getPrice() * i.getQuantity();
		return netAmount;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<Product> getProduct() {
		return products;
	}
	public void setProduct(List<Product> product) {
		this.products = product;
	}
	public boolean equals(String orderId) {
		return this.orderId.equals(orderId);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
}
