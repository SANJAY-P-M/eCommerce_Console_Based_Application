package roles;

public class Customer extends User{
	public Customer(String userName , String mobileNumber){
		this.userName = userName;
		this.setMobileNumber(mobileNumber);
	}

	@Override
	void seeProducts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void seeProductDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void addToWishList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void seeWishList() {
		// TODO Auto-generated method stub
		
	}
	

}
