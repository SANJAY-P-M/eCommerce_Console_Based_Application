package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eCommerce_Console_Based_Application.Assets;
import eCommerce_Console_Based_Application.Assets.StockNotAvailable;
import roles.Cart;
import roles.Customer;
import roles.Order;
import roles.Product;

public class OrderTable {
	
	
	public static void insertOrder(Customer customer,Product product,int quantity) throws StockNotAvailable {
		if(!ProductTable.isProductAvailable(product, quantity)) return;
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			double totalAmount = product.getPrice() * quantity;
			statement = Assets.connection.prepareStatement("INSERT INTO ORDERS ( userName , totalAmount ) VALUES ( ? , ? )",Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, customer.getUserName());
			statement.setDouble(2, totalAmount);
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			result.next();
			int orderId = result.getInt(1);
			OrderItems.insert(orderId,product,quantity);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Assets.closeResultSet(result);
		}
	}
	
	public static void insertOrder(Customer customer,Cart cart) throws StockNotAvailable {
		Map<Product,Integer> cartProduct = cart.getProductsAndQuantity();
		for(Map.Entry<Product, Integer> i:cartProduct.entrySet())
			if(!ProductTable.isProductAvailable(i.getKey(), i.getValue()))
				return;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = Assets.connection.prepareStatement("INSERT INTO ORDERS ( userName , totalAmount ) VALUES ( ? , ? )",Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, cart.getUserName());
			statement.setDouble(2, cart.getNetAmount());
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			result.next();
			int orderId = result.getInt(1);
			OrderItems.insert(orderId, cart.getProductsAndQuantity());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static void insert(Order order,Customer customer) {
//		PreparedStatement insertOrderTableStatement = null;
//		PreparedStatement insertOrderItemsStatement = null;
//		ResultSet result = null;
//		try {
//			insertOrderTableStatement = Assets.connection.prepareStatement("INSERT INTO ORDERS ( userName , orderDate ,totalAmount ) VALUES ( ? , ? , ?)",Statement.RETURN_GENERATED_KEYS);
//			insertOrderTableStatement.setString(1, customer.getUserName());
//			insertOrderTableStatement.setString(2, order.getDate());
//			insertOrderTableStatement.setInt(3, order.getTotalAmount());
//			insertOrderTableStatement.executeUpdate();
//			result = insertOrderTableStatement.getGeneratedKeys();
//			int orderId = result.getInt("orderId");
//			insertOrderItemsStatement = Assets.connection.prepareStatement("INSERT INTO orderItems values ( "+orderId+" ,  ? , ? )");
//			for(Product i:order.getProduct()) {
//				insertOrderItemsStatement.setString(1, i.getId());
//				insertOrderItemsStatement.setInt(2, i.getQuantity());
//				insertOrderItemsStatement.executeUpdate();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			Assets.closeResultSet(result);
//			Assets.closeStatement(insertOrderItemsStatement);
//			Assets.closeStatement(insertOrderTableStatement);
//		}
//	}
//	
//	public static Order placeOrder(Customer customer){
//		if(customer.getCart().getProducts().isEmpty()) throw new Assets.EmptyCartException();
//		PreparedStatement statement = null;
//		ResultSet result = null;
//		List<Product> list = new ArrayList<>();
//		try {
//			statement = Assets.connection.prepareStatement("SELECT c.productId , c.quantity , p.productName , p.productDescription , p.price ,p.review  FROM CART c WHERE c.userName = ? JOIN PRODUCTS p ON o.productId = c.productId");
//			statement.setString(1, customer.getUserName());
//			result = statement.executeQuery();
//			while(result.next()) {
//				String productId = result.getString("c.productId");
//				int quantity = result.getInt("c.quantity");
//				String productName = result.getString("p.productName");
//				double price = result.getDouble("p.price");
//				double review = result.getDouble("p.review");
//				String description = result.getString("p.description");
//				Product product = new Product(productId, productName, description, price, review, quantity);
//				if(ProductTable.isProductAvailable(productId, quantity))
//					ProductTable.reduceQuantity(productId,quantity);
//				else throw new Assets.StockNotAvailable(productId,ProductTable.getProduct(productId).getQuantity());
//				list.add(product);
//			}
//			Order order = new Order(list, LocalDateTime.now());
//			OrderTable.insert(order,customer);
//			return order;
//		}catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			Assets.closeStatement(statement);
//		}
//	}
//	


	public static List<Order> getOrders(Customer customer){
		ResultSet result = null;
		PreparedStatement statement = null;
		List<Order> orderList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM orders WHERE userName = ?";
			statement = Assets.connection.prepareStatement(sql);
			statement.setString(1, customer.getUserName());
			result = statement.executeQuery();
			while(result.next()) {
				orderList.add(
							new Order(
									result.getInt("orderId"),
									result.getString("userName"),
									result.getTimestamp("orderDate"),
									result.getInt("totalAmount"),
									result.getString("status"),
									OrderItems.getAllProducts(result.getInt("orderId"))
							)
						);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		return orderList;
	}
	
	
}
