package testSuite;

import java.util.List;

import doa.OrderTable;
import doa.ProductTable;
import doa.UserTable;
import eCommerce_Console_Based_Application.Assets;
import roles.Customer;
import roles.Order;
import roles.Product;
import eCommerce_Console_Based_Application.Assets;

public class Test {

	public static void main(String[] args) throws Exception {
		
		Assets.createConnection();
		
//		ProductTable.addProduct()
//		ProductTable.addProduct(new Product("lap21","asus laptop","best laptop under 1000",1000.0,5.6,10));
		
//		ProductTable.getProductsList();
//		System.out.println(ProductTable.getAllProducts());
		
//		System.out.print(Assets.noUserFound.equals(Assets.noUserFound));
		
//		ProductTable.getProduct()
//		System.out.println(ProductTable.getProduct("la101"));
		
//		UserTable.insertNewUser()
//		try {
//			Customer customer = new Customer("sanj0","SANJAY MURUGESAN","123","mi@mail.com","7894561230");
//			System.out.println(UserTable.insertNewUser(customer));			
//		}catch (Assets.UserNameAlreadyExistsException e) {
//			System.out.println("oki");
//		}catch (Assets.MobileNumberAlreadyExistsException e) {
//			 System.out.println("mobile already exists");
//		}
//		catch (Assets.EmailAlreadyExistsException e) {
//			System.out.println("mail already exists");
//		}
		
//		UserTable.getPassword()
//		try {
//			System.out.println(UserTable.getPassword("san"));			
//		}catch (Assets.NoUserFoundException e) {
//			 System.out.println("no User found");
//		}
		
//		Order
//		System.out.println(OrderTable.getOrders(new Customer("SANJAY MURUGESAN","123","mail@mail.com","9876543210")));
		
		OrderTable.insertOrder(
				new Customer("sanj0","SANJAY MURUGESAN","123","mi@mail.com","7894561230"),
				ProductTable.getProduct("lap201"),
				1
		);
	}

}
