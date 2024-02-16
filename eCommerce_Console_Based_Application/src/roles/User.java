package roles;

//abstract classes are made to increase modularity our project
public abstract class User {
//	protected variables can be inherited to child class
	protected String userName;
	protected String mobileNumber;

	public void seeProfile() {
	    String profileInfo = String.format(
	        "\n                     -----------------------\n\n" +
	        "                     **Profile Information**\n" +
	        "                     Username: %s\n" +
	        "                     Mobile Number: %s\n\n" +
	        "                     -----------------------",
	        this.userName,
	        this.mobileNumber);
	    System.out.println(profileInfo);
	}

	
//	yet to be implemented
	abstract void seeProducts();
	abstract void seeProductDetails();
	abstract void addToWishList();
	abstract void seeWishList();
}
