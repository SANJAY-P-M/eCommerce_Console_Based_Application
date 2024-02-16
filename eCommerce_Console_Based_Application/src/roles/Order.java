package roles;

import java.util.List;

public class Order{
	private String orderId;
	private List<Product> product;
	
	public Order(String orderId,List<Product> list) {
		setOrderId(orderId);
		setProduct(list);
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public boolean equals(String orderId) {
		return this.orderId.equals(orderId);
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Order ID: ").append(this.orderId).append("\n");
	    for (Product product : product) {
	        sb.append(product.toString()).append("\n");
	    }
	    return sb.toString();
	}

}
