package roles;

import java.util.List;

import doa.UserTable;

public class Admin extends User{
	
	private List<Employee> employees;
	private Product selected;
	
	public Admin(User user) {
		super(user);
		employees = UserTable.getEmployees();
	}

	public boolean addEmployee(User user) {
		return (Employee) UserTable.insert(user, "employee") != null;
	}

	public boolean removeEmployee(String email) {
		for(int i = 0;i < employees.size();i++) {
			if(employees.get(i).getEmail().equals(email)) {
//				Update in member and also in table
				employees.remove(i);
				return UserTable.delete(email);
			}
		}
		return false;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void setSelected(Product product) {
		this.selected = product;
	}
	
	public void unSelect() {
		this.selected = null;
	}

	public boolean updateProductPrice(double price) {
		return this.selected.setPrice(price);
	}

	public boolean addQuantity(int quantity) {
		return this.selected.setAvailableQuantity(this.selected.getAvailableQuantity()+quantity);
	}

	public boolean reduceQuantity(int quantity) {
		return this.selected.setAvailableQuantity(this.selected.getAvailableQuantity() - quantity);
	}
}
