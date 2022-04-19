package net.etfbl.ip.vm.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CreditCard implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String number;
	private String type;
	private String expirationDate;
	private String pin;
	private BigDecimal balance;
	private boolean active;
	private int userId;

	public CreditCard() {
		// TODO Auto-generated constructor stub
	}

	public CreditCard(int id, String number, String type, String expirationDate, String pin, BigDecimal balance,
			boolean active, int userId) {
		super();
		this.id = id;
		this.number = number;
		this.type = type;
		this.expirationDate = expirationDate;
		this.pin = pin;
		this.balance = balance;
		this.active = active;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", number=" + number + ", type=" + type + ", expirationDate=" + expirationDate
				+ ", pin=" + pin + ", balance=" + balance + ", active=" + active + ", userId=" + userId + "]";
	}
	
	

}
