package net.etfbl.ip.vm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.etfbl.ip.vm.dto.Museum;
import net.etfbl.ip.vm.dto.Tour;

public class MuseumDAO {


	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_ID= "SELECT * FROM museum WHERE id=?";
	
	public MuseumDAO() {
		// TODO Auto-generated constructor stub
	}

	public static Museum getMuseumById(int id) {
		Museum museum= null;
		Connection connection = null;
		ResultSet rs = null;
		Object[] values = {id};
		
		try {
			connection  = connectionPool.checkOut();
			
			PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				museum = new Museum(rs.getInt("id"), rs.getString("name"),rs.getString("address"),rs.getString("phone"), rs.getString("city"),
									rs.getString("country"), rs.getString("type"), rs.getString("image"), rs.getBoolean("active"), rs.getBigDecimal("latitude"),
									rs.getBigDecimal("longitude"), rs.getString("description"),rs.getString("presentation"));
			}
			statement.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
			
		return museum;		
	}
}
