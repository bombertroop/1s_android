package com.ak.app.haloburger.model;

public class AppSession {

	private String authToken;
	private String customerId;
	private String CardNumber;
	

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
}
