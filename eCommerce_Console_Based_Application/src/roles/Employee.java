package roles;

import java.util.List;

import doa.EmployeeWorksTable;

public class Employee extends User{

	private List<Order> hisWorks;
	
	public Employee(User user) {
		super(user);
		this.setHisWorks(EmployeeWorksTable.getWorks(this.getUserId()));
	}

	public List<Order> getHisWorks() {
		return hisWorks;
	}

	public void setHisWorks(List<Order> hisWorks) {
		this.hisWorks = hisWorks;
	}
	
}
