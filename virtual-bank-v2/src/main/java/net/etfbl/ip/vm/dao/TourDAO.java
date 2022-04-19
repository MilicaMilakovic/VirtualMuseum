package net.etfbl.ip.vm.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.etfbl.ip.vm.dto.CreditCard;
import net.etfbl.ip.vm.dto.Tour;

public class TourDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_ID= "SELECT * FROM tour WHERE id=?";
	
	
	public TourDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static BigDecimal getTourPrice(int id) {
		
		Tour tour= getTourById(id);
		if(tour!=null)
			return tour.getPrice();
		return null;		
	}
	
	public static Tour getTourById(int id) {
		Tour tour= null;
		Connection connection = null;
		ResultSet rs = null;
		Object[] values = {id};
		
		try {
			connection  = connectionPool.checkOut();
			
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				tour = new Tour(rs.getInt("id"),rs.getInt("museum_id"),rs.getString("start"),rs.getInt("duration"),rs.getBigDecimal("price"),rs.getBoolean("active"));
			}
			statement.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
			
		return tour;		
	}
	
	

}
