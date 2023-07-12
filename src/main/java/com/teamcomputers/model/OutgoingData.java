package com.teamcomputers.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="outgoing_data")
public class OutgoingData {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OutgoingData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutgoingData(String id) {
		super();
		this.id = id;
	}

}
