package net.etfbl.ip.vm.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int id;
	private int cardId;
	private BigDecimal amount;
	private String timestamp =new Timestamp(System.currentTimeMillis()).toString();
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	
	public Transaction(int cardId, BigDecimal amount) {
		super();
		this.cardId = cardId;
		this.amount = amount;
	}


	public Transaction(int id, int cardId, BigDecimal amount, String timestamp) {
		super();
		this.id = id;
		this.cardId = cardId;
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", cardId=" + cardId + ", amount=" + amount + ", timestamp=" + timestamp + "]";
	}

	
	
}
