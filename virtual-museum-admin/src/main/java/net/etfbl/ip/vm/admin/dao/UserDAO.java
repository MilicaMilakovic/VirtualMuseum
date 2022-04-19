package net.etfbl.ip.vm.admin.dao;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


import net.etfbl.ip.vm.admin.dto.User;
import net.etfbl.ip.vm.admin.dao.DAOUtil;
import net.etfbl.ip.vm.admin.dao.ConnectionPool;

public class UserDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_ID= "SELECT * FROM user WHERE id=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM user";
	private static final String SQL_RESET_PASSWORD = "UPDATE user SET password=? WHERE id=?";
	private static final String SQL_APPROVE_REGISTRATION = "UPDATE user SET approved=true WHERE id=?";
	private static final String SQL_DEACTIVATE_ACCOUNT = "UPDATE user SET active=(not active) WHERE id=?";
	
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
	
	public static boolean authenticate(int id, String token) {
		User user = getUserById(id);
		if(token.equals(user.getToken()) && user.isActive())
			return true;
		return false;
	}
	
	
	public static ArrayList<User> selectAll(){
		ArrayList<User> retVal = new ArrayList<User>();
		
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			 retVal.add(new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password")
						, rs.getString("email"), rs.getBoolean("logged_in"), rs.getBoolean("active"), rs.getString("role"), rs.getString("token"), rs.getBoolean("approved")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		
		return retVal;
		
	}
	
	//za blokiranje i brisanje
	public static boolean deactivateAccount(int id) {
		boolean result = false;
		Connection connection = null;
		ResultSet rs = null;		
	
		Object[] values = {id};		
		
		try {
			connection  = connectionPool.checkOut();
			
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_DEACTIVATE_ACCOUNT, false, values);
			result = statement.executeUpdate() >0 ? true : false;

			statement.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
	}
	
	public static boolean approveRegistration(int id) {
		boolean result = false;
		Connection connection = null;
		ResultSet rs = null;		
	
		Object[] values = {id};
		
		
		try {
			connection  = connectionPool.checkOut();
			
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_APPROVE_REGISTRATION, false, values);
			result = statement.executeUpdate() >0 ? true : false;

			statement.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
	}
	
	public static boolean resetPassword(int id) {
		
		boolean result = false;
		Connection connection = null;
		ResultSet rs = null;
		
		String newPassword = generatePassword();
		
		Object[] values = {newPassword, id};
		
		
		try {
			connection  = connectionPool.checkOut();
			
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_RESET_PASSWORD, false, values);
			result = statement.executeUpdate() >0 ? true : false;

			statement.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
		
	}
	
	private static String generatePassword() {
		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    int length = 15;

		
		 String generatedString = "";
		 
		 for(int i = 0; i < length; i++) {
		      int index = random.nextInt(alphabet.length());
		      char randomChar = alphabet.charAt(index);
		      sb.append(randomChar);
		 }
		 
		 generatedString = sb.toString();

		return generatedString;
	}
}
