package com.ak.app.haloburger.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryOrder {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("delivery_order_number")
    @Expose
    private String deliveryOrderNumber;

    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;

    @SerializedName("received_date")
    @Expose
    private String receivedDate;

    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    @SerializedName("is_receive_date_changed")
    @Expose
    private Boolean isReceiveDateChanged;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliveryOrderNumber() {
        return deliveryOrderNumber;
    }

    public void setDeliveryOrderNumber(String deliveryOrderNumber) {
        this.deliveryOrderNumber = deliveryOrderNumber;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getReceiveDateChanged() {
        return isReceiveDateChanged;
    }

    public void setReceiveDateChanged(Boolean receiveDateChanged) {
        isReceiveDateChanged = receiveDateChanged;
    }
}
