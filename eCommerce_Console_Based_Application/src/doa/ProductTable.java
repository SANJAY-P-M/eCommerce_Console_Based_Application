package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import eCommerce_Console_Based_Application.Assets;
import eCommerce_Console_Based_Application.Assets.NoSuchProductException;
import roles.Product;

public class ProductTable {
	
//	returns 
//	true when product added successfully
//	false when product already exists
	public static boolean addProduct(Product product) throws Exception {
		PreparedStatement statement = null;
		int noOfRowsUpdated = 0;
		try {
			statement =  Assets.connection.prepareStatement("INSERT INTO PRODUCTS VALUES ( ? , ? , ? , ? , ? , ? )");
			statement.setString(1, product.getId());
			statement.setString(2, product.getName());
			statement.setString(3, product.getDescription());
			statement.setDouble(4, product.getPrice());
			statement.setDouble(5, product.getReview());
			statement.setInt(6, product.getQuantity());
			noOfRowsUpdated = statement.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException duplicate) {
			throw new Exception("Product id already exists");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Assets.closeStatement(statement);			
		}
		return noOfRowsUpdated == 1;
	}
	
//	returns
//		List of products from database
	public static List<Product> getAllProducts(){
		List<Product> list = new ArrayList<>();
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			statement = Assets.connection.prepareStatement("SELECT * FROM PRODUCTS");			
			result = statement.executeQuery();
			while(result.next()) {
				list.add(
					new Product(
						result.getString(1), 
						result.getString(2), 
						result.getString(3), 
						result.getDouble(4), 
						result.getDouble(5),
						result.getInt(6)
					)
				);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		return list;
	}
	
//	returns
//		Product object with matching productId
//		if no product found throw Exception
	public static Product getProduct(String productId) throws NoSuchProductException {
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			statement = Assets.connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCTID = ?");
			statement.setString(1, productId);
			result = statement.executeQuery();
			if(result.next()) 
				return new Product(
					result.getString(1),
					result.getString(2),
					result.getString(3),
					result.getDouble(4),
					result.getDouble(5),
					result.getInt(6)
				);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		throw new Assets.NoSuchProductException(productId);
	}
	
//	returns
//		true  if product is available in given quantity 
//		else false;
//		throw Exception when there is no product with product Id
	public static boolean isProductAvailable(String productId,int quantity) throws NoSuchProductException{
		ResultSet result = null;
		PreparedStatement statement = null;
		int availableQuantity = -1;
		try {
			statement = Assets.connection.prepareStatement("SELECT availableQuantity FROM PRODUCTS WHERE productId = ?");
			statement.setString(1, productId);
			result = statement.executeQuery();
			if(result.next()) availableQuantity = result.getInt("availableQuantity");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		if(availableQuantity == -1) throw new Assets.NoSuchProductException(productId);
		return (availableQuantity >= quantity);
	}

	public static void reduceQuantity(String productId, int quantity) throws NoSuchProductException {
		PreparedStatement statement = null;
		try {
			statement = Assets.connection.prepareStatement("UPDATE TABLE PRODUCTS WHERE productId = ? SET availableQuantity = ?");
			statement.setString(1, productId);
			Product product = getProduct(productId);
			statement.setInt(2, product.getQuantity()-quantity);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Assets.closeStatement(statement);
		}
	}
	
}
