package com.ak.app.haloburger.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryOrderResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("delivery_order_number")
    @Expose
    private String delivaryOrderNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelivaryOrderNumber() {
        return delivaryOrderNumber;
    }

    public void setDelivaryOrderNumber(String delivaryOrderNumber) {
        this.delivaryOrderNumber = delivaryOrderNumber;
    }
}
