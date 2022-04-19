package net.etfbl.ip.vm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.etfbl.ip.vm.dto.Ticket;

public class TicketDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO ticket (issue_date, tour_id, user_id) VALUES (?,?,?)";

	public TicketDAO() {
		// TODO Auto-generated constructor stub
	}

	
	public static boolean issueTicket(Ticket ticket) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {ticket.getIssueDate(), ticket.getTourId(),ticket.getUserId()};
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			if (generatedKeys.next())
			{
				ticket.setId(generatedKeys.getInt(1));
				System.out.println(ticket);
			}
			
			pstmt.close();
		} catch(Exception e) {
			
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}		
		return result;
		
	}
}
