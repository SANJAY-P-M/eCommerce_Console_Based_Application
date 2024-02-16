package eCommerce_Console_Based_Application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
public class Assets {
	
	public static Scanner scan = new Scanner(System.in);
	
//	Sign up prompt
	public static String[] signUpPromptList = {"Sign up (new user)","Sign in (existing user)","Close Application"};
	
	public static Connection connection;
	
	public static void createConnection(){		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "12345");
			
		}catch(SQLException e) {
			System.out.println("         Cannot connect to db server busy ");
			e.printStackTrace();
		}
	}
}
