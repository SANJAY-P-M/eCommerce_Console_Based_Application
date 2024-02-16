package testSuite;

import java.util.List;

import doa.OrderTable;
import doa.ProductTable;
import doa.UserTable;
import eCommerce_Console_Based_Application.Assets;
import roles.Customer;
import roles.Order;
import roles.Product;

public class Test {

	public static void main(String[] args) throws Exception {
		
		Assets.createConnection();
		
//		ProductTable.addProduct()
//		ProductTable.addProduct(new Product("lap201","asus laptop","best laptop under 1000",1000.0,5.6,10));
		
//		ProductTable.getProductsList();
//		System.out.println(ProductTable.getAllProducts());
		
//		ProductTable.getProduct()
//		System.out.println(ProductTable.getProduct("la101"));
		
//		UserTable.insertNewUser()
//		UserTable.insertNewUser(new Customer("SANJAY MURUGESAN","123","mail@mail.com","9876543210"));
		
//		UserTable.getPassword()
//		System.out.println(UserTable.getPassword("mail@ail.com"));
		
//		Order
		System.out.println(OrderTable.getOrders(new Customer("SANJAY MURUGESAN","123","mail@mail.com","9876543210")));
	}

}
