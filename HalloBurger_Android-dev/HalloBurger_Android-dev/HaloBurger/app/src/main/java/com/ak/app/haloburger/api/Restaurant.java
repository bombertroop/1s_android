
package com.ak.app.haloburger.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("app_display_text")
    @Expose
    private String appDisplayText;
    @SerializedName("beacon_serial_number")
    @Expose
    private Object beaconSerialNumber;
    @SerializedName("beacon_uuid")
    @Expose
    private Object beaconUuid;
    @SerializedName("external_partner_id")
    @Expose
    private String externalPartnerId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("location_qrcode_identifier")
    @Expose
    private Object locationQrcodeIdentifier;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("online_order_link")
    @Expose
    private String onlineOrderLink;
    @SerializedName("online_order_support_status")
    @Expose
    private Boolean onlineOrderSupportStatus;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("social_link")
    @Expose
    private Object socialLink;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("today_open_hour")
    @Expose
    private TodayOpenHour todayOpenHour;
    @SerializedName("restaurant_distance")
    @Expose
    private String restaurantDistance;
    @SerializedName("is_open")
    @Expose
    private Boolean isOpen;
    @SerializedName("city_label")
    @Expose
    private String cityLabel;
    @SerializedName("state_label")
    @Expose
    private String stateLabel;
    @SerializedName("country_label")
    @Expose
    private String countryLabel;
    @SerializedName("available_offers")
    @Expose
    private List<AvailableOffer> availableOffers = null;
    @SerializedName("operating_hours")
    @Expose
    private List<OperatingHour> operatingHours = null;
    @SerializedName("online_partner")
    @Expose
    private Integer onlinePartner;
    @SerializedName("location_id")
    @Expose
    private Object locationId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppDisplayText() {
        return appDisplayText;
    }

    public void setAppDisplayText(String appDisplayText) {
        this.appDisplayText = appDisplayText;
    }

    public Object getBeaconSerialNumber() {
        return beaconSerialNumber;
    }

    public void setBeaconSerialNumber(Object beaconSerialNumber) {
        this.beaconSerialNumber = beaconSerialNumber;
    }

    public Object getBeaconUuid() {
        return beaconUuid;
    }

    public void setBeaconUuid(Object beaconUuid) {
        this.beaconUuid = beaconUuid;
    }

    public String getExternalPartnerId() {
        return externalPartnerId;
    }

    public void setExternalPartnerId(String externalPartnerId) {
        this.externalPartnerId = externalPartnerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Object getLocationQrcodeIdentifier() {
        return locationQrcodeIdentifier;
    }

    public void setLocationQrcodeIdentifier(Object locationQrcodeIdentifier) {
        this.locationQrcodeIdentifier = locationQrcodeIdentifier;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnlineOrderLink() {
        return onlineOrderLink;
    }

    public void setOnlineOrderLink(String onlineOrderLink) {
        this.onlineOrderLink = onlineOrderLink;
    }

    public Boolean getOnlineOrderSupportStatus() {
        return onlineOrderSupportStatus;
    }

    public void setOnlineOrderSupportStatus(Boolean onlineOrderSupportStatus) {
        this.onlineOrderSupportStatus = onlineOrderSupportStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getSocialLink() {
        return socialLink;
    }

    public void setSocialLink(Object socialLink) {
        this.socialLink = socialLink;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public TodayOpenHour getTodayOpenHour() {
        return todayOpenHour;
    }

    public void setTodayOpenHour(TodayOpenHour todayOpenHour) {
        this.todayOpenHour = todayOpenHour;
    }

    public String getRestaurantDistance() {
        return restaurantDistance;
    }

    public void setRestaurantDistance(String restaurantDistance) {
        this.restaurantDistance = restaurantDistance;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(String cityLabel) {
        this.cityLabel = cityLabel;
    }

    public String getStateLabel() {
        return stateLabel;
    }

    public void setStateLabel(String stateLabel) {
        this.stateLabel = stateLabel;
    }

    public String getCountryLabel() {
        return countryLabel;
    }

    public void setCountryLabel(String countryLabel) {
        this.countryLabel = countryLabel;
    }

    public List<AvailableOffer> getAvailableOffers() {
        return availableOffers;
    }

    public void setAvailableOffers(List<AvailableOffer> availableOffers) {
        this.availableOffers = availableOffers;
    }

    public List<OperatingHour> getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(List<OperatingHour> operatingHours) {
        this.operatingHours = operatingHours;
    }

    public Integer getOnlinePartner() {
        return onlinePartner;
    }

    public void setOnlinePartner(Integer onlinePartner) {
        this.onlinePartner = onlinePartner;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

}
