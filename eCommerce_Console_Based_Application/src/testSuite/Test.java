package testSuite;

import doa.ProductTable;
import eCommerce_Console_Based_Application.Assets;
import roles.Product;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Assets.createConnection();
		try {
			System.out.println(
					ProductTable.addProduct(
							new Product("lap20","asus laptop","best laptop under 1000",10000.0,4.5)
				 	)
			);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
