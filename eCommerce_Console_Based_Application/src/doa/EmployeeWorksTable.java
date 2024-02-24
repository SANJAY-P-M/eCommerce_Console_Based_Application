package doa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import eCommerce_Console_Based_Application.Assets;
import roles.Order;

public class EmployeeWorksTable {

	public static List<Order> getWorks(int userId) {
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Order> list = new ArrayList<>();
		try {
			statement = Connector.getInstance().getConnection().prepareStatement("SELECT orderId FROM EmployeeWorks WHERE empId = ?");
			statement.setInt(1, userId);;
			result = statement.executeQuery();
			while(result.next()) {
				list.add(OrderTable.getOrder(result.getInt("orderId")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Assets.closeResultSet(result);
			Assets.closeStatement(statement);
		}
		return null;
	}

}
