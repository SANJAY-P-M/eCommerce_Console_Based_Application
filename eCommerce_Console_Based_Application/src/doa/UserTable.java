package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import eCommerce_Console_Based_Application.Assets;
import roles.User;
import roles.UserFactory;
public class UserTable {
	
/*
 * returns 
 * 		User object if user successfully inserted
 * 		else null	
 */
	public static User insert(User user,String role){
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			String sql = "INSERT INTO users (fullName, mobileNumber, password , email ,role) VALUES ( ? , ? , ?, ? , ? )";
			statement = Connector.getInstance().getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getMobileNumber());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setString(5, role);
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			if(result.next()) user.setUserId(result.getInt(1));
			
//			Factory design Pattern
			return UserFactory.getFactoryInstance().getInstance(user, role);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Assets.closeStatement(statement);		
			Assets.closeResultSet(result);
		}
		return null;
	}

/*
 * returns 
 * 		if mail exists password of given e-mail
 * 		else null
 */
	public static String getPassword(String email ){
		ResultSet result = null;
		PreparedStatement statement = null;
		String password = null;
		try {
			String sql = "SELECT password FROM USERS WHERE email = ? ";
			statement =  Connector.getInstance().getConnection().prepareStatement(sql);
			statement.setString(1, email);
			result = statement.executeQuery();
			if(result.next()) password = result.getString("password");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {			
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		return password;
	}
	
/*
 * returns User object if exists
 * 			else null 
 */
	public static User getUserWithEMail(String email){
		ResultSet result = null;
		PreparedStatement statement  = null;
		User user = null;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("SELECT * FROM users WHERE email = ?");
			statement.setString(1, email);
			result = statement.executeQuery();
			result.next();
			int userId = result.getInt("userId");
			user =  new User(
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
		return user;
	}
  
	public static boolean isMailExists(String mobileNumber) {
		PreparedStatement statement = null;
		ResultSet result = null;
		boolean isFound = false;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("SELECT EXISTS (SELECT 1 FROM users WHERE email = ?)");
			statement.setString(1, mobileNumber);
			result = statement.executeQuery();
			if(result.next()) isFound = result.getInt(1) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		return isFound;
	}

	public static boolean isMobileNumberExists(String mobileNumber) {
		PreparedStatement statement = null;
		ResultSet result = null;
		boolean isFound = false;
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("SELECT EXISTS (SELECT 1 FROM users WHERE mobileNumber = ?)");
			statement.setString(1, mobileNumber);
			result = statement.executeQuery();
			if(result.next()) isFound = result.getInt(1) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		return isFound;
	}
}
