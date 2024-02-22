package roles;


import java.util.List;
import java.util.Map;

import doa.CartTable;
import doa.OrderTable;
import doa.ProductTable;
import doaException.ProductNotSelectedException;
import doaException.StockNotAvailable;
import eCommerce_Console_Based_Application.ECommerceApplication;

public class Customer extends User {

	private List<Order> orders;
	private Cart cart;
	private Product selected;
	
	public Customer(User user) {
		super(user);
		orders = OrderTable.getOrders(this.getUserId());
		cart = CartTable.getCart(this.getUserId());
	}
	
	public Customer(String fullName , String email , String mobileNumber,String password) {
		super(fullName, email, mobileNumber, password);
	}
	
	public Product getSelected() {
		return selected;
	}

	public void setSelected(Product selected) {
		this.selected = selected;
	}
	
	public void makeOrder(int quantity) throws StockNotAvailable, ProductNotSelectedException{
		
		if(this.selected == null) throw new ProductNotSelectedException();
		
//		Check Availability
		if(!ProductTable.isProductAvailable(this.getSelected(), quantity))
			throw new StockNotAvailable(getSelected());
		
//		Update in table 
		Order order = OrderTable.insertOrder(this, this.getSelected(), quantity);
		ProductTable.reduceQuantity(this.getSelected(), quantity);
		
//		Update in local instance
		this.getOrders().add(order);
	}
	
	public boolean addToCart(int quantity) throws StockNotAvailable, ProductNotSelectedException {
		
		if(this.selected == null) throw new ProductNotSelectedException();
		
//		Check Availability
		if(!ProductTable.isProductAvailable(this.getSelected(), quantity))
			throw new StockNotAvailable(this.getSelected());
		
//		Insert in table
		CartTable.insert(this.getUserId(), selected.getId(), quantity);
		
//		update in object
		Map<Product,Integer> map = this.getCart().getProductsAndQuantity();
		map.put(this.getSelected(), map.getOrDefault(this.getSelected(), 0)+quantity);
		
		return true;
	}
	
	public boolean checkOutFromCart() throws StockNotAvailable {
			
			Cart cart = this.getCart();
			
	//		Check Availability
			Map<Product,Integer> cartProduct = cart.getProductsAndQuantity();
			for(Map.Entry<Product, Integer> i:cartProduct.entrySet())
				if(!ProductTable.isProductAvailable(i.getKey(), i.getValue()))
					throw new StockNotAvailable(i.getKey());
			
	//		Insert into Order table
			Order order = OrderTable.insertOrder(cart);
			for(Map.Entry<Product, Integer> i:cartProduct.entrySet())
				ProductTable.reduceQuantity(i.getKey(), i.getValue());
			CartTable.makeEmpty(cart.getUserId());
			
	//		Finally add new Order to user class
			this.getOrders().add(order);
			this.setCart(null);
			
			return true;
	}
	
	public void removeFromCart(Product product) {
		
//		update in table
		CartTable.remove(getUserId(),product.getId());
		
		this.getCart().getProductsAndQuantity().remove(product);
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void viewAllProducts() {
		
		List<Product> list = ProductTable.getAvailableProducts();
		
		for(Product i:list)
			System.out.println(i);
		
		
	}
	
}
