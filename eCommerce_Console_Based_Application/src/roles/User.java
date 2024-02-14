package roles;

//abstract classes are made to increase modularity our project
public abstract class User {
//	protected variables can be inherited to child class
	protected String userName;
	protected String mobileNumber;

	void seeProfile() {
		
	}
	
//	yet to be implemented
	void seeProducts();
	void seeProductDetails();
	void addToWishList();
	void seeWishList();
}
