package com.teamcomputers.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class WebhookDelivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deliveryId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "deliveryId")
	private List<DeliveryMessageEntity> delivery_status;

	public WebhookDelivery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebhookDelivery(Long deliveryId, List<DeliveryMessageEntity> delivery_status) {
		super();
		this.deliveryId = deliveryId;
		this.delivery_status = delivery_status;
	}

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public List<DeliveryMessageEntity> getDelivery_status() {
		return delivery_status;
	}

	public void setDelivery_status(List<DeliveryMessageEntity> delivery_status) {
		this.delivery_status = delivery_status;
	}

}
