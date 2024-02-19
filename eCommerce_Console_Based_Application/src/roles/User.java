package roles;

import java.util.List;

public class User{
	private int userId;
	private String fullName;
	private String email;
	private String password;
	private String mobileNumber;
	private List<Order> orders;
	private Cart cart;
	
	public User(String fullName,String email,String mobileNumber, String password) {
		setFullName(fullName);
		setEmail(email);
		setMobileNumber(mobileNumber);
		setPassword(password);
	}
	
	public User(int userId,String fullName,String email,String mobileNumber,String password,List<Order> orders,Cart cart) {
		setUserId(userId);
		setEmail(email);
		setFullName(fullName);
		setMobileNumber(mobileNumber);
		setOrders(orders);
		setPassword(password);
		setCart(cart);
	}
	
//	Copy constructor
	public User(User user) {
		setUserId(userId);
		setFullName(fullName);
		setEmail(email);
		setMobileNumber(mobileNumber);
		setPassword(password);
		setOrders(orders);
		setCart(cart);
	}
	
	public void setUserId(int userName) {
		this.userId = userName;
	}
	
	public int getUserId() {
		return this.userId;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	 @Override
    public String toString() {
        return "User{" +
                "userName='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", orders=" + orders +
                ",cart = "+ cart +
                '}';
    }

}
