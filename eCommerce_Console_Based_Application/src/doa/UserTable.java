package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import eCommerce_Console_Based_Application.Assets;
import eCommerce_Console_Based_Application.Assets.EmailAlreadyExistsException;
import eCommerce_Console_Based_Application.Assets.MobileNumberAlreadyExistsException;
import eCommerce_Console_Based_Application.Assets.NoSuchEmailException;
import roles.Customer;
public class UserTable {
	
//	returns void
//		throws Exception when e-mail (or) mobile number already exists
	public static void insertNewUser(Customer customer) throws EmailAlreadyExistsException, MobileNumberAlreadyExistsException{
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			String sql = "INSERT INTO users (fullName, mobileNumber, password , email) VALUES ( ? , ? , ?, ?)";
			statement = Assets.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, customer.getFullName());
			statement.setString(2, customer.getMobileNumber());
			statement.setString(3, customer.getPassword());
			statement.setString(4, customer.getEmail());
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			if(result.next()) customer.setUserId(result.getInt(1));
		} catch (SQLIntegrityConstraintViolationException e) {
			String errorMessage = e.getMessage();
			if(errorMessage.contains("users.email")) throw new Assets.EmailAlreadyExistsException();
			else if(errorMessage.contains("users.mobile")) throw new Assets.MobileNumberAlreadyExistsException();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Assets.closeStatement(statement);		
			Assets.closeResultSet(result);
		}
	}

//	returns
//		password for the given e-mail
//		if e-mail does not exists (or) statement has error throws Exception
	public static String getPassword(String email ) throws NoSuchEmailException{
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			String sql = "SELECT password FROM USERS WHERE email = ? ";
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
		throw new Assets.NoSuchEmailException();
	}
	
//	returns
//		userName if exists else throw Exception
	public static Customer getUserWithEMail(String email) throws NoSuchEmailException{
		ResultSet result = null;
		PreparedStatement statement  = null;
		try {
			statement = Assets.connection.prepareStatement("SELECT * FROM users WHERE email = ?");
			statement.setString(1, email);
			result = statement.executeQuery();
			result.next();
			int userId = result.getInt("userId");
			return new Customer(
					userId,
					result.getString("fullName"),
					email, 
					result.getString("mobileNumber"),
					result.getString("password"),
					OrderTable.getOrders(userId),
					CartTable.getCart(userId)
				);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		throw new Assets.NoSuchEmailException();
	}
}
