package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import eCommerce_Console_Based_Application.Assets;
import roles.Customer;
import roles.Order;
import roles.Product;

public class OrderTable {
	
	public static List<Order> getOrders(Customer customer){
		ResultSet result = null;
		PreparedStatement statement = null;
		List<Order> list = new ArrayList<>();
		try {
			String sql = "SELECT o.orderId AS \"orderId\" , oi.productId AS \"productId\" , p.productName AS "
					+ "\"productName\" , p.productDescription AS \"productDescription\" , oi.quantity AS \"quantity\","
					+ "p.price AS \"price\" , p.review AS \"review\""
								+ " FROM ORDERS O JOIN ORDERED_ITEMS oi ON o.orderId = oi.orderId "
									+ "JOIN PRODUCTS P ON P.productId = oi.productId WHERE O.USERID = 1";
			statement = Assets.connection.prepareStatement(sql);
//			statement.setString(1, customer.getEmail());
			result = statement.executeQuery();
			while(result.next()) {
				String orderId = result.getString("orderId");
				Product product = new Product(
									result.getString("productId"),
									result.getString("productName"),
									result.getString("productDescription"),
									result.getDouble("price"),
									result.getDouble("review"),
									result.getInt("quantity")
								);	
				boolean added = false;
				for(Order i:list) {
					if(i.equals(orderId)) i.getProduct().add(product);
					added = true;
				}
				if(!added) {
					List<Product> temp = new ArrayList<>();
					temp.add(product);
					list.add(new Order(orderId,temp));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		return list;
	}
	
	public static boolean placeOrder(Product product,int quantity) {
		PreparedStatement statement = null;
		try {
			statement = Assets.connection.prepareStatement("INSERT INTO ORDERS ( USERID ) VALUES( ? )");
		}
	}
}