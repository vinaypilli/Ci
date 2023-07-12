package com.teamcomputers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teamcomputers.model.SLATimelines;

public class ServicePriorityDto {
	@JsonIgnoreProperties({"slaTimeInHours", "createdBy", "createdDate", "updatedBy", "updatedDate", "status" })
	private SLATimelines defaultPriority;
	
	public SLATimelines getDefaultPriority() {
		return defaultPriority;
	}
	public void setDefaultPriority(SLATimelines defaultPriority) {
		this.defaultPriority = defaultPriority;
	}
	public ServicePriorityDto(SLATimelines defaultPriority) {
		this.defaultPriority = defaultPriority;
	}
	public ServicePriorityDto() {
		super();
	}
}
