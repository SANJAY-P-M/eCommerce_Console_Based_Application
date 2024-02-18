package doa;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import eCommerce_Console_Based_Application.Assets;
import eCommerce_Console_Based_Application.Assets.NoSuchProductException;
import roles.Cart;
import roles.Customer;
import roles.Order;
import roles.Product;

public class OrderTable {

	public static void insert(Order order) {
//		Insert data to orders and orderItems table
	}
	
//	public static List<Order> getOrders(Customer customer){
//		ResultSet result = null;
//		PreparedStatement statement = null;
//		List<Order> list = new ArrayList<>();
//		try {
//			String sql = "SELECT FROM orders WHERE userName = ? JOIN orderItems ";
//			statement = Assets.connection.prepareStatement(sql);
//			statement.setString(1, customer.getUserName());
//			result = statement.executeQuery();
//			while(result.next()) {
//				String orderId = result.getString("orderId");
//				Product product = new Product(
//									result.getString("productId"),
//									result.getString("productName"),
//									result.getString("productDescription"),
//									result.getDouble("price"),
//									result.getDouble("review"),
//									result.getInt("quantity")
//								);	
//				boolean added = false;
//				for(Order i:list) {
//					if(i.equals(orderId)) i.getProduct().add(product);
//					added = true;
//				}
//				if(!added) {
//					List<Product> temp = new ArrayList<>();
//					temp.add(product);
//					list.add(new Order(orderId,temp));
//				}
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			Assets.closeResultSet(result);
//			Assets.closeStatement(statement);
//		}
//		return list;
//	}
	
	
}
