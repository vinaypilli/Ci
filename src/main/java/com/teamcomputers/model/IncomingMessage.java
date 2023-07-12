package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class IncomingMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String message_id;

	@Column(name = "number_From")
	private String from;
	@Column
	private long received_at;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "context_id")
	private Context context;
	@Column
	private String message_type;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "textTypeId")
	private TextType text_type;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "imgId")
	private ImageType image_type;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locationId")
	private LocationType location_type;
	
	@CreationTimestamp
	private LocalDateTime createdDate;

	public IncomingMessage() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public long getReceived_at() {
		return received_at;
	}

	public void setReceived_at(long received_at) {
		this.received_at = received_at;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public TextType getText_type() {
		return text_type;
	}

	public void setText_type(TextType text_type) {
		this.text_type = text_type;
	}

	public ImageType getImage_type() {
		return image_type;
	}

	public void setImage_type(ImageType image_type) {
		this.image_type = image_type;
	}

	public LocationType getLocation_type() {
		return location_type;
	}

	public void setLocation_type(LocationType location_type) {
		this.location_type = location_type;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public IncomingMessage(Long id, String message_id, String from, long received_at, Context context,
			String message_type, TextType text_type, ImageType image_type, LocationType location_type,
			LocalDateTime createdDate) {
		super();
		this.id = id;
		this.message_id = message_id;
		this.from = from;
		this.received_at = received_at;
		this.context = context;
		this.message_type = message_type;
		this.text_type = text_type;
		this.image_type = image_type;
		this.location_type = location_type;
		this.createdDate = createdDate;
	}

	
}
