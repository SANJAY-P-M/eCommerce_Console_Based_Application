package services;

import java.util.Scanner;

import doa.UserTable;
import roles.User;

public class Authentication {

	public static User createNewUser(String userName, String mobileNumber ,String password) {
	    User user = null;
	    
	    // When you authenticate in frontEnd of your application the you can reduce workLoad on server
	    // Input validation loop
	    while (user == null) {
	        user = UserTable.createUser(userName, mobileNumber, password);
	    }
	    UICards.printWelcomeMessage(userName);	
	    UICards.printSucessMessage("Username successfully created!");
	    return user;
}

	public static User authenticate(String username,String password){
		User user = null;
		do{
			user = UserTable.loginUser(username, password);
			if(user == null) password = LoginServices.getPassword(new Scanner(System.in));
		}while(user == null);
		UICards.printWelcomeMessage(username);
		return user;
	}

	

}
