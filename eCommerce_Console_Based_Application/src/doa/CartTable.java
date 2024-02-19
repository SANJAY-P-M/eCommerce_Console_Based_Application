package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import doaException.NoSuchProductException;
import eCommerce_Console_Based_Application.Assets;
import roles.Cart;
import roles.Product;
import roles.User;

public class CartTable {
	
	public static void insert(User user,Product product,int quantity) {
		PreparedStatement statement = null;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("INSERT INTO Cart VALUES ( ? , ? , ? )");
			statement.setInt(1, user.getUserId());
			statement.setInt(2, product.getId());
			statement.setInt(3, quantity);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Assets.closeStatement(statement);
		}
	}
	
	
	public static Cart getCart(int userId) {
		PreparedStatement statement = null;
		ResultSet result;
		HashMap<Product,Integer> productAndQuantityMap = new HashMap<>();
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("SELECT * FROM Carts WHERE userId = ?");
			statement.setInt(1, userId);
			result = statement.executeQuery();
			while(result.next()) {
				productAndQuantityMap.put(
						ProductTable.getProduct(result.getInt("productId")),
						result.getInt("quantity")
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchProductException e) {
			e.printStackTrace();
		}
		return new Cart(userId,productAndQuantityMap);
	}
}