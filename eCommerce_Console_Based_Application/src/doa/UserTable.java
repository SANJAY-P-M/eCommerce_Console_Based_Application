package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import eCommerce_Console_Based_Application.Assets;
import roles.Customer;
public class UserTable {
	
//	returns
//		true for success insert of data
//		false if any exception occurred
//		throws Exception when e-mail (or) mobile number already exists
	public static boolean insertNewUser(Customer user) throws Exception{
		PreparedStatement statement = null;
		try {
			String sql = "INSERT INTO users (fullName, mobileNumber, password , email ,isAdmin) VALUES (?, ?, ?, ? ,0)";
			statement = Assets.connection.prepareStatement(sql);

			statement.setString(1, user.getFullName());
			statement.setString(2, user.getMobileNumber());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			return statement.executeUpdate() == 1;
		} catch (SQLIntegrityConstraintViolationException e) {
			
//			This occurs when user try's to use same e-mail (or) mobile number 
			if(e.getMessage().contains("users_UNIQUE")) throw new Exception("e-mail already exists");
			else throw new Exception("mobile number already exists");
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			Assets.closeStatement(statement);			
		}
	}

//	returns
//		password for the given e-mail
//		if e-mail does not exists (or) statement has error throws Exception
	public static String getPassword(String email) throws Exception {
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			String sql = "SELECT PASSWORD FROM USERS WHERE EMAIL = ? ";
			statement =  Assets.connection.prepareStatement(sql);
			statement.setString(1, email);
			result = statement.executeQuery();
			if(result.next()) return result.getString("password");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {			
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		throw new Exception("Invalid E-Mail");
	}
}
