package doa;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import eCommerce_Console_Based_Application.Assets;
import roles.Product;

public class ProductTable {
	
//	returns 
//	true when product added successfully
//	false when product already exists
	public static boolean addProduct(Product product) throws Exception {
		try {
			PreparedStatement statement =  Assets.connection.prepareStatement("INSERT INTO PRODUCTS VALUES ( ? , ? , ? , ? , ? )");
			statement.setString(1, product.getId());
			statement.setString(2, product.getName());
			statement.setString(3, product.getDescription());
			statement.setDouble(4, product.getPrice());
			statement.setDouble(5, product.getReview());
			return statement.executeUpdate() == 1;
		} catch (SQLIntegrityConstraintViolationException duplicate) {
			throw new Exception("Product id already exists");
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
