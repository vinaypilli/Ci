package com.teamcomputers.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamcomputers.model.OptionEnum;

public class ProductDto {

	private long productId;
	private String serialNo;
	private String productName;
	private String productDescription;
//	private double productPrice;
//	private List<String> keys;

	private Map<String, Double> productPrices = new HashMap<>();

	private boolean ischeckboxPiece;
	private boolean ischeckboxGM250;
	private boolean ischeckboxGM500;
	private boolean ischeckboxKG1;
	private boolean ischeckboxLT1;

//	@Enumerated(EnumType.ORDINAL)
	private OptionEnum options;

	@JsonIgnore
	private byte[] image;
	private long createdBy;
	private LocalDateTime createdDate;
	private long updatedBy;
	private LocalDateTime updatedDate;
	private boolean status = true;

	private String fileUrl;
	private String filePath;
	private String filename;

	private long productCategoryId;

	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

//	public double getProductPrice() {
//		return productPrice;
//	}
//
//	public void setProductPrice(double productPrice) {
//		this.productPrice = productPrice;
//	}

//	public List<String> getKeys() {
//		return keys;
//	}
//
//	public void setKeys(List<String> keys) {
//		this.keys = keys;
//	}

	public Map<String, Double> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(Map<String, Double> productPrices) {
		this.productPrices = productPrices;
	}

	public boolean isIscheckboxPiece() {
		return ischeckboxPiece;
	}

	public void setIscheckboxPiece(boolean ischeckboxPiece) {
		this.ischeckboxPiece = ischeckboxPiece;
	}

	public boolean isIscheckboxGM250() {
		return ischeckboxGM250;
	}

	public void setIscheckboxGM250(boolean ischeckboxGM250) {
		this.ischeckboxGM250 = ischeckboxGM250;
	}

	public boolean isIscheckboxGM500() {
		return ischeckboxGM500;
	}

	public void setIscheckboxGM500(boolean ischeckboxGM500) {
		this.ischeckboxGM500 = ischeckboxGM500;
	}

	public boolean isIscheckboxKG1() {
		return ischeckboxKG1;
	}

	public void setIscheckboxKG1(boolean ischeckboxKG1) {
		this.ischeckboxKG1 = ischeckboxKG1;
	}

	public boolean isIscheckboxLT1() {
		return ischeckboxLT1;
	}

	public void setIscheckboxLT1(boolean ischeckboxLT1) {
		this.ischeckboxLT1 = ischeckboxLT1;
	}

	public OptionEnum getOptions() {
		return options;
	}

	public void setOptions(OptionEnum options) {
		this.options = options;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public ProductDto(long productId, String serialNo, String productName, String productDescription,
//			double productPrice, List<String> keys, 
			Map<String, Double> productPrices, boolean ischeckboxPiece, boolean ischeckboxGM250,
			boolean ischeckboxGM500, boolean ischeckboxKG1, boolean ischeckboxLT1, OptionEnum options, byte[] image,
			long createdBy, LocalDateTime createdDate, long updatedBy, LocalDateTime updatedDate, boolean status,
			String fileUrl, String filePath, String filename, long productCategoryId) {
		super();
		this.productId = productId;
		this.serialNo = serialNo;
		this.productName = productName;
		this.productDescription = productDescription;
		// this.productPrice = productPrice;
		// this.keys = keys;
		this.productPrices = productPrices;
		this.ischeckboxPiece = ischeckboxPiece;
		this.ischeckboxGM250 = ischeckboxGM250;
		this.ischeckboxGM500 = ischeckboxGM500;
		this.ischeckboxKG1 = ischeckboxKG1;
		this.ischeckboxLT1 = ischeckboxLT1;
		this.options = options;
		this.image = image;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
		this.fileUrl = fileUrl;
		this.filePath = filePath;
		this.filename = filename;
		this.productCategoryId = productCategoryId;
	}

	public ProductDto(long productId, String serialNo, String productName, String productDescription,
//			 double productPrice, List<String> keys, 
			Map<String, Double> productPrices, boolean ischeckboxPiece, boolean ischeckboxGM250,
			boolean ischeckboxGM500, boolean ischeckboxKG1, boolean ischeckboxLT1, OptionEnum options, long createdBy,
			LocalDateTime createdDate, long updatedBy, LocalDateTime updatedDate, boolean status,
			long productCategoryId) {
		super();
		this.productId = productId;
		this.serialNo = serialNo;
		this.productName = productName;
		this.productDescription = productDescription;
//		this.productPrice = productPrice;
//		this.keys = keys;
		this.productPrices = productPrices;
		this.ischeckboxPiece = ischeckboxPiece;
		this.ischeckboxGM250 = ischeckboxGM250;
		this.ischeckboxGM500 = ischeckboxGM500;
		this.ischeckboxKG1 = ischeckboxKG1;
		this.ischeckboxLT1 = ischeckboxLT1;
		this.options = options;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
		this.productCategoryId = productCategoryId;
	}

}
