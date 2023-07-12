package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class ServiceTitleDto {

	 private int serviceId;
	    private String serviceName;
	    private long defaultPriority;
	    private int subCategoryId;
	    private long createdBy;
	    private LocalDateTime createdDate;
	    private long updatedBy;
	    private LocalDateTime updatedDate;
	    private boolean status=true;
	   
	    
	    public ServiceTitleDto() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


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


		public long getDefaultPriority() {
			return defaultPriority;
		}


		public void setDefaultPriority(long defaultPriority) {
			this.defaultPriority = defaultPriority;
		}


		public int getSubCategoryId() {
			return subCategoryId;
		}


		public void setSubCategoryId(int subCategoryId) {
			this.subCategoryId = subCategoryId;
		}


		public long getCreatedBy() {
			return createdBy;
		}


		public void setCreatedBy(long createdBy) {
			this.createdBy = createdBy;
		}


		public LocalDateTime getCreatedDate() {
			return createdDate;
		}


		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}


		public long getUpdatedBy() {
			return updatedBy;
		}


		public void setUpdatedBy(long updatedBy) {
			this.updatedBy = updatedBy;
		}


		public LocalDateTime getUpdatedDate() {
			return updatedDate;
		}


		public void setUpdatedDate(LocalDateTime updatedDate) {
			this.updatedDate = updatedDate;
		}


		public boolean isStatus() {
			return status;
		}


		public void setStatus(boolean status) {
			this.status = status;
		}


		public ServiceTitleDto(int serviceId, String serviceName, long defaultPriority, int subCategoryId,
				long createdBy, LocalDateTime createdDate, long updatedBy, LocalDateTime updatedDate, boolean status) {
			super();
			this.serviceId = serviceId;
			this.serviceName = serviceName;
			this.defaultPriority = defaultPriority;
			this.subCategoryId = subCategoryId;
			this.createdBy = createdBy;
			this.createdDate = createdDate;
			this.updatedBy = updatedBy;
			this.updatedDate = updatedDate;
			this.status = status;
		}
	      
	   
}
