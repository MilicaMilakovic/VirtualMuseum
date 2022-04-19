package net.etfbl.ip.vm.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class Museum implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String address;
	private String phone;
	private String city;
	private String country;
	private String type;
	private String image;
	private boolean active;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String description;
	private String presentation;

	public Museum() {
		// TODO Auto-generated constructor stub
	}

	public Museum(int id, String name, String address, String phone, String city, String country, String type,
			String image, boolean active, BigDecimal latitude, BigDecimal longitude, String description,
			String presentation) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.city = city;
		this.country = country;
		this.type = type;
		this.image = image;
		this.active = active;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
		this.presentation = presentation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
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
		Museum other = (Museum) obj;
		return id == other.id;
	}
	
	
	

}
