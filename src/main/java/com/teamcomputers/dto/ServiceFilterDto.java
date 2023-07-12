package com.teamcomputers.dto;

public class ServiceFilterDto {
	private int serviceId;
	private String serviceName;
	
	
	
	
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public ServiceFilterDto(int serviceId, String serviceName) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
	}
	public ServiceFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
