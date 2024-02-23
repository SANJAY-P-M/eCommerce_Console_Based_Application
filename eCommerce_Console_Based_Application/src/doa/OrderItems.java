package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import doaException.NoSuchProductException;
import eCommerce_Console_Based_Application.Assets;
import roles.Product;

public class OrderItems {

	public static Map<Product,Integer> getAllProducts(int orderId){
		ResultSet result = null;
		PreparedStatement statement = null;
		Map<Product,Integer> productAndQuantity = new HashMap<>();
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("SELECT * FROM orderItems WHERE orderId = ?");
			statement.setInt(1, orderId);
			result = statement.executeQuery();
			while(result.next()) {
				Product product = ProductTable.getProduct(result.getInt("productId"));
				productAndQuantity.put(product,result.getInt("quantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchProductException e) {
			e.printStackTrace();
		} finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		return productAndQuantity;
	}

	public static void insert(int orderId, Map<Product,Integer> products) {
		PreparedStatement statement = null;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("INSERT INTO OrderItems (orderId , productId , quantity) VALUES (? , ? , ?)");
			for(Map.Entry<Product, Integer> i:products.entrySet()) {
				statement.setInt(1, orderId);
				statement.setInt(2, i.getKey().getId());
				statement.setInt(3, i.getValue());
				statement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
