package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import doaException.StockNotAvailable;
import eCommerce_Console_Based_Application.Assets;
import roles.Cart;
import roles.User;
import roles.Order;
import roles.Product;

public class OrderTable {
	
	
	public static void insertOrder(User user,Product product,int quantity) throws StockNotAvailable {
		if(!ProductTable.isProductAvailable(product, quantity)) return;
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			double totalAmount = product.getPrice() * quantity;
			statement = Connector.getInstance().getConnection().prepareStatement("INSERT INTO ORDERS ( userId , totalAmount ) VALUES ( ? , ? )",Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, user.getUserId());
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
	
	public static void insertOrder(Cart cart) throws StockNotAvailable {
		Map<Product,Integer> cartProduct = cart.getProductsAndQuantity();
		for(Map.Entry<Product, Integer> i:cartProduct.entrySet())
			if(!ProductTable.isProductAvailable(i.getKey(), i.getValue()))
				return;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("INSERT INTO ORDERS ( userId , totalAmount ) VALUES ( ? , ? )",Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, cart.getUserId());
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
				orderList.add(
							new Order(
									result.getInt("orderId"),
									result.getInt("userId"),
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
