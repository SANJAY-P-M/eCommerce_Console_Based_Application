package eCommerce_Console_Based_Application;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Assets {
	
	public static Scanner scan = new Scanner(System.in);
	
//	Sign up prompt
	public static String[] signUpPromptList = {"Sign up (new user)","Sign in (existing user)","Close Application"};
	
//	Customer home prompt
	public static String[] customerHome = {
			"View Profile",
			"View products",
			"View Cart",
			"View orders",
			"Log out"
	};
	
//	Modify profile
	public static String[] modifyProfile = {
			"Change e-mail",
			"Change full Name",
			"Change Mobile Number",
			"Change password",
			"Back",
	};

	public static String[] selectProduct = {"Select product with product Id ","Back"};

	public static String[] doWithProduct = {"Add to cart","Place order","Back"};

	public static String[] employeeHome = {"View profile ","Get his work","logout"};

	public static String[] adminHome = {"View profile","Add new Employee","Remove a employee","View products","Logout"};
	
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
