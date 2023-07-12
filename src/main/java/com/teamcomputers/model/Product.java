package com.teamcomputers.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	@Column(nullable = false, updatable = false)
	private String serialNo;
	@Column(nullable = false, unique = true)
	private String productName;
	@Column(nullable = false)
	private String productDescription;
//	@Column(nullable = false)
//	private double productPrice;

	@ElementCollection
	@CollectionTable(name = "product_prices_mapping", joinColumns = @JoinColumn(name = "product_id"))
	@MapKeyColumn(name = "price_key")
	@Column(name = "price_value", nullable = false)
	private Map<String, Double> productPrices = new HashMap<>();

	private boolean ischeckboxPiece;
	private boolean ischeckboxGM250;
	private boolean ischeckboxGM500;
	private boolean ischeckboxKG1;
	private boolean ischeckboxLT1;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private OptionEnum options;

//	@Enumerated(EnumType.STRING)
//	@Column(nullable = false)
//	private UnitOfMeasureEnnum unitOfMeasure;
	private boolean status;
	@Lob
	@JsonIgnore
	private byte[] image;

	private String fileUrl;
	private String filePath;
	private String filename;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createdBy", referencedColumnName = "userId", nullable = false, updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao createdBy;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updatedBy", referencedColumnName = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@OneToMany(mappedBy = "product")
	private List<Order_Product> orderProduct;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productCategory")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate" })
	private ProductCategory productCategory;

	@PrePersist
	private void generateSerialNo() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddHHmm");
		this.serialNo = now.format(formatter);
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public UserDao getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDao createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public UserDao getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDao updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Product(long productId, String serialNo, String productName, String productDescription,
			Map<String, Double> productPrices, boolean ischeckboxPiece, boolean ischeckboxGM250,
			boolean ischeckboxGM500, boolean ischeckboxKG1, boolean ischeckboxLT1, OptionEnum options, boolean status,
			byte[] image, String fileUrl, String filePath, String filename, UserDao createdBy,
			LocalDateTime createdDate, UserDao updatedBy, LocalDateTime updatedDate, List<Order_Product> orderProduct,
			ProductCategory productCategory) {
		super();
		this.productId = productId;
		this.serialNo = serialNo;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrices = productPrices;
		this.ischeckboxPiece = ischeckboxPiece;
		this.ischeckboxGM250 = ischeckboxGM250;
		this.ischeckboxGM500 = ischeckboxGM500;
		this.ischeckboxKG1 = ischeckboxKG1;
		this.ischeckboxLT1 = ischeckboxLT1;
		this.options = options;
		this.status = status;
		this.image = image;
		this.fileUrl = fileUrl;
		this.filePath = filePath;
		this.filename = filename;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.orderProduct = orderProduct;
		this.productCategory = productCategory;
	}

	public Product(long productId, String serialNo, String productName, String productDescription,
			Map<String, Double> productPrices, boolean ischeckboxPiece, boolean ischeckboxGM250,
			boolean ischeckboxGM500, boolean ischeckboxKG1, boolean ischeckboxLT1, OptionEnum options, boolean status,
			UserDao createdBy, LocalDateTime createdDate, UserDao updatedBy, LocalDateTime updatedDate,
			List<Order_Product> orderProduct, ProductCategory productCategory) {
		super();
		this.productId = productId;
		this.serialNo = serialNo;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrices = productPrices;
		this.ischeckboxPiece = ischeckboxPiece;
		this.ischeckboxGM250 = ischeckboxGM250;
		this.ischeckboxGM500 = ischeckboxGM500;
		this.ischeckboxKG1 = ischeckboxKG1;
		this.ischeckboxLT1 = ischeckboxLT1;
		this.options = options;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.orderProduct = orderProduct;
		this.productCategory = productCategory;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

}
