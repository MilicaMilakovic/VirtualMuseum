package net.etfbl.ip.vm.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import net.etfbl.ip.vm.dto.CreditCard;
import net.etfbl.ip.vm.dto.User;

public class CreditCardDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_ID= "SELECT * FROM credit_card WHERE id=?";
	private static final String SQL_SELECT_BY_CARD_NUMBER= "SELECT * FROM credit_card WHERE number=?";
	
	private static final String SQL_UPDATE_BALANCE = "UPDATE credit_card SET balance=(balance-?) WHERE id=?";
	private static final String SQL_UPDATE_STATUS = "UPDATE credit_card SET active=(not active) WHERE id=?";
	
	public CreditCardDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static CreditCard getCardByNumber(String cardNumber) {
		
		CreditCard card = null;
		Connection connection = null;
		ResultSet rs = null;
		Object[] values = {cardNumber};
		try {
			connection  = connectionPool.checkOut();
			
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_CARD_NUMBER, false, values);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				card = new CreditCard(rs.getInt("id"), rs.getString("number"), rs.getString("type"), rs.getString("expirationDate"), rs.getString("pin"), rs.getBigDecimal("balance"),
										rs.getBoolean("active"),rs.getInt("user_id"));
			}
			statement.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return card;
	}
	
	public static boolean updateBalance(int id, BigDecimal amount) {
		boolean result = false;
		Connection connection = null;
		ResultSet rs = null;
		Object[] values = {amount, id};
		
		try {
			connection  = connectionPool.checkOut();
			
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_UPDATE_BALANCE, false, values);
			result = statement.executeUpdate() >0 ? true : false;
			
//			if(rs.next()) {
//				card = new CreditCard(rs.getInt("id"), rs.getString("number"), rs.getString("type"), rs.getString("expirationDate"), rs.getString("pin"), rs.getBigDecimal("balance"),
//										rs.getBoolean("active"),rs.getInt("user_id"));
//			}
			statement.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
	}
	
	
	public static boolean cardExpired(CreditCard card) {
		String date[] = card.getExpirationDate().split("/");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		int currentYear = calendar.get(Calendar.YEAR) % 100;
		
		if(Integer.parseInt(date[1]) > currentYear)
			return false;
		if(Integer.parseInt(date[1]) == currentYear && Integer.parseInt(date[0]) <= currentMonth)			
			return false;
		
		return true;
	}
	
	public static CreditCard findCard( String cardholder,String cardNumber, String password) {
		CreditCard card = getCardByNumber(cardNumber);
		if(card!= null) {
			if(!card.getPin().equals(password))
				return null;
			User user = null;
			user = UserDAO.getUserById(card.getUserId());
			if(user == null)
				return null;
			
			if(!(user.getFirstName()+" " + user.getLastName()).equals(cardholder))
				return null;
		}
		
		return card;
	}
	
	
	public static boolean updateCardStatus(int id) {
		boolean result = false;
		Connection connection = null;
		ResultSet rs = null;
		Object[] values = {id};
		
		try {
			connection  = connectionPool.checkOut();
			
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_UPDATE_STATUS, false, values);
			result = statement.executeUpdate() >0 ? true : false;

			statement.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return result;
	}
	
	

}
