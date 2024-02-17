package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import eCommerce_Console_Based_Application.Assets;
import roles.Customer;
public class UserTable {
	
//	returns
//		true for success insert of data
//		false if not inserted
//		throws Exception when userName (or) e-mail (or) mobile number already exists
	public static boolean insertNewUser(Customer customer) throws Exception{
		PreparedStatement statement = null;
		int rowsAffected = 0;
		try {
			String sql = "INSERT INTO users (userName ,fullName, mobileNumber, password , email) VALUES ( ? , ? , ?, ?, ?)";
			statement = Assets.connection.prepareStatement(sql);
			statement.setString(1, customer.getUserName());
			statement.setString(2, customer.getFullName());
			statement.setString(3, customer.getMobileNumber());
			statement.setString(4, customer.getPassword());
			statement.setString(5, customer.getEmail());
			rowsAffected = statement.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			String errorMessage = e.getMessage();
			if(errorMessage.contains("users.PRIMARY")) throw new Exception("userName already exists");
			else if(errorMessage.contains("user.email")) throw new Exception("email already exists");
			else throw new Exception("mobile number Already exists");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Assets.closeStatement(statement);			
		}
		return rowsAffected == 1;
	}

//	returns
//		password for the given e-mail
//		if e-mail does not exists (or) statement has error throws Exception
	public static String getPassword(String userName) throws Exception {
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			String sql = "SELECT password FROM USERS WHERE userName = ? ";
			statement =  Assets.connection.prepareStatement(sql);
			statement.setString(1, userName);
			result = statement.executeQuery();
			if(result.next()) return result.getString("password");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {			
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		throw new Exception("UserName Does not exists");
	}
}
