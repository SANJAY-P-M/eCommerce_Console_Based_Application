package eCommerce_Console_Based_Application;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import doa.CartTable;
import doa.EmployeeWorksTable;
import doa.OrderTable;
import doa.ProductTable;
import doa.UserTable;
import doaException.NoSuchProductException;
import roles.Cart;
import roles.Employee;
import roles.Order;
import roles.Product;
import roles.User;
import services.UICards;

public class ECommerceApplication {
	
	private List<Product> products;
	private static List<Employee> employees;
	
	public List<Product> getAllProducts() {
		return this.products;
	}
	
	public static Employee assignOrder(int id,Order order) {
//		Assign work to the employee who has less work
		Employee emp = Collections.min(employees, (e1,e2)->Integer.compare(e1.getHisWorks().size(), e2.getHisWorks().size()));
		emp.getHisWorks().add(order);
		if(EmployeeWorksTable.insert(id,order.getOrderId()))
			return emp;
		else return null;
	}
	
	public ECommerceApplication(){
		products = ProductTable.getAllProducts();
		employees = UserTable.getEmployees();
	}
	
	public Product getProduct(int productId) {
		for(Product i:products)
			if(i.getId() == productId)
				return i;
		return null;
	}

	public List<Product> getAvailableProducts() {
		return products.stream()
				.filter(element -> element.getAvailableQuantity() > 0)
				.collect(Collectors.toList());
	}
}
