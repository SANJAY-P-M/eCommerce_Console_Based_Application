package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import eCommerce_Console_Based_Application.Assets;
import roles.Customer;
import roles.Order;
import roles.Product;

public class CartTable {
	
	public static Order placeOrder(Customer customer){
		if(customer.getCart().getProducts().isEmpty()) throw new Assets.EmptyCartException();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Product> list = new ArrayList<>();
		try {
			statement = Assets.connection.prepareStatement("SELECT c.productId , c.quantity , p.productName , p.productDescription , p.price ,p.review  FROM CART c WHERE c.userName = ? JOIN PRODUCTS p ON o.productId = c.productId");
			statement.setString(1, customer.getUserName());
			result = statement.executeQuery();
			while(result.next()) {
				String productId = result.getString("c.productId");
				int quantity = result.getInt("c.quantity");
				String productName = result.getString("p.productName");
				double price = result.getDouble("p.price");
				double review = result.getDouble("p.review");
				String description = result.getString("p.description");
				Product product = new Product(productId, productName, description, price, review, quantity);
				if(ProductTable.isProductAvailable(productId, quantity))
					ProductTable.reduceQuantity(productId,quantity);
				else throw new Assets.StockNotAvailable(productId,ProductTable.getProduct(productId).getQuantity());
				list.add(product);
			}
			Order order = new Order(list, LocalDateTime.now());
			OrderTable.insert(order);
			return order;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assets.closeStatement(statement);
		}
	}
	
}
