package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class LocationType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long locationId;
	private String address;
	private double latitude;
	private String name;
	private String url;
	private double longitude;
	@CreationTimestamp
	private LocalDateTime createdDate;

	public LocationType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocationType(Long locationId, String address, double latitude, String name, String url, double longitude,
			LocalDateTime createdDate) {
		super();
		this.locationId = locationId;
		this.address = address;
		this.latitude = latitude;
		this.name = name;
		this.url = url;
		this.longitude = longitude;
		this.createdDate = createdDate;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

}
