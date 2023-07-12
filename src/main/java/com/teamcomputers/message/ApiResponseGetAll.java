package com.teamcomputers.message;

import java.util.List;



public class ApiResponseGetAll<T> {

	private String status;
    private int code;
    private String message;
    private int totalPages;
    private int currentPage;
    private int totalRecords;
    private int perPage;
    private List<T> data;

	public ApiResponseGetAll() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApiResponseGetAll(String status, int code, String message, List<T> data, int totalPages, int currentPage,
			int totalRecords, int perPage) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.totalRecords = totalRecords;
		this.perPage = perPage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	
}
