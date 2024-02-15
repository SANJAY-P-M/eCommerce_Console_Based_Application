package doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import roles.Customer;
import roles.User;
public class DatabaseController {
	private static Connection connection;
	public static void createConnection(){		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "12345");
			
		}catch(SQLException e) {
			System.out.println("         Cannot connect to db server busy ");
			e.printStackTrace();
		}
	}
	
	public static User createUser(String userName,String mobileNumber,String password) {
		try {
			String sql = "INSERT INTO users (userName, mobileNumber, password,isAdmin) VALUES (?, ?, ?,0)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, userName);
			statement.setString(2, mobileNumber);
			statement.setString(3, password);

			int rowsAffected = statement.executeUpdate();

			if (rowsAffected == 1) {
			    System.out.println("User inserted successfully!");
			} else {
			    System.out.println("Error inserting user.");
			}

			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
//			Occurs when user try's to create with same mobileNmber
			System.out.println("      Mobile number already exists   ");
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return new Customer(userName,mobileNumber);
	}

//	returns 
//		if user exists -> ResultSet contains userName and Password
//		else null
	public static ResultSet getUserInformation(String username) {
	    String sql = "SELECT userName,password FROM users WHERE userName = ?";
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        // Use a PreparedStatement to prevent SQL injection
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, username);

	        // Execute the query and check if any rows are returned
	        resultSet = statement.executeQuery();
	        if(resultSet.next()) return resultSet; // return userName and password
	        else return null;

	    } catch (SQLException e) {
	        System.err.println("Error checking username uniqueness: " + e.getMessage());
	        return null; // returns null
	    } finally {
	        // Close resources to avoid leaks
	        closeResultSet(resultSet);
	        closeStatement(statement);
	    }
	}

	// Helper functions for closing resources (you might already have these)
	private static void closeResultSet(ResultSet resultSet) {
	    if (resultSet != null) {
	        try {
	            resultSet.close();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	    }
	}

	private static void closeStatement(Statement statement) {
	    if (statement != null) {
	        try {
	            statement.close();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	    }
	}
	
//	OverLoading a method
//	throws Exception when password is Invalid
	public static User createUser(String username, String password) throws Exception{
		try {
	        PreparedStatement statement = connection.prepareStatement("SELECT userName, mobileNumber, password FROM users WHERE userName = ?");
	        statement.setString(1, username);
	        ResultSet resultSet = statement.executeQuery();
	        if(!resultSet.next()) {
	        	System.out.print("User Name does not exists");
	        	return null;
	        } else if(resultSet.getString("password").equals(password)) {
	        		return new Customer(resultSet.getString("userName"),resultSet.getString("mobileNumber"));
        	}
//	        when password mismatch
	        else throw new Exception("Invalid Pssword");
	    } catch (SQLException e) {
	        System.out.println("Error creating user: " + e.getMessage());
	        return null;
	    }
	}

	
	public static void deleteRecord(String userName) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("DELETE COLUMN FROM USERS WHERE userName = ?");
			statement.setString(0, userName);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
		}
	}

	public static void updateMobileNumber(String username ,String mobileNumber) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("UPDATE USERS SET mobileNumber = ? WHERE userName = ?");
			statement.setString(0, username);
			statement.setString(1, mobileNumber);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
		}
	}

}
