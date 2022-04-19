package net.etfbl.ip.vm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import net.etfbl.ip.vm.dto.Transaction;

public class TransactionDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SQL_INSERT = "INSERT INTO transaction (card_id, amount, timestamp) VALUES (?,?,?)";
	private static final String SQL_GET_CARD_TRANSACTIONS = "SELECT * from transaction WHERE card_id=?";
	
	public TransactionDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean newTransaction(Transaction transaction) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {transaction.getCardId(), transaction.getAmount(), transaction.getTimestamp() };
		
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
				transaction.setId(generatedKeys.getInt(1));
				System.out.println(transaction);
			}
			
			pstmt.close();
		} catch(Exception e) {
			
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}		
		return result;
	}

	
	public static ArrayList<Transaction> getCardTransactions(int cardID){
		ArrayList<Transaction> retVal = new ArrayList<Transaction>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {cardID};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_CARD_TRANSACTIONS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new Transaction(rs.getInt("id"),rs.getInt("card_id"),rs.getBigDecimal("amount"), rs.getString("timestamp")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
}
