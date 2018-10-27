package com.ak.app.haloburger.model;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;

import org.json.JSONException;
import org.json.JSONObject;


public class SignUpBean extends AuthenticationBean {

	private String firstName;
	private String lastName;
	private String passwordHint;
	private String phoneNumber;
	private String latitude;
	private String longitude;
	private String dobDay;
	private String dobMonth;
	private String dobYear;
	private String marketingOptin;
	private String marketingOptinTexting;
	private String zipCode;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String referralCode;
	private String favLoc;

	public SignUpBean() {
		super.setConnectType("1");
		super.setServiceName("/user/signup");
		super.setParamList(new String[] { super.getServiceName() });
	}

	public JSONObject getJsonObj() throws JSONException {

		JSONObject obj = super.getObj();

		obj.put("first_name", getFirstName());
		obj.put("last_name", getLastName());
		obj.put("phone_number", getPhoneNumber());
		obj.put("latitude", getLatitude());
		obj.put("longitude", getLongitude());
		obj.put("referral_code", getReferralCode());
		obj.put("dob_day", getDobDay());
		obj.put("dob_month", getDobMonth());
		obj.put("dob_year", getDobYear());
		obj.put("marketing_optin", getMarketingOptin());
		obj.put("marketing_optin_texting ", getMarketingOptinTexting());
		obj.put("zipcode", getZipCode());
		obj.put("address_line_1", getAddressLine1());
		obj.put("address_line_2", getAddressLine2());
		obj.put("city", getCity());
		obj.put("state", getState());
		obj.put("password_hint", getPasswordHint());
		obj.put("favorite_location", getFavLoc());
		obj.put("appkey", MainActivity.getInstance().getResources().getString(R.string.appkey));

		return obj;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public String getDobDay() {
		return dobDay;
	}

	public void setDobDay(String dobDay) {
		this.dobDay = dobDay;
	}

	public String getDobMonth() {
		return dobMonth;
	}

	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}

	public String getDobYear() {
		return dobYear;
	}

	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}

	public String getMarketingOptin() {
		return marketingOptin;
	}

	public void setMarketingOptin(String marketingOptin) {
		this.marketingOptin = marketingOptin;
	}

	public String getMarketingOptinTexting() {
		return marketingOptinTexting;
	}

	public void setMarketingOptinTexting(String marketingOptinTexting) {
		this.marketingOptinTexting = marketingOptinTexting;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getFavLoc() {
		return favLoc;
	}

	public void setFavLoc(String favLoc) {
		this.favLoc = favLoc;
	}

}
