package net.etfbl.ip.vm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.etfbl.ip.vm.dto.Tour;
import net.etfbl.ip.vm.dto.User;

public class UserDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_ID= "SELECT * FROM user WHERE id=?";
	
	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	public static User getUserById(int id) {
		User user= null;
		Connection connection = null;
		ResultSet rs = null;
		Object[] values = {id};
		
		try {
			connection  = connectionPool.checkOut();
			
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password")
								, rs.getString("email"), rs.getBoolean("logged_in"), rs.getBoolean("active"), rs.getString("role"), rs.getString("token"), rs.getBoolean("approved"));
			}
			statement.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
			
		return user;		
	}
}
