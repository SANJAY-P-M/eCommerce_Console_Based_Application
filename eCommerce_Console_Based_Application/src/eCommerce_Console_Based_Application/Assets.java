package eCommerce_Console_Based_Application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Assets {
	
	public static class StockNotAvailable extends Exception {
		public StockNotAvailable(String productId,int availableQuantity) {
			super(availableQuantity+" stock only availabe in productId "+productId);
		}
	}
	public static class NoSuchProductException extends Exception {
		public NoSuchProductException(String productId) {
			super("product not found with productId : "+productId);
		}
	}
	public static class NoUserFoundException extends Exception {
		public NoUserFoundException() {
			super("No user found");
		}	
	};
	public static class UserNameAlreadyExistsException extends Exception {
		public UserNameAlreadyExistsException() {
			super("User already exists");
		}
	};
	public static class EmailAlreadyExistsException extends Exception {
		public EmailAlreadyExistsException() {
			super("E mail already exists");
		}	
	};
	public static class MobileNumberAlreadyExistsException extends Exception {
		public MobileNumberAlreadyExistsException() {
			super("Mobile number already exists");
		}
	};
	public static class EmptyCartException extends Exception{
		public EmptyCartException() {
			super("Cart is empty");
		}
	};

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
	
	// Helper functions for closing resources
		public static void closeResultSet(ResultSet resultSet) {
		    if (resultSet != null) {
		        try {
		            resultSet.close();
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        }
		    }
		}

		public static void closeStatement(Statement statement) {
		    if (statement != null) {
		        try {
		            statement.close();
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        }
		    }
		}
}
