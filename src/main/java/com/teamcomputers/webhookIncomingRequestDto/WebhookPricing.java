package com.teamcomputers.webhookIncomingRequestDto;

public class WebhookPricing {

	private boolean billable;
    private String pricing_model;
    private String category;
	public boolean isBillable() {
		return billable;
	}
	public void setBillable(boolean billable) {
		this.billable = billable;
	}
	public String getPricing_model() {
		return pricing_model;
	}
	public void setPricing_model(String pricing_model) {
		this.pricing_model = pricing_model;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public WebhookPricing(boolean billable, String pricing_model, String category) {
		super();
		this.billable = billable;
		this.pricing_model = pricing_model;
		this.category = category;
	}
	public WebhookPricing() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
}
