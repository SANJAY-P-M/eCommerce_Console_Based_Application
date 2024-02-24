package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import doaException.StockNotAvailable;
import eCommerce_Console_Based_Application.Assets;
import roles.Cart;
import roles.User;
import roles.Order;
import roles.Product;

public class OrderTable {
	
	
	public static Order insertOrder(int userId,Product product,int quantity){
		ResultSet result = null;
		PreparedStatement statement = null;
		Order order = null;
		try {
			double totalAmount = (product.getPrice() * quantity);
			Timestamp orderDate = Timestamp.valueOf(LocalDateTime.now());
			statement = Connector.getInstance().getConnection().prepareStatement("INSERT INTO ORDERS ( userId , totalAmount,orderDate ) VALUES ( ? , ? , ? )",Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, userId);
			statement.setDouble(2, totalAmount);
			statement.setTimestamp(3, orderDate);
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			if(result.next()) {
				int orderId = result.getInt(1);
				Map<Product,Integer> map = new HashMap<>();
				map.put(product, quantity);
				OrderItems.insert(orderId,map);
				order = new Order(orderId, userId, orderDate, totalAmount, "pending", map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Assets.closeResultSet(result);
		}
		return order;
	}
	
	public static Order getOrder(int orderId) {
		PreparedStatement statement= null;
		ResultSet result = null;
		Order order = null;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("SELECT * from ORDERS WHERE orderId = ?");
			statement.setInt(1, orderId);
			result = statement.executeQuery();
			if(result.next()) {
				int userId = result.getInt("userId");
				Timestamp orderDate = result.getTimestamp("orderDate");
				int totalAmount = result.getInt("totalAmount");
				String status = result.getString("status");
				Map<Product, Integer> allProducts = OrderItems.getAllProducts(orderId);
				order = new Order(orderId, userId, orderDate, totalAmount, status, allProducts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order; 
	}

	public static Order insertOrder(Cart cart){
		PreparedStatement statement = null;
		ResultSet result = null;
		Order order = null;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("INSERT INTO ORDERS ( userId , totalAmount ) VALUES ( ? , ? )",Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, cart.getUserId());
			statement.setDouble(2, cart.getNetAmount());
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			result.next();
			int orderId = result.getInt(1);
			OrderItems.insert(orderId, cart.getProductsAndQuantity());
			order = OrderTable.getOrder(orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	public static List<Order> getOrders(int userId){
		ResultSet result = null;
		PreparedStatement statement = null;
		List<Order> orderList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM orders WHERE userId = ?";
			statement = Connector.getInstance().getConnection().prepareStatement(sql);
			statement.setInt(1, userId);
			result = statement.executeQuery();
			while(result.next()) {
				int orderId = result.getInt("orderId");
				orderList.add(
							new Order(
									orderId,
									result.getInt("userId"),
									result.getTimestamp("orderDate"),
									result.getInt("totalAmount"),
									result.getString("status"),
									OrderItems.getAllProducts(orderId)
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
