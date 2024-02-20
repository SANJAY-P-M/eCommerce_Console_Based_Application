package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import doaException.NoSuchProductException;
import doaException.StockNotAvailable;
import eCommerce_Console_Based_Application.Assets;
import roles.Product;

public class ProductTable {
	
//	returns 
//	true when product added successfully
	public static boolean insertProduct(Product product){
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement =  Connector.getInstance().getConnection().prepareStatement("INSERT INTO PRODUCTS ( productName , productDescription , price ,review,availableQuantity ) VALUES ( ? , ? , ? , ? , ?)",Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.setDouble(4, product.getReview());
			statement.setInt(5, product.getAvailableQuantity());
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			if(result.next()) product.setId(result.getInt(1));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			Assets.closeStatement(statement);	
			Assets.closeResultSet(result);
		}
	}
	
//	returns
//		List of products from database
	public static List<Product> getAllProducts(){
		List<Product> list = new ArrayList<>();
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("SELECT * FROM PRODUCTS");			
			result = statement.executeQuery();
			while(result.next()) {
				list.add(
					new Product(
						result.getInt(1), 
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
	public static Product getProduct(int productId) throws NoSuchProductException {
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCTID = ?");
			statement.setInt(1, productId);
			result = statement.executeQuery();
			if(result.next()) 
				return new Product(
					result.getInt(1),
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
		throw new NoSuchProductException(String.valueOf(productId));
	}
	
//	returns
//		true  if product is available in given quantity 
//		else throw Exception when there is no product with product Id
	public static boolean isProductAvailable(Product product,int quantity){
		ResultSet result = null;
		PreparedStatement statement = null;
		int availableQuantity = -1;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("SELECT availableQuantity FROM PRODUCTS WHERE productId = ?");
			statement.setInt(1, product.getId());
			result = statement.executeQuery();
			if(result.next()) availableQuantity = result.getInt("availableQuantity");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		return (availableQuantity >= quantity);
	}

	public static void reduceQuantity(Product product, int quantity){
		PreparedStatement statement = null;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("UPDATE PRODUCTS SET availableQuantity = ? WHERE productId = ? ");
			Product temp = getProduct(product.getId());
			statement.setInt(1, temp.getAvailableQuantity()-quantity);
			statement.setInt(2, product.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchProductException e) {
			e.printStackTrace();
		}finally {
			Assets.closeStatement(statement);
		}
	}
	
}
