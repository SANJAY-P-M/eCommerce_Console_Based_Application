package doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import roles.Customer;
import roles.User;
import services.UICards;
public class UserTable {
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
			statement.executeUpdate();
			statement.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return new Customer(userName,mobileNumber);
	}

	//	OverLoading a method
//	throws Exception when password is Invalid
	public static User loginUser(String username, String password){
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT mobileNumber,password FROM users WHERE userName = ?");
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			if(resultSet.getString("password").equals(password)) {
				return new Customer(username, resultSet.getString("mobileNumber"));
			} else {
				UICards.printWarning("Invalid password    ");
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	returns 
//		if user exists -> ResultSet contains userName and Password
//		else null
	public static ResultSet getUserInformationWithUsername(String username) {
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

	public static ResultSet getUserInformationWithMobileNumber(String mobileNumber) {
		String sql = "SELECT userName,password FROM users WHERE mobileNumber = ?";
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        // Use a PreparedStatement to prevent SQL injection
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, mobileNumber);

	        // Execute the query and check if any rows are returned
	        resultSet = statement.executeQuery();
	        if(resultSet.next()) return resultSet; // return userName and password
	        else return null;

	    } catch (SQLException e) {
	        System.err.println("Error checking mobile number uniqueness: " + e.getMessage());
	        return null; // returns null
	    } finally {
	        // Close resources to avoid leaks
	        closeResultSet(resultSet);
	        closeStatement(statement);
	    }
	}

}
