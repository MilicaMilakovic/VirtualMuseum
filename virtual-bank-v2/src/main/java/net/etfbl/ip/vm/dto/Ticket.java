package net.etfbl.ip.vm.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String issueDate = new Timestamp(System.currentTimeMillis()).toString();;
	private int tourId;
	private int userId;

	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Ticket(int id, String issueDate, int tourId, int userId) {
		super();
		this.id = id;
		this.issueDate = issueDate;
		this.tourId = tourId;
		this.userId = userId;
	}
	
	

	public Ticket(int tourId, int userId) {
		super();
		this.tourId = tourId;
		this.userId = userId;
	}

	public Ticket(String issueDate, int tourId, int userId) {
		super();
		this.issueDate = issueDate;
		this.tourId = tourId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public int getTourId() {
		return tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", issueDate=" + issueDate + ", tourId=" + tourId + ", userId=" + userId + "]";
	}

	
	
}
