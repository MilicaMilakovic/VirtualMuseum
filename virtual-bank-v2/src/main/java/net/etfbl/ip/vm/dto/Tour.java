package net.etfbl.ip.vm.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tour implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int id;
	private int museumId;
	private String start;
	private int duration;
	private BigDecimal price;
	private boolean active;
	
	
	public Tour() {
		// TODO Auto-generated constructor stub
	}


	public Tour(int id,int museumId, String start, int duration, BigDecimal price, boolean active) {
		super();
		this.id = id;
		this.museumId = museumId;
		this.start = start;
		this.duration = duration;
		this.price = price;
		this.active = active;
	}


	@Override
	public String toString() {
		return "Tour [museumId=" + museumId + ", start=" + start + ", duration=" + duration + ", price=" + price
				+ ", active=" + active + "]";
	}


	public int getMuseumId() {
		return museumId;
	}


	public void setMuseumId(int museumId) {
		this.museumId = museumId;
	}


	public String getStart() {
		return start;
	}


	public void setStart(String start) {
		this.start = start;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	
}
