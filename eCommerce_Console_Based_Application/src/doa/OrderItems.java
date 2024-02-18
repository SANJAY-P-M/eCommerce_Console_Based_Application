package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eCommerce_Console_Based_Application.Assets;
import eCommerce_Console_Based_Application.Assets.NoSuchProductException;
import eCommerce_Console_Based_Application.Assets.StockNotAvailable;
import roles.Product;

public class OrderItems {

	public static List<Product> getAllProducts(int orderId){
		ResultSet result = null;
		PreparedStatement statement = null;
		List<Product> products = new ArrayList<>();
		try {
			statement = Assets.connection.prepareStatement("SELECT * FROM orderItems WHERE orderId = ?");
			statement.setInt(1, orderId);
			result = statement.executeQuery();
			while(result.next()) {
				Product product = ProductTable.getProduct(result.getString("productId"));
				product.setQuantity(result.getInt("quantity"));
				products.add(product);
			}
		} catch (SQLException e) {
			
		} catch (NoSuchProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		return products;
	}

	public static void insert(int orderId, Product product, int quantity){
		PreparedStatement statement = null;
		try {
			statement = Assets.connection.prepareStatement("INSERT INTO orderItems VALUES ( ? , ? , ? )");
			statement.setInt(1, orderId);
			statement.setString(2 ,product.getId());
			statement.setInt(3, quantity);
			statement.executeUpdate();
			ProductTable.reduceQuantity(product, quantity);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Assets.closeStatement(statement);
		}
	}

	public static void insert(int orderId, List<Product> products) {
		PreparedStatement statement = null;
		try {
			statement = Assets.connection.prepareStatement("INSERT INTO OrderItems VALUES (? , ? , ?)");
			for(Product i:products) {
				statement.setInt(1, orderId);
				statement.setString(2, i.getId());
				statement.setInt(3, i.getQuantity());
				statement.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
