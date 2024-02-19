package roles;


public class Customer extends User {
	
	public Customer(User user) {
		super(user);
	}
	
	public Customer(String fullName , String email , String mobileNumber,String password) {
		super(fullName, email, mobileNumber, password);
	}
}
